package com.example.desafioseletivoseplag.repository;

import com.example.desafioseletivoseplag.models.Cidade;
import com.example.desafioseletivoseplag.models.enums.UfEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CidadeRepository  extends JpaRepository<Cidade, Long> {

    @Query("SELECT c " +
            "FROM Cidade c " +
            "WHERE (:nome IS NULL OR UPPER(c.nome) LIKE CONCAT('%',CONCAT(UPPER(:nome),'%'))) " +
            "AND (:uf IS NULL OR c.uf = :uf) ")
    Page<Cidade> findByFilter(String nome, UfEnum uf, Pageable pageable);
}
