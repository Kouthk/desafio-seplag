package com.example.desafioseletivoseplag.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private Long id;

    @Column(name = "us_username")
    private String username;

    @Column(name = "us_password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @ManyToMany
    @JoinTable(
            name = "perfil_usuario",
            joinColumns = @JoinColumn(name = "us_id"),
            inverseJoinColumns = @JoinColumn(name = "pe_id")
    )
    private Set<Perfil> perfis = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }
}
