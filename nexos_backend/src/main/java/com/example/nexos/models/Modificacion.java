package com.example.nexos.models;


import javax.persistence.*;
import java.sql.Date;
@Entity
public class Modificacion {


    private Date fecha;
    @ManyToOne
    private Usuario usuarioModificacion;
    @ManyToOne
    private Mercancia mercancia;

    public Mercancia getMercanciaModificacion() {
        return mercancia;
    }

    public void setMercanciaModificacion(Mercancia mercanciaModificacion) {
        this.mercancia = mercanciaModificacion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(Usuario usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }



    public Long getId() {
        return id;
    }
}
