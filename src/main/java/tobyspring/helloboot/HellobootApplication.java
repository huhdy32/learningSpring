package tobyspring.helloboot;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

import javax.swing.text.AbstractDocument;
import java.io.IOException;

public class HellobootApplication {
    public static void main(String[] args) {
        // 스프링 컨테이너를 만들어보자
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();
        // 빈 등록



        // 아무거도 안되는데? 스프링을 실행시켜 보자

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("Front Controller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 인증, 보안, 다국어, 공통기능 등등 구현
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");
//                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);;
                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.setStatus(HttpStatus.OK.value());
                        resp.getWriter().println(applicationContext.getBean(HelloController.class).hello(req.getParameter("name")));
                    }else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                        resp.getWriter().println("NOT MATCH");
                    }
                }
            }).addMapping("/*");
        });
        // 요놈을 이용해서 서블릿컨텍스트를 넣어서 컨테이너를 구성하는구나
        webServer.start();
        // 서블릿 컨테이너를 구성했음 레전드노

        // 서블릿은 서블릿 컨테이너에 여러가지가 들어갈수 있음으로,
        // 따라서 컨테이너는 요청을 적절한 서블렛에 이 요청을 처리하도록 전달함

    }
}

