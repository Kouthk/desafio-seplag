package com.example.desafioseletivoseplag.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "unidade")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unid_id")
    private Long id;

    @Column(name = "unid_nome")
    private String nome;

    @Column(name = "unid_sigla")
    private String sigla;

    @ManyToMany
    @JoinTable(
            name = "unidade_endereco",
            joinColumns = @JoinColumn(name = "unid_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private Set<Endereco> enderecos = new HashSet<>();

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

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
