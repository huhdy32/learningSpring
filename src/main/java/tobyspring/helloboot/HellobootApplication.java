package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// 스프링 컨테이너가 이 빈이 빈을 만들어내는 팩토리 메소드를 가지는 객체구나임을 인식 시켜야 함
@Configuration
public class HellobootApplication {
    public static void main(String[] args) {
        // 지금 까지 쓰던 요놈은 자바코드로 작성된 Configuration 코드를 읽을 수 없음 따라서 수정
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();
                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext ->
                        servletContext.addServlet("Dispatcher Servlet",
                                new DispatcherServlet(this))
                                .addMapping("/*")
                );
                webServer.start();
            }
        };
        applicationContext.register(HellobootApplication.class);
        applicationContext.refresh();
         /*좋아, 하지만 이번엔 복잡한 객체를 위한 팩토리 메소드를 만들자. 팩토리 메소드를 통해 구현 해보자!!

         어차피 우리가 쓸 팩토리 메소드는 Spring Container에서 관리해. 지가 팩토리 메소드임을 확인하고 실행시킬것임
         AnnotationConfigApplicationContext 컨테이너는 요놈 지원 안해. 팩토리메소드로만 받는다.
         하지만 빈을 구성할 팩토리 메소드를 가진 클래스를 등록 해줘야 한다.
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();*/
    }

    //

    @Bean
    public HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    // 여기서 우리 리턴타입을 빈을 주입받을 객체에서 기대하는 값으로 정해주도록 하자.!!!
    // <인터페이스 타입으로 리턴하도록 하세요>
    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }
}

