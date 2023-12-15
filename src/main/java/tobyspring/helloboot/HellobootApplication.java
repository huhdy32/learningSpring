package tobyspring.helloboot;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.swing.text.AbstractDocument;
import java.io.IOException;

public class HellobootApplication {
    public static void main(String[] args) {
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        ServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
        WebServer webServer = webServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("Dispatcher-Servlet",
                            new DispatcherServlet(applicationContext)
                    ).addMapping("/*");
                    // 이렇게 디스패쳐 서블릿에 Spring Container를 넘겼다!!!
        });
        // 에러가 나는게 당연하다. 우린 디스패처 서블릿에게 addMapping을 통해 여청을 처리하도록 했지만, 어떤 빈들을 이용해 어떤 작업을 처리할지를 명시하지 않았음
        // 초기에는 XML에 다 명시했지만, 매핑정보를 서블릿의 코드로 URI 매핑을 컨트롤러 빈에 집어넣도록 하는 요즘의 방법!!! 가자~
    }
}

