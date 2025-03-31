package com.example.desafioseletivoseplag.repository;

import com.example.desafioseletivoseplag.models.FotoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, Long> {
    List<FotoPessoa> findByPessoaId(Long pessoaId);

}
