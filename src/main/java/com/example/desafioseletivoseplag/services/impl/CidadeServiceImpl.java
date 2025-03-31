package com.example.desafioseletivoseplag.services.impl;

import com.example.desafioseletivoseplag.dtos.CidadeDTO;
import com.example.desafioseletivoseplag.dtos.filters.CidadeFilterDTO;
import com.example.desafioseletivoseplag.models.Cidade;
import com.example.desafioseletivoseplag.models.enums.LayerEnum;
import com.example.desafioseletivoseplag.providers.exceptions.ResourceNotFoundException;
import com.example.desafioseletivoseplag.repository.CidadeRepository;
import com.example.desafioseletivoseplag.services.CidadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeServiceImpl implements CidadeService {

    private final CidadeRepository repository;

    public CidadeServiceImpl(CidadeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CidadeDTO> findAll() {
        List<Cidade> cidades = repository.findAll();
        return cidades.stream().map(CidadeDTO::new).toList();
    }

    @Override
    public CidadeDTO findById(Long id) {
        Cidade cidade = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma cidade encontrada", this));
        return new CidadeDTO(cidade);
    }

    @Override
    public Page<CidadeDTO> findByFilter(CidadeFilterDTO filter, Pageable pageable) {
        Page<Cidade> cidades = repository.findByFilter(filter.getNome(), filter.getUf(), pageable);
        return cidades.map(CidadeDTO::new);
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }


}
