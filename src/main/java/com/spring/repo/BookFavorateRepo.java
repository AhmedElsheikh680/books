package com.spring.repo;

import com.spring.base.BaseRepository;
import com.spring.entity.BookFavorate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookFavorateRepo extends BaseRepository<BookFavorate, Long> {

    Optional<BookFavorate> findByAuthorIdAndBookId(Long authorId, Long bookId);

}
