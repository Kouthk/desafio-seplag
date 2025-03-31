package com.example.desafioseletivoseplag.services.impl;


import com.example.desafioseletivoseplag.dtos.EnderecoDTO;
import com.example.desafioseletivoseplag.dtos.PessoaDTO;
import com.example.desafioseletivoseplag.dtos.filters.PessoaFilterDTO;
import com.example.desafioseletivoseplag.models.Endereco;
import com.example.desafioseletivoseplag.models.FotoPessoa;
import com.example.desafioseletivoseplag.models.Pessoa;
import com.example.desafioseletivoseplag.models.enums.LayerEnum;
import com.example.desafioseletivoseplag.providers.exceptions.BusinessException;
import com.example.desafioseletivoseplag.providers.exceptions.ResourceNotFoundException;
import com.example.desafioseletivoseplag.repository.PessoaRepository;
import com.example.desafioseletivoseplag.services.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PessoaServiceImpl implements PessoaService {


    private final PessoaRepository repository;

    public PessoaServiceImpl(PessoaRepository repository) {
        this.repository = repository;
    }
    @Override
    public Page<PessoaDTO> findByFilter(PessoaFilterDTO filter, Pageable pageable) {
        return repository.findByFilter(filter.getNome(), pageable).map(PessoaDTO::new) ;
    }

    @Override
    @Transactional
    public PessoaDTO create(PessoaDTO dto) {
        validarCamposObrigatorios(dto);

        Pessoa pessoa = dto.toModel();

        pessoa = repository.save(pessoa);

        if (dto.getUrlFoto() != null) {
            FotoPessoa foto = new FotoPessoa();
            foto.setUrl(dto.getUrlFoto());
            foto.setPessoa(pessoa);
            pessoa.getFotos().add(foto);
        }

        Set<Endereco> enderecos = dto.getEnderecos().stream()
                .map(EnderecoDTO::toModel)
                .collect(Collectors.toSet());
        pessoa.setEnderecos(enderecos);

        pessoa = repository.save(pessoa);

        return new PessoaDTO(pessoa, pessoa.getFotos().stream().toList(), pessoa.getEnderecos().stream().toList());
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhuma pessoa encontrada para exclusão.", this);
        }
        repository.deleteById(id);
    }

    @Override
    public List<PessoaDTO> findAll() {
        return repository.findAll().stream()
                .map(pessoa -> new PessoaDTO(pessoa, pessoa.getFotos().stream().toList(), pessoa.getEnderecos().stream().toList()))
                .toList();
    }

    @Override
    public PessoaDTO findById(Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa encontrada", this));

        return new PessoaDTO(pessoa, pessoa.getFotos().stream().toList(), pessoa.getEnderecos().stream().toList());
    }

    @Override
    @Transactional
    public PessoaDTO update(PessoaDTO dto, Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa encontrada", this));

        validarCamposObrigatorios(dto);

        pessoa.setNome(dto.getNome());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoa.setSexo(dto.getSexo());
        pessoa.setNomeMae(dto.getNomeMae());
        pessoa.setNomePai(dto.getNomePai());

        Set<Endereco> novosEnderecos = dto.getEnderecos().stream()
                .map(EnderecoDTO::toModel)
                .collect(Collectors.toSet());
        pessoa.setEnderecos(novosEnderecos);

        if (dto.getUrlFoto() != null) {
            pessoa.getFotos().clear();
            FotoPessoa novaFoto = new FotoPessoa();
            novaFoto.setUrl(dto.getUrlFoto());
            novaFoto.setPessoa(pessoa);
            pessoa.getFotos().add(novaFoto);
        }

        pessoa = repository.save(pessoa);

        return new PessoaDTO(pessoa, pessoa.getFotos().stream().toList(), pessoa.getEnderecos().stream().toList());
    }

    private void validarCamposObrigatorios(PessoaDTO pessoaDTO) {
        if (pessoaDTO.getNome() == null || pessoaDTO.getNome().isEmpty()) {
            throw new BusinessException("O nome da pessoa é obrigatório", this);
        }
        if (pessoaDTO.getDataNascimento() == null) {
            throw new BusinessException("A data de nascimento da pessoa é obrigatória", this);
        }
        if (pessoaDTO.getSexo() == null) {
            throw new BusinessException("O sexo da pessoa é obrigatório", this);
        }
    }

    private String extrairUrlFoto(Pessoa pessoa) {
        return pessoa.getFotos().stream()
                .findFirst()
                .map(FotoPessoa::getUrl)
                .orElse(null);
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }
}
