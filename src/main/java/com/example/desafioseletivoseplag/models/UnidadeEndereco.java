package com.example.desafioseletivoseplag.models;

import jakarta.persistence.*;

@Entity
@Table(name = "unidade_endereco")
public class UnidadeEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "unid_id")
    private Long unidadeId;
    @Column(name = "end_id")
    private Long enderecoId;

    public UnidadeEndereco() {}

    public UnidadeEndereco(Long unidadeId, Long enderecoId) {
        this.unidadeId = unidadeId;
        this.enderecoId = enderecoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }
}

