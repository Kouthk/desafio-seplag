package com.example.desafioseletivoseplag.dtos.filters;

import com.example.desafioseletivoseplag.models.Pessoa;

import java.time.LocalDate;

public class LotacaoFilterDTO {
    private String nomePessoa;
    private String portaria;
    private LocalDate dataLotacao;
    private LocalDate dataRemocao;

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public LocalDate getDataLotacao() {
        return dataLotacao;
    }

    public void setDataLotacao(LocalDate dataLotacao) {
        this.dataLotacao = dataLotacao;
    }

    public String getPortaria() {
        return portaria;
    }

    public void setPortaria(String portaria) {
        this.portaria = portaria;
    }

    public LocalDate getDataRemocao() {
        return dataRemocao;
    }

    public void setDataRemocao(LocalDate dataRemocao) {
        this.dataRemocao = dataRemocao;
    }
}
