package learn.lwl.tomcat.container;

import learn.lwl.tomcat.MyHttpRequest;
import learn.lwl.tomcat.MyHttpResponse;

/**
 * Created with IDEA
 *
 * @author:wenleili@sohu-inc.com
 * @Date:2019/4/9
 * @Time:14:43
 **/
public class StaticResourceProcessor {
    public void process(MyHttpRequest request, MyHttpResponse response) {
        response.sendStaticResource();

    }
}
