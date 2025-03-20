package com.example.desafioseletivoseplag.models;


import com.example.desafioseletivoseplag.dtos.ServidorEfetivoDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "servidor_efetivo", uniqueConstraints = @UniqueConstraint(columnNames = "se_matricula"))
public class ServidorEfetivo {

    @Id
    @Column(name = "pes_id")
    private Long pessoaId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @Column(name = "se_matricula")
    private String matricula;

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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}
