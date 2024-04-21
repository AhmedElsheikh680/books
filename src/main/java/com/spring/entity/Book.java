package com.spring.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;


@NamedEntityGraph(name = "loadAuthor", attributeNodes = @NamedAttributeNode("author"))

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

     @Formula("(select count(*) from books)")
    private long bookCount;

    @Transient
    private double discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;

    public double getDiscount() {
//        return price * .25;
        return discount;
    }

    @PostLoad
    public void calcDiscount() {
        this.setDiscount(price *.25);
    }
}
