package com.spring.repo;

import com.spring.entity.Author;
import com.spring.entity.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthorRepo extends BaseRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    Optional<Author>findByEmail(String email);
}
