package com.spring.dto;


import com.spring.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookDTO {

    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @Min(value = 5)
    @Max(value = 500)
    private double price;

    @NotEmpty
    private Author author;
}
