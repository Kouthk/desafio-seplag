package com.example.desafioseletivoseplag.dtos;

import com.example.desafioseletivoseplag.models.FotoPessoa;
import com.example.desafioseletivoseplag.providers.dtos.ToModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FotoDTO implements ToModel<FotoPessoa> {

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data;
    private String bucket;
    private String hash;
    private byte[] content;
    private String filename;
    private String contentType;
    private String url;

    public FotoDTO() {}

    public FotoDTO(FotoPessoa foto) {
        this.id = foto.getId();
        this.data = foto.getData().atStartOfDay();
        this.bucket = foto.getBucket();
        this.url = foto.getUrl();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public FotoPessoa toModel() {
        FotoPessoa model = new FotoPessoa();
        model.setId(id);
        model.setData(LocalDate.from(data));
        model.setBucket(bucket);
        return model;
    }

}
