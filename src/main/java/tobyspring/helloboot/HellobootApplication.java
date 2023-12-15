package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


@Configuration
@ComponentScan
public class HellobootApplication {
    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        System.out.println("WEBSERVER 등록");
        return new TomcatServletWebServerFactory();
    }
    @Bean
    public DispatcherServlet dispatcherServlet() {
        System.out.println("DISPATCHER 등록");
        return new DispatcherServlet();
    }
    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
//                dispatcherServlet.setApplicationContext(this);
//                야 이거 컨텍스트도 지정안해줬는데 어떻게 했냐?
//                범인은 스프링이야. 스프링이 씨발 그냥 집어넣었데.
//                이건 빈의 생명주기 ( 빈의 라이프 사이클 을 볼 줄 알아야해 )

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("Dispatcher Servlet",
                                    dispatcherServlet)
                            .addMapping("/*");

                });
                webServer.start();
            }
        };
        System.out.println(100);
        applicationContext.register(HellobootApplication.class);
        System.out.println(200);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean(DispatcherServlet.class));
        System.out.println(300);
    }


}

