package com.example.desafioseletivoseplag.providers.exceptions;

import com.example.desafioseletivoseplag.models.enums.LayerEnum;

public class DatabaseException extends LayerException {

    public DatabaseException(String message, LayerDefinition layerDefinition) {
        super(message, layerDefinition);
    }
}
