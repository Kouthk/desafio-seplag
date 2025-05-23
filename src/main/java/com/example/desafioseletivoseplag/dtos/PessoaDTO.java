package com.example.desafioseletivoseplag.dtos;

import com.example.desafioseletivoseplag.models.Endereco;
import com.example.desafioseletivoseplag.models.FotoPessoa;
import com.example.desafioseletivoseplag.models.Pessoa;
import com.example.desafioseletivoseplag.models.enums.SexoEnum;
import com.example.desafioseletivoseplag.providers.dtos.ToModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PessoaDTO implements ToModel<Pessoa> {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private SexoEnum sexo;
    private String nomeMae;
    private String nomePai;
    private String urlFoto;
    private List<EnderecoDTO> enderecos = new ArrayList<>();
    private List<FotoDTO> fotos = new ArrayList<>();

    public PessoaDTO() {}

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.dataNascimento = pessoa.getDataNascimento();
        this.sexo = pessoa.getSexo();
        this.nomeMae = pessoa.getNomeMae();
        this.nomePai = pessoa.getNomePai();
    }

    public PessoaDTO(Pessoa pessoa, List<FotoPessoa> fotos, List<Endereco> enderecos) {
        this(pessoa);
        this.fotos = (fotos != null)
                ? fotos.stream()
                .filter(foto -> foto != null)  // Filtra fotos nulas
                .map(FotoDTO::new)
                .toList()
                : new ArrayList<>();
        this.enderecos = (enderecos != null && !enderecos.isEmpty())
                ? enderecos.stream().map(EnderecoDTO::new).collect(Collectors.toList())
                : new ArrayList<>();
    }



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

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }

    public List<FotoDTO> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoDTO> fotos) {
        this.fotos = fotos;
    }

    @Override
    public Pessoa toModel() {
        Pessoa pessoa = new  Pessoa();
        pessoa.setId(id);
        pessoa.setNome(nome);
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setSexo(sexo);
        pessoa.setNomePai(nomePai);
        pessoa.setNomeMae(nomeMae);
        if(enderecos != null && !enderecos.isEmpty()) {
            pessoa.setEnderecos(enderecos.stream().map(EnderecoDTO::toModel).collect(Collectors.toSet()));
        }
        if(fotos != null && !fotos.isEmpty()) {
            pessoa.setFotos(fotos.stream().map(FotoDTO::toModel).collect(Collectors.toSet()));
        }
        return pessoa;
    }


}
