package com.example.desafioseletivoseplag.services;


import com.example.desafioseletivoseplag.dtos.ServidorTemporarioDTO;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.providers.services.CrudService;


public interface ServidorTemporarioService extends CrudService<ServidorTemporarioDTO, Long>, LayerDefinition {

}