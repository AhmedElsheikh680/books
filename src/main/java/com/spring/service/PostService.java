package com.spring.service;

import com.spring.dto.PostDTO;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_POST_URL = "https://jsonplaceholder.typicode.com/posts";

    public ResponseEntity<PostDTO> getPostById(Long id) {

//        RestTemplate restTemplate = new RestTemplate();
        try {
//            ResponseEntity<PostDTO> response = restTemplate.getForEntity(BASE_POST_URL + "/" + id, PostDTO.class);
            ResponseEntity<PostDTO> response = restTemplate.exchange(BASE_POST_URL+"/"+id, HttpMethod.GET,null, PostDTO.class);
            return response;
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<PostDTO>> getAllPosts() {
//        return restTemplate.getForEntity(BASE_POST_URL, List.class).getBody();
        ResponseEntity<PostDTO[]> response =  restTemplate.exchange(BASE_POST_URL, HttpMethod.GET, null, PostDTO[].class);
        return new ResponseEntity<>(Arrays.asList(response.getBody()), response.getStatusCode());
    }

    public ResponseEntity<PostDTO> createPost(PostDTO postDTO) {
        // PostForEntity
//        HttpEntity<PostDTO> request = new HttpEntity<>(postDTO, getHeaders());
//        ResponseEntity<PostDTO> response = restTemplate.postForEntity(BASE_POST_URL, request, PostDTO.class);
//        return response;

        //exchange
        HttpEntity<PostDTO> request  = new HttpEntity<>(postDTO, getHeaders());
        ResponseEntity<PostDTO> response = restTemplate.exchange(BASE_POST_URL, HttpMethod.POST, request, PostDTO.class);
        return response;
    }

    public void updatePost(PostDTO postDTO) {
        HttpEntity<PostDTO> request = new HttpEntity<>(postDTO, getHeaders());
        restTemplate.put(BASE_POST_URL, request);

    }


    public void deletePost(Long id) {
        restTemplate.delete(BASE_POST_URL +"/" +id);
    }


    public void uploadFile(String id, String pathType) {




        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", id);
        map.add("pathType", pathType);
        map.add("file", getTestFile());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, getHeaders());

        String serverUrl = "http://localhost:8082/spring-rest/fileserver/singlefileupload/";

        ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, request, String.class);
    }
    public String getTestFile() {

        return new File("").toString();
    }
    public void getUrlHeaders() {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        HttpHeaders httpHeaders = restTemplate.headForHeaders("");
//    	 assertTrue(httpHeaders.getContentType().includes(MediaType.APPLICATION_JSON));
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout).build();
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json");
        headers.add("accept-language", "en");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return headers;
    }
}
