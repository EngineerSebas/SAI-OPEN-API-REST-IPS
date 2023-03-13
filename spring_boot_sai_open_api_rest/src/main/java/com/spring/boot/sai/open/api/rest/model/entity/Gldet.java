package com.spring.boot.sai.open.api.rest.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Entity
public class Gldet {

    private Integer conteo;
    @Id
    private String id_n;
    private String acct;
    private Short e;
    private Short s;
    private String tipo;
    private Integer batch;
    private Integer cuota;
    private String invc;
    private Integer depto;
    private String ccost;
    private String actividad;
    private BigDecimal debit;
    private BigDecimal credit;
    private String period;
    private BigDecimal base;
    private String descripcion;
    private String closing;
    private String concil;
    private String cruce;
    private String destino;
    private Date duedate;
    private String proyecto;
}
