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
public class Cust implements Serializable {

    private String company;
    @Id
    private String id_n;
    private String company_extendidox;
    private String addr1;
    private String phone1;
    private String phone2;
    private String pais;
    private String medico;
    private BigDecimal creditlmt;
    private String cliente;
    private String proveedor;
    private String city;

    private static final long serialVersionUID = 1L;
}
