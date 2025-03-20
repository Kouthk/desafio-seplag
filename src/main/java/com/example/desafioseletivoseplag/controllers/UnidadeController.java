package com.example.desafioseletivoseplag.controllers;

import com.example.desafioseletivoseplag.dtos.UnidadeDTO;
import com.example.desafioseletivoseplag.dtos.filters.UnidadeFilterDTO;
import com.example.desafioseletivoseplag.models.enums.LayerEnum;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.services.UnidadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/unidades")
public class UnidadeController implements LayerDefinition {
    
    private final UnidadeService service;

    public UnidadeController(UnidadeService service) {
        this.service = service;
    }

    @PostMapping("/paginado")
    public Page<UnidadeDTO> findByFilter(@RequestBody UnidadeFilterDTO filter, Pageable pageable) {
        return service.findByFilter(filter, pageable);
    }

    @PostMapping
    public UnidadeDTO create(@RequestBody UnidadeDTO unidadeDTO) {
        return service.create(unidadeDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public List<UnidadeDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UnidadeDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public UnidadeDTO update(@RequestBody UnidadeDTO unidadeDTO, @PathVariable Long id) {
        return service.update(unidadeDTO, id);
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_CONTROLLER;
    }
}
