package com.spring.controller;


import com.spring.entity.Author;
import com.spring.entity.AuthorSearch;
import com.spring.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/author")
@Tag(name = "Author Controller")
public class AuthorController {


    @Autowired
    private AuthorService authorService;

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findById(@PathVariable @Min(value = 5) @Max(value = 500) Long id) {
//
//        return ResponseEntity.ok(authorService.findById(id));
//    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author Already Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class))
            }),
            @ApiResponse(responseCode ="400", description = "Invalid Id Supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Author Not Found", content = @Content)

    })
    @Operation(summary = "Find Author by Id")
    public ResponseEntity<?> findById(@Parameter( example = "20",name = "Book Id") @PathVariable Long id) {

        return ResponseEntity.ok(authorService.findById(id));
    }

    @GetMapping("")
    @Operation(summary = "Find All Authors")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @PostMapping("")
    @Operation(summary = "Add New Author")
    public ResponseEntity<?> save(@RequestBody @Valid Author entity){
        return ResponseEntity.ok(authorService.save(entity));
    }


    @PutMapping("")
    @Operation(summary = "Update The Author")
    public ResponseEntity<?> update(@RequestBody @Valid Author entity) {
        return ResponseEntity.ok(authorService.save(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Author By Its Id")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping    ("/spec")
    @Operation(summary = "Find Author By Search")
    public ResponseEntity<?> findByAuthorSpec(@RequestBody AuthorSearch authorSearch) {
        return ResponseEntity.ok(authorService.findByAuthorSpec(authorSearch));
    }



}
