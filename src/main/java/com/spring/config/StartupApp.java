package com.spring.config;

import com.spring.entity.Author;
import com.spring.entity.Book;
import com.spring.service.AuthorService;
import com.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class StartupApp implements CommandLineRunner {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        if (authorService.findAll().isEmpty()) {

            // Add Authors
            Author author = new Author();
//            author.setName("Ahmed");

            Author author2 = new Author();
//            author2.setName("mohamed");

            Author author3 = new Author();
//            author3.setName("Ali");

            authorService.saveAll(Arrays.asList(author, author2, author3));
        }

        if (bookService.findAll().isEmpty()) {
            // Add books
            Book book = new Book();
//            book.setName("Java");
            book.setPrice(300);
//        book.setAuthor(authorService.findById(1L));
            book.setAuthor(authorService.getById(1L));

            Book book2 = new Book();
//            book2.setName("C#");
            book2.setPrice(200);
//        book2.setAuthor(authorService.findById(1L));
            book2.setAuthor(authorService.getById(1L));

            Book book3 = new Book();
//            book3.setName("MySQL");
            book3.setPrice(150);
//        book3.setAuthor(authorService.findById(2L));
            book3.setAuthor(authorService.getById(2L));

            bookService.saveAll(Arrays.asList(book, book2, book3));

        }


    }
}
