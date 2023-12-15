package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    void helloController() {
        HelloController helloController = new HelloController(new HelloService() {
            @Override
            public String sayHello(String name) {
                return name;
            }
        });

        String test = helloController.hello("Test");
        Assertions.assertThat(test).isEqualTo("Test");
        // 의존관계가 존재하는 객체를 테스트 할려면 어떻게 해여할까?
        // 스프링 컨테이너가 없는 환경에서 이를 테스트 하기위해선 어떻게 해야하나?
        // 간단하게 헬로 서비스가 호출되는 구간을 생성하자
        // 이로써 의존관계의 객체를 독립시킨다.

        // 또한, 에외사항에 대해서도 검증해야한다.
    }

    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController(new HelloService() {
            @Override
            public String sayHello(String name) {
                return name;
            }
        });
        Assertions.assertThatThrownBy(() -> {
            String test = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> {
            String test = helloController.hello("   ");
        }).isInstanceOf(IllegalArgumentException.class);
        // 예외의 종류를 분명히 구분하자
    }
}
