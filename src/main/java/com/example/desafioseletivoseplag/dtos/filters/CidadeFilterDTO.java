package com.example.desafioseletivoseplag.dtos.filters;

import com.example.desafioseletivoseplag.models.enums.UfEnum;

public class CidadeFilterDTO {

    String nome;
    UfEnum uf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UfEnum getUf() {
        return uf;
    }

    public void setUf(UfEnum uf) {
        this.uf = uf;
    }
}
