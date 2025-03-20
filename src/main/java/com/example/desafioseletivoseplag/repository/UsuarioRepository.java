package com.example.desafioseletivoseplag.repository;

import com.example.desafioseletivoseplag.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
}
