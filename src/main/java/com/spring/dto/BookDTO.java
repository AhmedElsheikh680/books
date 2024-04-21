package com.spring.dto;


import com.spring.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookDTO {

    private Long id;

    private String name;

    private double price;
    private Author author;
}
