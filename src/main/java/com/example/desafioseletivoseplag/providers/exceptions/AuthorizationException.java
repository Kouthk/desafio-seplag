package com.example.desafioseletivoseplag.providers.exceptions;

public class AuthorizationException extends LayerException {

    public AuthorizationException(String message, LayerDefinition layerDefinition) {
        super(message, layerDefinition);
    }
}
