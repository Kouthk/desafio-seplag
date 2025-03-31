package com.example.desafioseletivoseplag.services.impl;

import com.example.desafioseletivoseplag.dtos.FotoDTO;
import com.example.desafioseletivoseplag.repository.FotoPessoaRepository;
import com.example.desafioseletivoseplag.services.FotoPessoaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FotoPessoaServiceImpl implements FotoPessoaService {

    private final FotoPessoaRepository fotoPessoaRepository;

    public FotoPessoaServiceImpl(FotoPessoaRepository fotoPessoaRepository) {
        this.fotoPessoaRepository = fotoPessoaRepository;
    }

    @Override
    public FotoDTO upload(FotoDTO dto, long pessoaId) {
        return null;
    }

    @Override
    public List<FotoDTO> download(long pessoaId) {
        return List.of();
    }

    @Override
    public FotoDTO download(String key) {
        return null;
    }

    @Override
    public List<FotoDTO> findByPessoaId(long pessoaId) {
        return List.of();
    }

    @Override
    public void delete(long pessoaId) {

    }
}
