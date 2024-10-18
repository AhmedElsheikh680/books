package com.spring.service;


import com.spring.entity.Author;
import com.spring.entity.AuthorSearch;
import com.spring.base.BaseService;
import com.spring.exception.DuplicateRecordException;
import com.spring.repo.AuthorRepo;
import com.spring.repo.AuthorSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService extends BaseService<Author, Long> {

    Logger logger  = LoggerFactory.getLogger(AuthorService.class);
//private static final Logger logger = LogManager.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepo authorRepo;

    @Override
//    @Cacheable(value = "findAllAuthorCache", key = "#root.methodName")
    @Cacheable(value = "author", key = "#root.methodName")
    public List<Author> findAll() {
        return super.findAll();
    }

    @Override
//    @Cacheable(value = "findByIdCache", key = "#aLong")
    @Cacheable(value = "author", key = "#aLong")
//    @CachePut(value ="author", key = "#aLong")
    public Author findById(Long aLong) {
        if (aLong == null) {
            logger.info("The Id parameter must not be null................");
        }
        return super.findById(aLong);
    }

    @Override
//    @CacheEvict(value = {"findAllAuthorCache,findByIdCache, findAuthorByEmail"}, key = "#root.methodName", allEntries=true)
    @CacheEvict(value = {"author"}, key = "#root.methodName", allEntries=true)
    public Author save(Author author) {

        if (!author.getEmail().isEmpty() && author.getEmail() !=null) {
           Optional<Author> entity =  findByEmail(author.getEmail());
//           CompletableFuture<Author> entity =  findByEmail(author.getEmail());
           logger.info("Author Name is {} and email is " + author.getFullName(), author.getEmail());
            System.out.println("Email: "+ author.getEmail());
           if (entity.isPresent()) {
//           if (entity.isDone()) {
//               throw new DuplicateRecordException("This Email already exist");
               logger.error("This Email already exist!!!!!!!!!!");
               throw new DuplicateRecordException();
           }
        }
        return super.save(author);
    }

    @Override
//    @CacheEvict(value = {"findAllAuthorCache,findByIdCache, findAuthorByEmail"}, key = "#root.methodName", allEntries = true)
//    @CacheEvict(value = {"author"}, key = "#root.methodName", allEntries = true)
  @Caching(evict = {@CacheEvict("author"), @CacheEvict(value = "author", key="#entity.id")})
    public Author update(Author entity) {

//       Author author =  findById(entity.getId());
//       author.setFullName(entity.getFullName());
        return super.update(entity);
    }

    public List<Author> findByAuthorSpec(AuthorSearch authorSearch) {
        AuthorSpec authorSpec = new AuthorSpec(authorSearch);
        return authorRepo.findAll(authorSpec);
    }

    @Cacheable(value = "findAuthorByEmail", key = "#email")
    public Optional<Author> findByEmail(String email) {
        return authorRepo.findByEmail(email);
    }

//    @Async(value = "threadPoolTaskExecutor")
//    public CompletableFuture<Author> findByEmail(String email) {
//        return CompletableFuture.completedFuture(authorRepo.findByEmail(email).get());
//    }
}
