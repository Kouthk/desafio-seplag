package com.example.desafioseletivoseplag.services;

import com.example.desafioseletivoseplag.dtos.PessoaDTO;
import com.example.desafioseletivoseplag.dtos.filters.PessoaFilterDTO;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.providers.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaService extends CrudService<PessoaDTO, Long>, LayerDefinition {
    Page<PessoaDTO> findByFilter(PessoaFilterDTO filter, Pageable pageable);
}
