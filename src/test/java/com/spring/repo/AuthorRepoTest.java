package com.spring.repo;

import com.spring.entity.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class AuthorRepoTest {

    @Autowired
    private AuthorRepo authorRepo;

    @Test
    void findByEmailNotFound() {
       Optional<Author> author =  authorRepo.findByEmail("ahmed@mohamed.com");
        Assertions.assertEquals(false, author.isPresent());

    }
}
