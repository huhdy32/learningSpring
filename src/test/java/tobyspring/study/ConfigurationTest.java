package tobyspring.study;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {

    // Configuration 클래스의 특징은 내부 메소드로 빈을 생성하는 팩토리 메소드를 다수 가지고 있다는 것이다.
    // Bean1 Bean2 라는 빈이 있다. 이 둘다 동일한 오브젝트를 의존한다고 하자.
    // 빈들은 특별한 경우가 아니라면 싱글톤으로 등록된다고 헀다.
    // <<< 근데 >>>     
    // 우리가 자바코드로 팩토리 메소드를 만들어서 각 빈을 생성하여 의존관계를 주입하면 이 룰을 지킬 수 없음 !!!!!!!
    // @Configuration 클래스에 프록시 빈 메소드가 트루란 말은 처음에 스프링 컨테이너가 시작할떄 이런 프록시 클래스를 생송하고 이를 @Configuration 클래스로 사용한다.
    @Test
    void test() {
        MyConfig myConfig = new MyConfig();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyConfig.class);
        applicationContext.refresh();

        Bean1 bean1 = applicationContext.getBean(Bean1.class);
        Bean2 bean2 = applicationContext.getBean(Bean2.class);

        assertThat(bean1.common).isEqualTo(bean2.common);
        // 야 스프링 컨테이너는 이걸 어떻게 똑같은걸로 관리하노??
        // 스프링 컨테이너에서 생성되는 @Configuration 어노테이션이 붙은 클래스가 발생시키는 마법
    }

    @Test
    void testProxy() {
    }

    @Test
    void testMyconfig2() {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        applicationContext.register(MyConfig2.class);
        applicationContext.register(MyConfig.class);
        applicationContext.refresh();
        Bean1 bean1 = applicationContext.getBean(Bean1.class);
        Bean2 bean2 = applicationContext.getBean(Bean2.class);

        assertThat(bean1.common).isEqualTo(bean2.common);
    }

    @Configuration
    static class MyConfig {
        @Bean
        static Common common() {
            return new Common();
        }
        @Bean
        static Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        static Bean2 bean2() {
            return new Bean2(common());
        }
    }

//    @Configuration
//    static class MyConfig2 {
//        @Bean
//        Bean1 offerBean1() {
//            return new Bean1(MyConfig.common());
//        }
//    }

    static class Bean1 {
        Common common;

        public Bean1(Common common) {
            this.common = common;
        }

    }

    static class Bean2 {
        Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }


    private static class Common {
    }
}
