package com.example.desafioseletivoseplag.dtos;

import com.example.desafioseletivoseplag.models.Perfil;
import com.example.desafioseletivoseplag.models.Pessoa;
import com.example.desafioseletivoseplag.models.Usuario;
import com.example.desafioseletivoseplag.providers.dtos.ToModel;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioDTO implements ToModel<Usuario> {

    private Long id;
    private String username;
    private String password;
    private PessoaDTO pessoa;
    private Set<PerfilDTO> perfis = new HashSet<>();

    public UsuarioDTO() {}

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
    }

    public UsuarioDTO(Usuario usuario, Set<Perfil> perfis) {
        this(usuario);
        this.perfis = perfis.stream().map(PerfilDTO::new).collect(Collectors.toSet());
    }

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

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    public Set<PerfilDTO> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<PerfilDTO> perfis) {
        this.perfis = perfis;
    }

    @Override
    public Usuario toModel() {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setPessoa(pessoa.toModel());
        if(!perfis.isEmpty()) {
            usuario.setPerfis(perfis.stream().map(PerfilDTO::toModel).collect(Collectors.toSet()));
        }
        return usuario;
    }
}
