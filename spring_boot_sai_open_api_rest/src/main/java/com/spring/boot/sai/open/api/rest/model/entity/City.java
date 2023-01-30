package com.spring.boot.sai.open.api.rest.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class City {

    @Id
    private int id;
    private String name;

    private String code;

    private int state_id;

    private int country_id;
}
