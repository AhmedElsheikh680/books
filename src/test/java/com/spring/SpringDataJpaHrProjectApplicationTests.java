package com.spring;

import com.spring.entity.Author;
import com.spring.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
//@RequiredArgsConstructor
class SpringDataJpaHrProjectApplicationTests {
@Autowired
    private  AuthorService authorService;

//    @Test
//    void contextLoads() {
//    }
    @Test
    void findByEmailNotFoundTest() {
        Optional<Author> author = authorService.findByEmail("ahmed@Mohamed.com");
        Assertions.assertEquals(false, author.isPresent());

    }

    @Test
    void findbyEmailFoundTest() {
        Optional<Author> author = authorService.findByEmail("a@a.com");
        Assertions.assertEquals(true, author.isPresent());
        Assertions.assertEquals("a@a.com", author.get().getEmail());
    }

}
