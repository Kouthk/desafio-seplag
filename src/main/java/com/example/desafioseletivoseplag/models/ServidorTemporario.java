package com.example.desafioseletivoseplag.models;


import com.example.desafioseletivoseplag.dtos.ServidorTemporarioDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "servidor_temporario")
public class ServidorTemporario {

    @Id
    @Column(name = "pes_id")
    private Long pessoaId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @Column(name = "st_data_admissao")
    private LocalDate dataAdmissao;

    @Column(name = "st_data_demissao")
    private LocalDate dataDemissao;

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(LocalDate dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

}