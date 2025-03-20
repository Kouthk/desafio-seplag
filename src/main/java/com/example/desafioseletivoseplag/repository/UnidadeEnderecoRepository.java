package com.example.desafioseletivoseplag.repository;

import com.example.desafioseletivoseplag.models.UnidadeEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEndereco, Long> {

    List<UnidadeEndereco> findByUnidadeId(Long unidadeId);
    void deleteByUnidadeId(Long unidadeId);
}
