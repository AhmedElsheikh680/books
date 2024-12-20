package com.spring.entity;


import com.spring.base.BaseEntity;
import com.spring.validator.IpAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
@Schema(name = "Author Schema")
public class Author extends BaseEntity<Long> {

    private String fullName;

//    @Pattern(regexp = "^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$")
//    @IpAddress("message = "Should Be Enter Valid Ip Address")
    @IpAddress(message = "{validation.constraints.ip-address.message}")
    private String ipAddress;

//    @Email
    @Email(message = "{enter.valid.email}")
    private String email;

    @Formula("(select COUNT(*) from books book where book.author_id = id)")
    private long bookCount;
//
//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Book> books = new ArrayList<>();

    private String imagePath;

    // Helper method to add and remove instead of getter and Setter
//    public void addBook(Book book) {
//        books.add(book);
//    }
//
//    public void remove(Book book) {
//        books.remove(book);
//    }
}
