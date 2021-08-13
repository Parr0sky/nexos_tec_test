package com.example.nexos.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Entity
public class Mercancia {

    /**
     *
     */
    private String nombre;
    /**
     *
     */
    private Integer cantidad;
    /**
     *
     */
    private Date fechaIngreso;
    /**
     *
     */
    @ManyToOne
    private Usuario usuarioCreacion;



    @OneToMany(mappedBy = "mercancia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Modificacion> modificacion;

    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }


    public Set<Modificacion> getUsuarioModificacion() {
        return modificacion;
    }

    public void addModificacion(Modificacion modificacion) {
        this.modificacion.add(modificacion);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     *
     * @return
     */

    public Long getId() {
        return id;
    }


}
