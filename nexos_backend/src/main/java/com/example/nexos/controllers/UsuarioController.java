package com.example.nexos.controllers;

import com.example.nexos.models.Usuario;
import com.example.nexos.repositories.CargoRepositorio;
import com.example.nexos.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping(path = "/user")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private CargoRepositorio cargoRepositorio;

    @PostMapping(path = "/add")
    public @ResponseBody
    String agregarUsuario(@RequestParam String nombre, @RequestParam Integer edad, @RequestParam Long cargo, @RequestParam Date fecha_ingreso) {
        Usuario n = new Usuario();
        n.setCargo( cargoRepositorio.findById(cargo).orElse(null));
        n.setEdad(edad);
        n.setFechaIngreso(fecha_ingreso);
        n.setNombre(nombre);
        try{
            usuarioRepositorio.save(n);
            return "Guardado";
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Usuario> darUsuarios(){
        return usuarioRepositorio.findAll();
    }

}
