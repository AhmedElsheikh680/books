package com.spring.entity;

import com.spring.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class BookFavorate extends BaseEntity<Long> {

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author author;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "book_id")
    private Book book;
}
