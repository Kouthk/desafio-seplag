package com.example.desafioseletivoseplag.models;

import com.example.desafioseletivoseplag.dtos.FotoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "foto_pessoa")
public class FotoPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id")
    private Long id;

    @Column(name = "fp_data")
    private LocalDate data;

    @Column(name = "fp_bucket")
    private String bucket;

    @Column(name = "fp_hash")
    private String hash;

    @Column(name = "fp_url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
