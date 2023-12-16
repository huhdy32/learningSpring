package tobyspring.config;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ComponentScan
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableMyAutoConfiguration
public @interface MySpringBootApplication {
}
