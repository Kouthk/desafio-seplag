package com.example.desafioseletivoseplag.dtos;

import com.example.desafioseletivoseplag.models.Pessoa;
import com.example.desafioseletivoseplag.models.ServidorEfetivo;
import com.example.desafioseletivoseplag.providers.dtos.ToModel;

public class ServidorEfetivoDTO implements ToModel<ServidorEfetivo> {

    private String matricula;
    private PessoaDTO pessoa;

    public ServidorEfetivoDTO() {}

    public ServidorEfetivoDTO(ServidorEfetivo servidorEfetivo) {
        this.matricula = servidorEfetivo.getMatricula();
    }

    public ServidorEfetivoDTO(ServidorEfetivo servidorEfetivo, Pessoa pessoa) {
        this(servidorEfetivo);
        this.pessoa = pessoa == null ? null : new PessoaDTO(pessoa, pessoa.getFotos().stream().toList(),pessoa.getEnderecos().stream().toList());
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public ServidorEfetivo toModel() {
        ServidorEfetivo servidorEfetivo = new ServidorEfetivo();
        servidorEfetivo.setMatricula(matricula);
        if (pessoa != null) {
            servidorEfetivo.setPessoa(pessoa.toModel());
        }
        return servidorEfetivo;
    }
}
