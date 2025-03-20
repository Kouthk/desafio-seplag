package com.example.desafioseletivoseplag.models;

import com.example.desafioseletivoseplag.dtos.PessoaDTO;
import com.example.desafioseletivoseplag.models.enums.SexoEnum;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Long id;

    @Column(name = "pes_nome")
    private String nome;

    @Column(name = "pes_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "pes_sexo")
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @Column(name = "pes_mae")
    private String nomeMae;

    @Column(name = "pes_pai")
    private String nomePai;

    @OneToMany(mappedBy = "pessoa")
    private Set<FotoPessoa> fotos = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "pessoa_endereco",
            joinColumns = @JoinColumn(name = "pes_id"),
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String mae) {
        this.nomeMae = mae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String pai) {
        this.nomePai = pai;
    }

    public Set<FotoPessoa> getFotos() {
        return fotos;
    }

    public void setFotos(Set<FotoPessoa> fotos) {
        this.fotos = fotos;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
