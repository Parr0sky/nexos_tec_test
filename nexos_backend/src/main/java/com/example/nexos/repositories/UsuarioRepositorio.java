package com.example.nexos.repositories;

import com.example.nexos.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositorio extends CrudRepository<Usuario,Long> {
}
