package tobyspring.helloboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@HelloBootTest
public class HelloRepositoryTest {
    @Autowired
    HelloRepository repository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @BeforeEach
    void init() {
        this.jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
    }
    @Test
    void findHelloTest() {
        assertThat(repository.findHello("Toby")).isNull();
    }

    @Test
    void increaseCount() {
        repository.increaseCount("Toby");
        assertThat(repository.countOf("Toby")).isEqualTo(1);

        repository.increaseCount("Toby");
        assertThat(repository.countOf("Toby")).isEqualTo(2);


    }
}
