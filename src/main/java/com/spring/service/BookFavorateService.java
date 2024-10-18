package com.spring.service;

import com.spring.base.BaseService;
import com.spring.entity.BookFavorate;
import com.spring.repo.BookFavorateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookFavorateService extends BaseService<BookFavorate, Long> {


    private final BookFavorateRepo bookFavorateRepo;

    public Optional<BookFavorate> findByAuthorIdAndBookId(Long authorId, Long bookId) {
        return bookFavorateRepo.findByAuthorIdAndBookId(authorId, bookId);
    }
}
