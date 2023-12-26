package tobyspring.helloboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
public class HellobootApplication {
//    @Bean
//    // 컨테이너의 기능을 활요해서 테스트하고싶은 경우 유용함 기억하는게 좋아
//    ApplicationRunner applicationRunner(Environment environment) {
//        return args -> {
//            String property = environment.getProperty("my.name");
//            System.out.println("my.name : " + property);
//        };
//    }
    public static void main(String... args) {
        SpringApplication.run(HellobootApplication.class, args);
    }

}

