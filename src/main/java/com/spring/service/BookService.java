package com.spring.service;

import com.spring.entity.Book;
import com.spring.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow( () -> new NoSuchElementException("Book Not Found With ID: "+ id));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book entity) {
        if (entity.getId() != null) {
            throw new RuntimeException();
        }
        return bookRepository.save(entity);
    }

    public List<Book> saveAll(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    public Book update(Book entity) {
        Book book = findById(entity.getId());
        book.setId(entity.getId());
        return bookRepository.save(book);
    }


    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public int deleteAuthorById(Long id) {
        return bookRepository.deleteAuthorById(id);
    }
}


