package com.example.desafioseletivoseplag.services;

import com.example.desafioseletivoseplag.dtos.UnidadeDTO;
import com.example.desafioseletivoseplag.dtos.filters.UnidadeFilterDTO;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.providers.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UnidadeService extends CrudService<UnidadeDTO, Long>, LayerDefinition {

    Page<UnidadeDTO> findByFilter(UnidadeFilterDTO filter, Pageable pageable);
}
