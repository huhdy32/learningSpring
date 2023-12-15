package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloApiTest {
    @Test
    void helloApi() {
        // http localhost:8080/hello?name=Spring
        // HTTPie 요청을 어떻게 보낼래?

        // 스프링에서 API요청을 하라고 만들어준 클래스 RestTemplate
//        RestTemplate
        // 근데 정상적으로 처리될때는 응답이 제대로 오는데, 에러가 있을땐 예외를 던짐
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> response =
                rest.getForEntity("http://localhost:8080/hello?name={name}"
                        , String.class
                        , "Spring"
                );
        // 응답 검증 단계
        // status Code
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header
        assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN.toString());
        // body
        assertThat(response.getBody()).isEqualTo("Hello Spring");


    }

    @Test
    void failsHelloApi() {

        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> response =
                rest.getForEntity("http://localhost:8080/hello?name="
                        , String.class
                );
        // 응답 검증 단계
        // status Code
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        // 일반적인 예외 시, 서블릿 컨테이너는 응답코드 500으로 매칭해서 리턴합니다. 서버에서 뭔가 에외가 발새했다.



        // header
//        assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN.toString());

        // body
//        assertThat(response.getBody()).isEqualTo("Hello Spring");


    }

}
