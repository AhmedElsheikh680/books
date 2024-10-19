package com.spring.controller;

import com.spring.entity.Author;
import com.spring.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthorControllerTest {

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthorService authorService;

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
}
