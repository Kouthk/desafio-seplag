package com.example.desafioseletivoseplag.repository;

import com.example.desafioseletivoseplag.models.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PessoaRepository  extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p " +
            "FROM Pessoa p " +
            "WHERE (:nome IS NULL OR UPPER(p.nome) LIKE :nome) ")
    Page<Pessoa> findByFilter(String nome, Pageable pageable);
}
