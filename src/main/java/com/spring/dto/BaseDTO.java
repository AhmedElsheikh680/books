package com.spring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseDTO <ID> {

    private ID id;
    private String name;
}
