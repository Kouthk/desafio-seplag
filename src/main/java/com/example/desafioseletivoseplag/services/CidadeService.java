package com.example.desafioseletivoseplag.services;

import com.example.desafioseletivoseplag.dtos.CidadeDTO;
import com.example.desafioseletivoseplag.dtos.filters.CidadeFilterDTO;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.providers.services.FindService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CidadeService extends FindService<CidadeDTO, Long>, LayerDefinition {

    Page<CidadeDTO> findByFilter(CidadeFilterDTO filter, Pageable pageable);
}
