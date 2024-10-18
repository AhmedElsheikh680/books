package com.spring.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder

public class BookDTO {

    private Long id;

//    @NotNull
//    @NotEmpty
//    @NotBlank
    private String name;

    @Min(value = 5)
    @Max(value = 500)
    private double price;

//    @NotEmpty
    @NotNull
    private AuthorDTO authorDTO;
    private String authorName;
    private String authorEmail;

    private Boolean isFavorate;
}
