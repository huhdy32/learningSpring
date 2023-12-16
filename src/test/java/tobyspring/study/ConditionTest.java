package tobyspring.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionTest {
    @Test
    void conditional() {
        new ApplicationContextRunner()
                .withUserConfiguration(Config1.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(MyBean.class);
                    assertThat(context).hasSingleBean(Config1.class);
                });
        new ApplicationContextRunner()
                .withUserConfiguration(Config2.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(MyBean.class);
                    assertThat(context).doesNotHaveBean(Config2.class);
                });
        
/*

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Config1.class);
        applicationContext.refresh();

        assertThat(applicationContext.getBean(MyBean.class));

        //false
        AnnotationConfigApplicationContext applicationContext2 = new AnnotationConfigApplicationContext();
        applicationContext2.register(Config2.class);
        applicationContext2.refresh();

        assertThat(applicationContext2.getBean(MyBean.class));
        // Test 전용 어플리케이션
*/


    }


    @Configuration
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean mybean() {
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean mybean() {
            return new MyBean();
        }
    }

    static class MyBean {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

    private static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            return (boolean) annotationAttributes.get("value");
        }
    }
}
