package com.example.nexos.controllers;

import com.example.nexos.models.Mercancia;
import com.example.nexos.models.Modificacion;
import com.example.nexos.models.Usuario;
import com.example.nexos.repositories.CargoRepositorio;
import com.example.nexos.repositories.MercanciaRepositorio;
import com.example.nexos.repositories.ModificacionRepositorio;
import com.example.nexos.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
@Controller
@RequestMapping(path = "/mercs")
public class MercanciaController {

    @Autowired
    private MercanciaRepositorio mercanciaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private ModificacionRepositorio modificacionRepositorio;

    @PostMapping(path = "/add")
    public @ResponseBody
    String agregarMercancia(@RequestParam String nombre, @RequestParam Integer cantidad, @RequestParam Date fecha, @RequestParam Long usuario_creacion) {
        Mercancia n = new Mercancia();
        n.setCantidad(cantidad);
        n.setFechaIngreso(fecha);
        n.setNombre(nombre);
        n.setUsuarioCreacion(usuarioRepositorio.findById(usuario_creacion).orElse(null));
        Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        if(fecha.before(sqlDate)){
            mercanciaRepositorio.save(n);
            return "Guardado";
        }
        else{
            return "Error en fecha";
        }
    }
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Mercancia> darMercacias(){
        return mercanciaRepositorio.findAll();
    }

    @PutMapping( "/edit/{id}")
     @ResponseBody Mercancia reemplazarMerc( @PathVariable Long id, @RequestParam String nombre, @RequestParam Integer cantidad, @RequestParam Date fecha, @RequestParam Long usuario_mod){
        return mercanciaRepositorio.findById(id).map(mercancia -> {
                    mercancia.setFechaIngreso(fecha);
                    mercancia.setNombre(nombre);
                    mercancia.setCantidad(cantidad);
                    Usuario temp = usuarioRepositorio.findById(usuario_mod).orElse(null);
                    if(temp==null){
                        return null;
                    }
                    Modificacion mod=new Modificacion();
                    mod.setUsuarioModificacion(temp);
                    mod.setFecha(new java.sql.Date(new java.util.Date().getTime()));
                    modificacionRepositorio.save(mod);
                    mercancia.addModificacion(mod);
                    return mercanciaRepositorio.save(mercancia);
                }).orElseGet(()-> {
                    return null;
        });
    }

    @DeleteMapping(path = "/delete/{id}")
    @ResponseBody void eliminarMercancia(@PathVariable Long id, @RequestParam Long idUser){
        if(mercanciaRepositorio.findById(id).get().getUsuarioCreacion().getId()==idUser)
            mercanciaRepositorio.deleteById(id);

    }
}
