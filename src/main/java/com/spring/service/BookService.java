package com.spring.service;

import com.spring.entity.Book;
import com.spring.base.BaseService;
import com.spring.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends BaseService<Book, Long> {

    @Autowired
    private BookRepository bookRepository;


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
        return super.update(book);
    }



    public int deleteAuthorById(Long id) {
        return bookRepository.deleteAuthorById(id);
    }
}


