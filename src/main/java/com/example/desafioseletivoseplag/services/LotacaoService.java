package com.example.desafioseletivoseplag.services;

import com.example.desafioseletivoseplag.dtos.LotacaoDTO;
import com.example.desafioseletivoseplag.dtos.filters.LotacaoFilterDTO;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.providers.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LotacaoService extends CrudService<LotacaoDTO, Long>, LayerDefinition {

    Page<LotacaoDTO> findByFilter(LotacaoFilterDTO filter, Pageable pageable);

}
