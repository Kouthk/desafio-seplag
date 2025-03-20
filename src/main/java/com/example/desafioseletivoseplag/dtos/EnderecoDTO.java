package com.example.desafioseletivoseplag.dtos;

import com.example.desafioseletivoseplag.models.Endereco;
import com.example.desafioseletivoseplag.models.enums.TipoLogradouroEnum;
import com.example.desafioseletivoseplag.providers.dtos.ToModel;

public class EnderecoDTO implements ToModel<Endereco> {

    private Long id;
    private TipoLogradouroEnum tipoLogradouro;
    private String logradouro;
    private String numero;
    private String bairro;
    private CidadeDTO cidade;

    public EnderecoDTO() {}

    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.tipoLogradouro = endereco.getTipoLogradouro();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade() == null ? null : new CidadeDTO(endereco.getCidade());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoLogradouroEnum getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouroEnum tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public CidadeDTO getCidade() {
        return cidade;
    }

    public void setCidade(CidadeDTO cidade) {
        this.cidade = cidade;
    }

    @Override
    public Endereco toModel() {
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setTipoLogradouro(tipoLogradouro);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        if (cidade != null) {
            endereco.setCidade(cidade.toModel());
        }
        return endereco;
    }
}
