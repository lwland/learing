package learn.lwl.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.handler.stream.ChunkedFile;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

import javax.activation.MimetypesFileTypeMap;
import javax.net.ssl.SSLException;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.cert.CertificateException;
import java.util.regex.Pattern;

public class HttpFileServer {

    private static final Pattern INSECURE_URI = Pattern.compile(".*[<>&\"].*");
    private static final String url = "/src/main/resources";

    public void bind() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel socketChannel) throws Exception {
                        System.out.println("one connect");
                        socketChannel.pipeline().addLast(new HttpServerCodec());
//                        socketChannel.pipeline().addLast("httpDecoder", new HttpRequestDecoder());
                        socketChannel.pipeline().addLast("httpAggregator", new HttpObjectAggregator(65536));
//                        socketChannel.pipeline().addLast("httpEncoder", new HttpResponseEncoder());
                        socketChannel.pipeline().addLast("httpChunked", new ChunkedWriteHandler());
                        socketChannel.pipeline().addLast("fileServerHandler", new FileServerHandler(url));
                    }
                });
        try {
            ChannelFuture future = serverBootstrap.bind("127.0.0.1", 8080).sync();
            future.channel().closeFuture().sync();
            ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    private class FileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
        private String url;

        public FileServerHandler(String url) {
            super();
            this.url = url;
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            if (ctx.channel().isActive()) {
                cause.printStackTrace();
                sendError(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest httpRequest) throws Exception {
            System.out.println("begin to read");
            if (!httpRequest.decoderResult().isSuccess()) {
                sendError(channelHandlerContext, HttpResponseStatus.BAD_REQUEST);
                return;
            }
            if (httpRequest.method() != HttpMethod.GET) {
                sendError(channelHandlerContext, HttpResponseStatus.METHOD_NOT_ALLOWED);
                return;
            }
            final String uri = httpRequest.uri();
            final String path = sanitize(uri);
            if (path == null) {
                sendError(channelHandlerContext, HttpResponseStatus.FORBIDDEN);
                return;
            }
            File file = new File(path);
            if (file.isHidden() || !file.exists()) {
                sendError(channelHandlerContext, HttpResponseStatus.NOT_FOUND);
                return;
            }
            if (file.isDirectory()) {
                if (uri.endsWith("/")) {
                    sendListing(channelHandlerContext, file);
                } else {
                    sendRedirect(channelHandlerContext, uri + "/");
                }
                return;

            }
            if (!file.isFile()) {
                sendError(channelHandlerContext, HttpResponseStatus.FORBIDDEN);
                return;
            }
            RandomAccessFile randomAccessFile = null;
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
            } catch (Exception e) {
                sendError(channelHandlerContext, HttpResponseStatus.NOT_FOUND);
                return;
            }
            long fileLen = randomAccessFile.length();
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            HttpUtil.setContentLength(response, fileLen);
            MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, mimeTypesMap.getContentType(file.getPath()));
            if (HttpUtil.isKeepAlive(httpRequest)) {
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            channelHandlerContext.write(response);
            ChannelFuture sendFuture;
            sendFuture = channelHandlerContext.write(new ChunkedFile(randomAccessFile, 0, fileLen, 8192),
                    channelHandlerContext.newProgressivePromise());
            ChannelFuture lastContentFuture = channelHandlerContext.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            sendFuture.addListener(new ChannelProgressiveFutureListener() {
                @Override
                public void operationComplete(ChannelProgressiveFuture channelProgressiveFuture) throws Exception {
                    System.out.println("Transfer complete");
                }

                @Override
                public void operationProgressed(ChannelProgressiveFuture channelProgressiveFuture, long progress, long total) throws Exception {
                    if (total < 0) {
                        System.err.println("Transfer progress: " + progress);
                    } else {
                        System.err.println("Transfer progress: " + progress + "/" + total);
                    }
                }

            });
            if (!HttpUtil.isKeepAlive(httpRequest)) {
                lastContentFuture.addListener(ChannelFutureListener.CLOSE);
            }

        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        private final Pattern ALLOWED_FILE_NAME = Pattern.compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");

        private void sendListing(ChannelHandlerContext channelHandlerContext, File dir) {
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");
            String dirPath = dir.getPath();
            StringBuffer buf = new StringBuffer();
            buf.append("<!DOCTYPE html>\r\n");
            buf.append("<html><head><title>");
            buf.append(dirPath);
            buf.append("目录:");
            buf.append("</title></head><body>\r\n");

            buf.append("<h3>");
            buf.append(dirPath).append(" 目录：");
            buf.append("</h3>\r\n");
            buf.append("<ul>");
            buf.append("<li>链接：<a href=\"../\">..</a></li>\r\n");
            for (File f : dir.listFiles()) {
                if (f.isHidden() || !f.canRead()) {
                    continue;
                }
                String name = f.getName();
                if (!ALLOWED_FILE_NAME.matcher(name).matches()) {
                    continue;
                }

                buf.append("<li>链接：<a href=\"");
                buf.append(name);
                buf.append("\">");
                buf.append(name);
                buf.append("</a></li>\r\n");
            }

            buf.append("</ul></body></html>\r\n");
            ByteBuf byteBuf = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
            response.content().writeBytes(byteBuf);
            byteBuf.release();
            channelHandlerContext.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

        }

        private void sendRedirect(ChannelHandlerContext channelHandlerContext, String uri) {
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FOUND);
            response.headers().set(HttpHeaderNames.LOCATION, uri);
            channelHandlerContext.writeAndFlush(response);
        }

        private String sanitize(String str) {
            String uri;
            try {
                uri = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                try {
                    uri = URLDecoder.decode(str, "ISO-8859-1");
                } catch (UnsupportedEncodingException e1) {
                    throw new Error();
                }
            }
            if (!uri.startsWith(url)) {
                return null;
            }
            if (!uri.startsWith("/")) {
                return null;
            }
            uri.replaceAll("/", File.separator);
            if (uri.startsWith(".") || uri.endsWith(".") || uri.contains("." + File.separator) || uri.contains(File.separator + ".") ||
                    INSECURE_URI.matcher(uri).matches()) {
                return null;
            }
            return System.getProperty("user.dir") + File.separator + uri;
        }

        private void sendError(ChannelHandlerContext channelHandlerContext, HttpResponseStatus status) {
            HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
                    Unpooled.copiedBuffer("Failure:" + status.toString() + "\r\n", CharsetUtil.UTF_8));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");
            channelHandlerContext.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }

    }

    public static void main(String[] args) {
        final boolean SSL = System.getProperty("ssl") != null;
        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = null;
            try {
                ssc = new SelfSignedCertificate();
                sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey())
                        .sslProvider(SslProvider.JDK).build();
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (SSLException e) {
                e.printStackTrace();
            }

        } else {
            sslCtx = null;
        }
        new HttpFileServer().bind();
    }
}