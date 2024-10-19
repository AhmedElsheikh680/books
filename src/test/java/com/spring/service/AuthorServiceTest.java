package com.spring.service;

import com.spring.entity.Author;
import com.spring.repo.AuthorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class AuthorServiceTest {

    @MockBean
    AuthorRepo authorRepo;
    @Autowired
    private AuthorService authorService;


    @Test
    void findByEmailFoundTest() {
        Mockito.when(authorRepo.findByEmail(Mockito.anyString())).thenReturn(Optional.of(new Author("ahmed", "192.168.1.1", "a@a.com", 1, null)));
        Optional<Author> author = authorService.findByEmail("ahmed@mohamed.com");
        Assertions.assertEquals(true, author.isPresent());
        Assertions.assertEquals("a@a.com", author.get().getEmail());
    }
}


