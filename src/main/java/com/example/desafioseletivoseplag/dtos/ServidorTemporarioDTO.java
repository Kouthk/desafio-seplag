package com.example.desafioseletivoseplag.dtos;

import com.example.desafioseletivoseplag.models.Pessoa;
import com.example.desafioseletivoseplag.models.ServidorTemporario;
import com.example.desafioseletivoseplag.providers.dtos.ToModel;

import java.time.LocalDate;

public class ServidorTemporarioDTO implements ToModel<ServidorTemporario> {

    private LocalDate dataAdmissao;
    private LocalDate dataDemissao;
    private PessoaDTO pessoa;

    public ServidorTemporarioDTO() {}

    public ServidorTemporarioDTO(ServidorTemporario servidorTemporario) {
        this.dataAdmissao = servidorTemporario.getDataAdmissao();
        this.dataDemissao = servidorTemporario.getDataDemissao();
    }

    public ServidorTemporarioDTO(ServidorTemporario servidorTemporario, Pessoa pessoa) {
        this(servidorTemporario);
        this.pessoa = pessoa == null ? null : new  PessoaDTO(pessoa, pessoa.getFotos().stream().toList(),pessoa.getEnderecos().stream().toList());
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

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public ServidorTemporario toModel() {
        ServidorTemporario servidorTemporario = new ServidorTemporario();
        servidorTemporario.setDataAdmissao(dataAdmissao);
        servidorTemporario.setDataDemissao(dataDemissao);
        if (pessoa != null) {
            servidorTemporario.setPessoa(pessoa.toModel());
        }
        return servidorTemporario;
    }
}
