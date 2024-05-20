package com.spring.service;


import com.spring.entity.Author;
import com.spring.entity.AuthorSearch;
import com.spring.entity.base.BaseService;
import com.spring.exception.DuplicateRecordException;
import com.spring.repo.AuthorRepo;
import com.spring.repo.AuthorSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService extends BaseService<Author, Long> {

    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public Author save(Author author) {

        if (!author.getEmail().isEmpty() && author.getEmail() !=null) {
           Optional<Author> entity =  findByEmail(author.getEmail());
           if (entity.isPresent()) {
//               throw new DuplicateRecordException("This Email already exist");
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

    public Optional<Author> findByEmail(String email) {
        return authorRepo.findByEmail(email);
    }
}
