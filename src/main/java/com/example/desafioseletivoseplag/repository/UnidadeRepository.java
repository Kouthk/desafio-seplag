package com.example.desafioseletivoseplag.repository;

import com.example.desafioseletivoseplag.models.Cidade;
import com.example.desafioseletivoseplag.models.Unidade;
import com.example.desafioseletivoseplag.models.enums.UfEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnidadeRepository  extends JpaRepository<Unidade, Long> {

    @Query("SELECT u " +
            "FROM Unidade u " +
            "WHERE (:nome IS NULL OR UPPER(u.nome) LIKE CONCAT('%',CONCAT(UPPER(:nome),'%'))) " +
            "AND (:sigla IS NULL OR UPPER(u.sigla) LIKE CONCAT('%',CONCAT(UPPER(:sigla),'%'))) ")
    Page<Unidade> findByFilter(String nome, String sigla, Pageable pageable);
}
