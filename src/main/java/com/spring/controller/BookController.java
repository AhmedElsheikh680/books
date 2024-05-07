package com.spring.controller;


import com.spring.dto.BookDTO;
import com.spring.entity.Book;
import com.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Book book = bookService.findById(id);
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setAuthor(book.getAuthor());

        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody @Valid BookDTO dto) {

        Book book = new Book();
        book.setName(dto.getName());
        book.setPrice(dto.getPrice());
        book.setAuthor(dto.getAuthor());
        return ResponseEntity.ok(bookService.save(book));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody @Valid Book entity) {
        return ResponseEntity.ok(bookService.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/auhtor/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.deleteAuthorById(id));
    }







}
