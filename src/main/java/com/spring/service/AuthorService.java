package com.spring.service;


import com.spring.entity.Author;
import com.spring.entity.AuthorSearch;
import com.spring.entity.base.BaseService;
import com.spring.exception.DuplicateRecordException;
import com.spring.repo.AuthorRepo;
import com.spring.repo.AuthorSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthorService extends BaseService<Author, Long> {

    Logger logger  = LoggerFactory.getLogger(AuthorService.class);
//private static final Logger logger = LogManager.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public Author save(Author author) {

        if (!author.getEmail().isEmpty() && author.getEmail() !=null) {
//           Optional<Author> entity =  findByEmail(author.getEmail());
           CompletableFuture<Author> entity =  findByEmail(author.getEmail());
           logger.info("Author Name is {} and email is " + author.getName(), author.getEmail());
            System.out.println("Email: "+ author.getEmail());
//           if (entity.isPresent()) {
           if (entity.isDone()) {
//               throw new DuplicateRecordException("This Email already exist");
               logger.error("This Email already exist!!!!!!!!!!");
               throw new DuplicateRecordException();
           }
        }
        return super.save(author);
    }

    @Override
    public Author update(Author entity) {

       Author author =  findById(entity.getId());
       author.setName(entity.getName());
        return super.update(author);
    }

    public List<Author> findByAuthorSpec(AuthorSearch authorSearch) {
        AuthorSpec authorSpec = new AuthorSpec(authorSearch);
        return authorRepo.findAll(authorSpec);
    }

//    public Optional<Author> findByEmail(String email) {
//        return authorRepo.findByEmail(email);
//    }

    @Async
    public CompletableFuture<Author> findByEmail(String email) {
        return CompletableFuture.completedFuture(authorRepo.findByEmail(email).get());
    }
}
