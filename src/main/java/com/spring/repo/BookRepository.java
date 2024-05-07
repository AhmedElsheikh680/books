package com.spring.repo;

import com.spring.entity.Book;
import com.spring.entity.base.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends BaseRepository<Book, Long> {

//    @Override
//    @EntityGraph(attributePaths = {"author"})
//    Optional<Book> findById(Long id);
//
//    @Override
//    @EntityGraph(attributePaths = {"author"})
//    List<Book> findAll();


    @Override
    @EntityGraph(value = "loadAuthor")
    Optional<Book> findById(Long aLong);

    @Override
    @EntityGraph(value = "loadAuthor")
    List<Book> findAll();


    @Transactional
    @Modifying()
    @Query("delete from Book where author.id= :id")

   int deleteAuthorById(Long id);



















}
