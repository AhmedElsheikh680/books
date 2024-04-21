package com.spring.controller;


import com.spring.entity.Author;
import com.spring.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {


    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        return ResponseEntity.ok(authorService.findById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Author entity){
        return ResponseEntity.ok(authorService.save(entity));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Author entity) {
        return ResponseEntity.ok(authorService.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
