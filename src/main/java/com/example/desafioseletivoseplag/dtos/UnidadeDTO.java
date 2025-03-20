package com.example.desafioseletivoseplag.dtos;

import com.example.desafioseletivoseplag.models.Endereco;
import com.example.desafioseletivoseplag.models.Unidade;
import com.example.desafioseletivoseplag.providers.dtos.ToModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UnidadeDTO implements ToModel<Unidade> {

    private Long id;
    private String nome;
    private String sigla;
    private Set<EnderecoDTO> enderecos = new HashSet<>();

    public UnidadeDTO() {}

    public UnidadeDTO(Unidade unidade) {
        this.id = unidade.getId();
        this.nome = unidade.getNome();
        this.sigla = unidade.getSigla();
    }

    public UnidadeDTO(Unidade unidade, Set<Endereco> enderecos) {
        this(unidade);
        this.enderecos = enderecos == null || enderecos.isEmpty() ? null : enderecos.stream().map(EnderecoDTO::new).collect(Collectors.toSet());
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Set<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public Unidade toModel() {
        Unidade unidade = new Unidade();
        unidade.setId(id);
        unidade.setNome(nome);
        unidade.setSigla(sigla);
        return unidade;
    }
}
