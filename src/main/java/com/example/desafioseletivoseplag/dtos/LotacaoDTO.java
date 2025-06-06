package com.example.desafioseletivoseplag.dtos;

import com.example.desafioseletivoseplag.models.Lotacao;
import com.example.desafioseletivoseplag.models.Pessoa;
import com.example.desafioseletivoseplag.models.Unidade;
import com.example.desafioseletivoseplag.providers.dtos.ToModel;

import java.time.LocalDate;

public class LotacaoDTO implements ToModel<Lotacao> {

    private Long id;
    private LocalDate dataLotacao;
    private LocalDate dataRemocao;
    private String portaria;
    private PessoaDTO pessoa;
    private UnidadeDTO unidade;

    public LotacaoDTO() {}

    public LotacaoDTO(Lotacao lotacao) {
        this.id = lotacao.getId();
        this.dataLotacao = lotacao.getDataLotacao();
        this.dataRemocao = lotacao.getDataRemocao();
        this.portaria = lotacao.getPortaria();
        this.pessoa = lotacao.getPessoa() != null ? new PessoaDTO(lotacao.getPessoa()) : null;
        this.unidade = lotacao.getUnidade() != null ? new UnidadeDTO(lotacao.getUnidade()) : null;
    }


    public LotacaoDTO(Lotacao lotacao, Pessoa pessoa, Unidade unidade) {
        this(lotacao);
        this.pessoa = pessoa == null ? null : new PessoaDTO(pessoa);
        this.unidade = unidade == null ? null : new UnidadeDTO(unidade);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataLotacao() {
        return dataLotacao;
    }

    public void setDataLotacao(LocalDate dataLotacao) {
        this.dataLotacao = dataLotacao;
    }

    public LocalDate getDataRemocao() {
        return dataRemocao;
    }

    public void setDataRemocao(LocalDate dataRemocao) {
        this.dataRemocao = dataRemocao;
    }

    public String getPortaria() {
        return portaria;
    }

    public void setPortaria(String portaria) {
        this.portaria = portaria;
    }

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    public UnidadeDTO getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeDTO unidade) {
        this.unidade = unidade;
    }

    @Override
    public Lotacao toModel() {
        Lotacao lotacao = new Lotacao();
        lotacao.setId(id);
        lotacao.setDataLotacao(dataLotacao);
        lotacao.setDataRemocao(dataRemocao);
        lotacao.setPortaria(portaria);
        if(pessoa != null) {
            lotacao.setPessoa(pessoa.toModel());
        }
        if(unidade != null) {
            lotacao.setUnidade(unidade.toModel());
        }
        return lotacao;
    }
}
