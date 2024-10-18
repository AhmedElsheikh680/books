package com.spring.mapper.qualifier;


import com.spring.entity.Book;
import com.spring.entity.BookFavorate;
import com.spring.service.BookFavorateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@BookFavorateQualifier
@Component
@RequiredArgsConstructor
public class BookFavorateQualifierImpl {

    private final BookFavorateService bookFavorateService;

    @BookFavorateQualifier.BookFavorateFlagMethodQualifier
    public Boolean getIsFavorate(Book entity) {
        Optional<BookFavorate> bookFavorate = bookFavorateService.findByAuthorIdAndBookId(entity.getId(), entity.getAuthor().getId());
        return bookFavorate.isPresent() ? true : false;
//        return bookFavorateService.findByAuthorIdAndBookId(entity.getId(), entity.getAuthor().getId()).isPresent();

    }
}
