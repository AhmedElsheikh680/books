package com.spring.controller;


import com.spring.dto.AuthorDTO;
import com.spring.entity.Author;
import com.spring.entity.AuthorSearch;
import com.spring.mapper.AuthorMapper;
import com.spring.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/author")
@Tag(name = "Author Controller5")
@RequiredArgsConstructor
@CrossOrigin(value = {"http://localhost:4200"})
@Log4j2
public class AuthorController {


    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);
//    @Autowired
    private  final AuthorService authorService;
    private final AuthorMapper authorMapper;
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
        if (id ==null) {
            log.error("Invalid ID supplied!!!!");
            return ResponseEntity.badRequest().body("Invalid ID Supplied !!!!!!!!!!!!");
        }
        Author author = authorService.findById(id);
//        AuthorDTO authorDTO  = AuthorDTO.builder()
//                .email(author.getEmail())
//                .bookCount(author.getBookCount())
//                .ipAddress(author.getIpAddress())
//                .imagePath(author.getImagePath())
//                .build();
//            AuthorDTO authorDTO = authorMapper.mapToDTO(author);
            AuthorDTO authorDTO = authorMapper.map(author);
        return ResponseEntity.ok(authorDTO);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
//       Author author =  authorService.findByEmail(email).get();
       Optional<Author> author =  authorService.findByEmail(email);
       if (author.isPresent()) {

           AuthorDTO authorDTO = authorMapper.map(author.get());
           return ResponseEntity.ok(authorDTO);
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Author with email " + email + " Not found");
       }

    }

    @GetMapping("")
    @Operation(summary = "Find All Authors")
    public ResponseEntity<?> findAll() {

        List<Author> authors  = authorService.findAll();
        List<AuthorDTO> dtos = authorMapper.map(authors);
        dtos.get(0).setName(authors.get(0).getFullName());
        log.info("DTOS: "+ dtos.toString());
        log.info("DTOS: "+ dtos.get(0).getName());
        log.info("Updated name: "+dtos.get(0).getName());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("")
    @Operation(summary = "Add New Author")
//    public ResponseEntity<?> save(@RequestBody @Valid Author entity){
//        return ResponseEntity.ok(authorService.save(entity));
//    }
    public ResponseEntity<?> save(@RequestBody @Valid AuthorDTO authorDTO){
//        Author author = authorMapper.mapToEntity(authorDTO);
        Author author = authorMapper.unMap(authorDTO);
       Author entity =  authorService.save(author);
      AuthorDTO dto = authorMapper.map(entity);
        return ResponseEntity.ok(dto);
    }


    @PutMapping("")
    @Operation(summary = "Update The Author")
//    public ResponseEntity<?> update(@RequestBody @Valid Author entity) {
//        return ResponseEntity.ok(authorService.save(entity));
//    }
    public ResponseEntity<?> update(@RequestBody @Valid AuthorDTO authorDTO) {
        Author entity= authorService.findById(authorDTO.getId());
        Author author = authorMapper.unMap(authorDTO, entity);
        Author returnedEntity = authorService.update(author);
        AuthorDTO dto = authorMapper.map(returnedEntity);
        return ResponseEntity.ok(dto);
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
