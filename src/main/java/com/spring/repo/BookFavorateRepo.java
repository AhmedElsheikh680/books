package com.spring.repo;

import com.spring.entity.BookFavorate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookFavorateRepo extends JpaRepository<BookFavorate, Long> {

    Optional<BookFavorate> findByAuthorIdAndBookId(Long authorId, Long bookId);

}
