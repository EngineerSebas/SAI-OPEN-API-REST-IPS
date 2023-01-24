package com.spring.boot.sai.open.api.rest.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filter {
    private String field;
    private String operator;
    private String value;

}

