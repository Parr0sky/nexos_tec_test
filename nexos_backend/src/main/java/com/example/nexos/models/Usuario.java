package com.example.nexos.models;

import javax.persistence.*;

import java.util.Date;
@Entity
public class Usuario {
    /**
     *
     */
    private String nombre;
    /**
     *
     */
    private Integer edad;
    /**
     *
     */
    @ManyToOne
    private Cargo cargo;
    /**
     *
     */
    private Date fechaIngreso;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }


}
