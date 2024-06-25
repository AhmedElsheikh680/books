package com.spring.controller;


import com.spring.dto.PostDTO;
import com.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping("")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.createPost(postDTO));
    }
}
