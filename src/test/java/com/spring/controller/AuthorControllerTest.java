package com.spring.controller;

import com.spring.dto.AuthorDTO;
import com.spring.entity.Author;
import com.spring.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerTest {

    @Autowired
    RestTemplate restTemplate;

    @MockBean
    AuthorService authorService;

    @Test
    void findByEmailNotFoundTest() {
        String email = "a@a.com";
        Mockito.when(authorService.findByEmail(Mockito.anyString())).thenReturn(Optional.of(new Author("ahmed", "192.168.1.1", "a@a.com", 1, null)));
        ResponseEntity<AuthorDTO> responseEntity = restTemplate.getForEntity("http://localhost:1996/author/email/" + email, AuthorDTO.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        org.assertj.core.api.Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
