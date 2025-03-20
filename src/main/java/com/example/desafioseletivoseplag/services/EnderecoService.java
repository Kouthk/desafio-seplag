package com.example.desafioseletivoseplag.services;

import com.example.desafioseletivoseplag.dtos.EnderecoDTO;
import com.example.desafioseletivoseplag.dtos.PessoaDTO;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.providers.services.CrudService;

public interface EnderecoService extends CrudService<EnderecoDTO, Long>, LayerDefinition {
}
