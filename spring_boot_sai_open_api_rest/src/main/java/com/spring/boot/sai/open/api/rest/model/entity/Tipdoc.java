package com.spring.boot.sai.open.api.rest.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Setter
@Getter
@Entity
public class Tipdoc implements Serializable {
    @Id
    private String clase;

    private static final long serialVersionUID = 1L;
}
