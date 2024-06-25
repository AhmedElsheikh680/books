package com.spring.service;

import com.spring.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_POST_URL = "https://jsonplaceholder.typicode.com/posts";

    public ResponseEntity<PostDTO> getPostById(Long id) {

//        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<PostDTO> response = restTemplate.getForEntity(BASE_POST_URL + "/" + id, PostDTO.class);
            return response;
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public List<PostDTO> getAllPosts() {
        return restTemplate.getForEntity(BASE_POST_URL, List.class).getBody();

    }

    public ResponseEntity<PostDTO> createPost(PostDTO postDTO) {
        HttpEntity<PostDTO> request = new HttpEntity<>(postDTO, getHeaders());
        ResponseEntity<PostDTO> response = restTemplate.postForEntity(BASE_POST_URL, request, PostDTO.class);
        return response;
    }

    public void updatePost(PostDTO postDTO) {
        HttpEntity<PostDTO> request = new HttpEntity<>(postDTO, getHeaders());
        restTemplate.put(BASE_POST_URL, request);

    }


    public void deletePost(Long id) {
        restTemplate.delete(BASE_POST_URL +"/" +id);
    }
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json");
        headers.add("accept-language", "en");
        return headers;
    }
}
