package com.spring.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@NamedEntityGraph(name = "loadAuthor", attributeNodes = @NamedAttributeNode("author"))

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book extends BaseEntity<Long> {

    @Min(value = 5)
    @Max(value = 500)
    private double price;

     @Formula("(select count(*) from books)")
    private long bookCount;

    @Transient
    private double discount;



//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
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
