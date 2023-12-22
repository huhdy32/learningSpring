package tobyspring.helloboot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


//// 빈 사이의 의존관계주입이 필요 없다면 proxy = false로 설정하자.
//@Configuration(proxyBeanMethods = false)
//public class WebServerConfiguration {
//    @Bean
//    public ServletWebServerFactory customerWebServerFactory() {
//        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
//        // 스프링 부트가 왜 굳이 서블릿 컨테이너를 팩토리로 구현해놨을까?
//        // 서블릿 컨테이너를 띄우기 전에 여러 편리한 메소드를 제공하기 윟마이다.
//        // 어떤게 있을까요??
//        tomcatServletWebServerFactory.setPort(9090);
//        return tomcatServletWebServerFactory;
//
//        // 어 이상태에서 실행하니까 빈으로 등록된 ServletWebServerFactory가 두개가 있어서 에러!!
//
//    }
//}
