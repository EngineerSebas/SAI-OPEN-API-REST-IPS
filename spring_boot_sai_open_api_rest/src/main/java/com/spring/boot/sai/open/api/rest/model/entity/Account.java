package com.spring.boot.sai.open.api.rest.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Account {
    private int id;

    private String name;

    @Id
    private int code;

}
