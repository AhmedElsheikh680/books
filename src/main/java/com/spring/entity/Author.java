package com.spring.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.entity.base.BaseEntity;
import com.spring.validator.IpAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")

public class Author extends BaseEntity<Long> {

//    @Pattern(regexp = "^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$")
    @IpAddress(message = "Should Be Enter Valid Ip Address")
    private String ipAddress;

    @Email
    private String email;

    @Formula("(select COUNT(*) from books book where book.author_id = id)")
    private long bookCount;

    @OneToMany(mappedBy = "author")
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();

    // Helper method to add and remove instead of getter and Setter
    public void addBook(Book book) {
        books.add(book);
    }

    public void remove(Book book) {
        books.remove(book);
    }
}
