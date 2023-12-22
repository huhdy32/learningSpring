package tobyspring.helloboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import tobyspring.config.MySpringBootApplication;
import org.springframework.boot.SpringApplication;


//@Configuration
//@ComponentScan
@MySpringBootApplication
public class HellobootApplication {
    public static void main(String... args) {
//        MySpringApplication.run(HellobootApplication.class, args);
        SpringApplication.run(HellobootApplication.class, args);
    }
}

