package com.spring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDTO{

    private String name;
    private String ipAddress;
    private String email;
    private long bookCount;
    private String imagePath;
}
