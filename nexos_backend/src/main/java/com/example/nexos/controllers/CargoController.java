package com.example.nexos.controllers;

import com.example.nexos.models.Cargo;
import com.example.nexos.repositories.CargoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/cargo")
public class CargoController {

    @Autowired
    private CargoRepositorio cargoRepositorio;

    @GetMapping(path = "/all")
    public @ResponseBody    Iterable<Cargo> darMercacias(){
        return cargoRepositorio.findAll();
    }
}
