package tobyspring.config.autoconfig;

import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {

    // 이 왜 안되노??
    // 왜 그대로 들어가노?
    // 그래 이상하다 했다. 어떤 경로, 어떻게 읽을지를 정하지도 않았는데 ...
    // 만약 이렇게 플레이스 홀더를 놔뒀을때 프로퍼티 소스를 다 뒤졌을때 이런종류가 없으면 어떻게 될까?

    @Bean("TomcatWebServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(ServerProperties serverProperties) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

        factory.setContextPath(serverProperties.getContextPath());
        factory.setPort(serverProperties.getPort());

        return factory;
    }

}
