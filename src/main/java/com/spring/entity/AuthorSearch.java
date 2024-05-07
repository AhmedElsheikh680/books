package com.spring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorSearch {

    private String authorName;

    private String email;

    private String ipAddress;

    private String bookName;

    private Double priceBook;
}
