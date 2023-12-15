package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {
        // 컨트롤러의 주요 역할 중 하나는 사용자 요청을 검증하는 것임
        return helloService.sayHello(name);
    }
}
