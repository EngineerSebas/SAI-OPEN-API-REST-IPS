package com.spring.boot.sai.open.api.rest.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Cust implements Serializable {
    @Id
    private String id_n;
    private String company;
    private String company_extendido;
    private String addr1;
    private String phone1;
    private String phone2;
    private String pais;
    private String medico;
    private BigDecimal creditlmt;
    private String cliente;
    private String proveedor;
    private String city;
    private String nit;
    private String id_tipocartera;
    private String regimen;
    private String inactivo;
    private short zona;
    private short idvend;


    private static final long serialVersionUID = 1L;
}
