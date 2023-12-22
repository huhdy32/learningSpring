package tobyspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {
    @Bean("TomcatWebServerFactory")
    @ConditionalOnMissingBean
    // 항상 사용자 등록 빈이 항상 자동구성정보보다 먼저 빈으로 등록된다.
    // 이를 이용해 만약 사용자가 등록한 동일한 인터페이스(타입)의 빈이 있는지 없는지를 확인하고, 없으면 이를 생성하도록 한다.
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
