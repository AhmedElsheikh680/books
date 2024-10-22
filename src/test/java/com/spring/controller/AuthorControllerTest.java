package com.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.entity.Author;
import com.spring.service.AuthorService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Log4j2
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthorControllerTest {

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthorService authorService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    public void initMethod() {
        log.info("Init Method>>>>>>>>>>>>>>>>>>>>>.");
        Mockito.when(authorService.save(Mockito.any(Author.class))).thenReturn(new Author("ahmed", "192.168.1.1", "a@a.com", 1, null));
    }

//    @Test
//    void findByEmailNotFoundTest() {
//        String email = "a@a.com";
//        Mockito.when(authorService.findByEmail(Mockito.anyString())).thenReturn(Optional.of(new Author("ahmed", "192.168.1.1", "a@a.com", 1, null)));
//        ResponseEntity<AuthorDTO> responseEntity = restTemplate.getForEntity("http://localhost:1996/author/email/" + email, AuthorDTO.class);
//        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        org.assertj.core.api.Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//    }

    @Test
    void findByEmailNotFoundTest () throws Exception {
       String email = "a@a.com";
        Mockito.when(authorService.findByEmail(Mockito.anyString())).thenReturn(Optional.of(new Author("ahmed", "192.168.1.1", "a@a.com", 1, null)));
        mockMvc.perform(get("/author/email/{email}", email)
                .contentType("application/json"))
//                .param("SendMelcomeMail", true)
//                .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isOk());
    }

    @Test
    void addAuthorTest () throws Exception {
        mockMvc.perform(post("/author")
                .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new Author("ahmed", "192.168.1.1", "a@a.com", 1, null))))
                .andExpect(status().isOk());
    }

    @AfterAll
    void destroyMethod() {
        log.info("Destroy Method>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
