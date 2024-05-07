package com.spring.service;


import com.spring.entity.Author;
import com.spring.entity.AuthorSearch;
import com.spring.entity.base.BaseService;
import com.spring.repo.AuthorRepo;
import com.spring.repo.AuthorSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService extends BaseService<Author, Long> {

    @Autowired
    private AuthorRepo authorRepo;

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
}
