package com.example.desafioseletivoseplag.services;

import com.example.desafioseletivoseplag.dtos.FotoDTO;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.providers.services.CrudService;

import java.util.List;

public interface FotoPessoaService  {

    FotoDTO upload(FotoDTO dto, long pessoaId);
    List<FotoDTO> download(long pessoaId);
    FotoDTO download(String key);
    List<FotoDTO> findByPessoaId(long pessoaId);
    void delete(long pessoaId);
}
