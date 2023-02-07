package com.spring.boot.sai.open.api.rest.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Shipto implements Serializable {

    @Id
    private String id_n;
    private String company;
    private String primer_nombre;
    private String segundo_nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String company_extendido;
    private String addr1;
    private String phone1;
    private String phone2;
    private String email;
    private BigDecimal creditlmt;
    private String comment1;
    private String city;

    private static final long serialVersionUID = 1L;
}
