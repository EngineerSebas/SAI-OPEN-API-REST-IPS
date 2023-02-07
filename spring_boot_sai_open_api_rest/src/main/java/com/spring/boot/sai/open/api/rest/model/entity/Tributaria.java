package com.spring.boot.sai.open.api.rest.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Tributaria implements Serializable {

    @Id
    private String id_n;
    private String company;
    private String primer_nombre;
    private String segundo_nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private Short tipo_contirbuyente;
    private Short tdoc;
    private static final long serialVersionUID = 1L;
}
