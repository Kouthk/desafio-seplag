package com.example.desafioseletivoseplag.services;

import com.example.desafioseletivoseplag.dtos.PessoaDTO;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.providers.services.CrudService;

public interface PessoaService extends CrudService<PessoaDTO, Long>, LayerDefinition {

}
