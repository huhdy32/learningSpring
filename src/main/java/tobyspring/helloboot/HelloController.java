package tobyspring.helloboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/hello")
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

/*   디스패처 서블릿은 빈을 다~ 뒤져.
    이중에 매핑 정보를 가지고 있는 빈 객체를 찾아서 매핑 테이블이란걸 만들어.
    근데 빈이 나중에는 몇천개가 될 수도 있는데 이는 오버헤드를 유발하겠는데?
    아. 컨트롤러인 빈은 따로 명시를 해 두자. → @RequestMapping*/
    @GetMapping
    @ResponseBody
    public String hello(String name) {
        // 컨트롤러의 주요 역할 중 하나는 사용자 요청을 검증하는 것임
        return helloService.sayHello(name);
    }
    /*디스패처 서블릿은 함수의 리턴값이 어떤건지를 기본값으로 확인한다.
    따라서 우린 클래스의 메소드가 리턴하는 값이
    뷰의이름인지 혹은 그냥 TextPlain인지를 구분할 수 있도록 해야한다.*/
}
