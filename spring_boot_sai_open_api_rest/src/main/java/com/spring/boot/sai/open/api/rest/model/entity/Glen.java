package com.spring.boot.sai.open.api.rest.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Setter
@Getter
@Entity
public class Glen {
    @Id
    private String id_n;
    private String tipo;
    private Integer batch;
    private String username;
    private short e;
    private short s;
    private Date fecha;
    private String revisado;
    private Date fecha_revision;
    private String exportada;
    private String estado;
    private String descripcion;

}
