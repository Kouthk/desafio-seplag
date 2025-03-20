package com.example.desafioseletivoseplag.repository;

import com.example.desafioseletivoseplag.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository  extends JpaRepository<Pessoa, Long> {
}
