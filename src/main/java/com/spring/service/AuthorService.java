package com.spring.service;


import com.spring.entity.Author;
import com.spring.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    public Author findById(Long id) {
        return authorRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Author Not Found With ID: "+ id));
    }

    public List<Author> findAll() {
        return authorRepo.findAll();
    }

    public Author save(Author author) {
        if (author.getId() != null) {
            throw new RuntimeException();
        }
        return authorRepo.save(author);
    }

    public Author update(Author entity) {
       Author author =  findById(entity.getId());
       author.setId(entity.getId());
       return authorRepo.save(author);
    }

    public void deleteById(Long id) {
        authorRepo.deleteById(id);
    }












}
