package com.example.desafioseletivoseplag.providers.exceptions;

import com.example.desafioseletivoseplag.models.enums.LayerEnum;

public interface LayerDefinition {

    String getClassName();
    LayerEnum getLayer();
}
