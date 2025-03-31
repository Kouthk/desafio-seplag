package com.example.desafioseletivoseplag.services;

import com.example.desafioseletivoseplag.dtos.LotacaoDTO;
import com.example.desafioseletivoseplag.dtos.filters.LotacaoFilterDTO;
import com.example.desafioseletivoseplag.models.Lotacao;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.providers.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotacaoService extends CrudService<LotacaoDTO, Long>, LayerDefinition {

    Page<LotacaoDTO> findByFilter(LotacaoFilterDTO filter, Pageable pageable);

    Page<LotacaoDTO> findServidorEfetivoByUnidadeId(Long unidadeId, Pageable pageable);

    Page<LotacaoDTO> findServidorTemporarioByUnidadeId(Long unidadeId, Pageable pageable);


}
