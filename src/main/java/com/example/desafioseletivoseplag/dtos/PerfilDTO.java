package com.example.desafioseletivoseplag.dtos;

import com.example.desafioseletivoseplag.models.Perfil;
import com.example.desafioseletivoseplag.providers.dtos.ToModel;

public class PerfilDTO implements ToModel<Perfil> {

    private Long id;
    private String authority;

    public PerfilDTO() {}

    public PerfilDTO(Perfil perfil) {
        this.id = perfil.getId();
        this.authority = perfil.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public Perfil toModel() {
        Perfil perfil = new Perfil();
        perfil.setId(id);
        perfil.setAuthority(authority);
        return perfil;
    }
}
