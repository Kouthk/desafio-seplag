package com.example.desafioseletivoseplag.dtos;

import com.example.desafioseletivoseplag.models.Cidade;
import com.example.desafioseletivoseplag.models.enums.UfEnum;
import com.example.desafioseletivoseplag.providers.dtos.ToModel;

public class CidadeDTO implements ToModel<Cidade> {

    private Long id;
    private String nome;
    private UfEnum uf;

    public CidadeDTO() {}

    public CidadeDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.uf = cidade.getUf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public Cidade toModel() {
        Cidade cidade = new Cidade();
        cidade.setId(id);
        cidade.setNome(nome);
        cidade.setUf(uf);
        return cidade;
    }
}
