package com.example.desafioseletivoseplag.services.impl;


import com.example.desafioseletivoseplag.dtos.PessoaDTO;
import com.example.desafioseletivoseplag.models.enums.LayerEnum;
import com.example.desafioseletivoseplag.services.PessoaService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PessoaServiceImpl implements PessoaService {

    @Override
    public String getClassName() {
        return "";
    }

    @Override
    public LayerEnum getLayer() {
        return null;
    }

    @Override
    public PessoaDTO create(PessoaDTO pessoaDTO) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<PessoaDTO> findAll() {
        return List.of();
    }

    @Override
    public PessoaDTO findById(Long aLong) {
        return null;
    }

    @Override
    public PessoaDTO update(PessoaDTO pessoaDTO, Long aLong) {
        return null;
    }
}
