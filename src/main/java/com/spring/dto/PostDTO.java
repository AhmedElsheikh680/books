package com.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

    private static final long serialVersionUID = -973790642300432737L;

    private Long id;
    private Long userId;
    private String title;
    private String body;


}
