package com.example.desafioseletivoseplag.services;

import com.example.desafioseletivoseplag.dtos.ServidorEfetivoDTO;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.providers.services.CrudService;

import java.util.List;

public interface ServidorEfetivoService extends CrudService<ServidorEfetivoDTO, Long>, LayerDefinition {

}
