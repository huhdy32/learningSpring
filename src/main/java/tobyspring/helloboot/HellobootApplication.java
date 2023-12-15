package tobyspring.helloboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.swing.text.AbstractDocument;
import java.io.IOException;

public class HellobootApplication {
    public static void main(String[] args) {
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();
                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext ->
                        servletContext.addServlet("Dispatcher Servlet",
                                        new DispatcherServlet(this)
                                ).addMapping("/*")
                );
                webServer.start();
            }
        };
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();
        //이제 서블릿 컨테이너의 서블릿에 매핑정보를 넣는게 아니라, 스프링 컨테이너에 존재하는 컨트롤러에 매핑정보를 등록해버리자!!
        // 이를테면 GetMapping, PostMapping 등의 메소드가 존재함 또한
    }
}

