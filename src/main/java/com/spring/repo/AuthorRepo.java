package com.spring.repo;

import com.spring.entity.Author;
import com.spring.entity.base.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AuthorRepo extends BaseRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    Optional<Author>findByEmail(String email);

    @Override
    @EntityGraph(attributePaths = "books")
    List<Author> findAll();

    @Override
    @EntityGraph(attributePaths = "books")
    Optional<Author> findById(Long aLong);
}
