package learn.lwl.tomcat.server;

import learn.lwl.tomcat.MyHttpRequest;
import learn.lwl.tomcat.MyHttpResponse;
import learn.lwl.tomcat.container.SevletProcessor;
import learn.lwl.tomcat.container.StaticResourceProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created with IDEA
 *
 * @author:wenleili@sohu-inc.com
 * @Date:2019/4/9
 * @Time:10:16
 **/
public class HttpProcessor {
    HttpConnector httpConnector;
    MyHttpRequest request;
    MyHttpResponse response;

    public HttpProcessor(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    public void process(Socket socket) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            request = new MyHttpRequest(inputStream);
            response = new MyHttpResponse(socket.getOutputStream());
            response.setRequest(request);
            response.setHeader("Server", "Pyrmont Servlet Container");
            parseRequest(inputStream, outputStream);
            parseHeaders(inputStream);
            if (request.getUri().startsWith("/servlet/")) {
                SevletProcessor processor = new SevletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void parseHeaders(InputStream inputStream) {
       
    }

    private void parseRequest(InputStream inputStream, OutputStream outputStream) {
    }
}
