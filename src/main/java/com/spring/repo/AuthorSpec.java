package com.spring.repo;

import com.spring.entity.Author;
import com.spring.entity.AuthorSearch;
import com.spring.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorSpec implements Specification<Author> {

    public AuthorSpec(AuthorSearch authorSearch) {
        this.authorSearch = authorSearch;
    }

    private AuthorSearch authorSearch;

    @Override
    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        Join<Author, Book> bookJoin = root.join("books", JoinType.LEFT);
        // autherName
        if (authorSearch.getAuthorName() != null && !authorSearch.getAuthorName().isEmpty()) {

             predicates.add(criteriaBuilder.like(root.get("name"), authorSearch.getAuthorName()));
        }

        //email
        if (authorSearch.getEmail() !=null && !authorSearch.getEmail().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("email"), authorSearch.getEmail()));
        }

        //ip Address
        if (authorSearch.getIpAddress() !=null && !authorSearch.getIpAddress().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("ipAddress"),"%"+ authorSearch.getIpAddress() + "%"));
        }

        //bookName
        if (authorSearch.getBookName() != null && !authorSearch.getBookName().isEmpty()) {
            predicates.add(criteriaBuilder.like(bookJoin.get("name"), "%"+ authorSearch.getBookName() + "%"));
        }
        // price Book
        if (authorSearch.getPriceBook() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(bookJoin.get("price"), authorSearch.getPriceBook()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
