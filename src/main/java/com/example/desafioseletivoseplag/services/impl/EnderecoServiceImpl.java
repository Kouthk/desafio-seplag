package com.example.desafioseletivoseplag.services.impl;

import com.example.desafioseletivoseplag.dtos.EnderecoDTO;
import com.example.desafioseletivoseplag.models.Endereco;
import com.example.desafioseletivoseplag.models.enums.LayerEnum;
import com.example.desafioseletivoseplag.providers.exceptions.BusinessException;
import com.example.desafioseletivoseplag.providers.exceptions.ResourceNotFoundException;
import com.example.desafioseletivoseplag.repository.EnderecoRepository;
import com.example.desafioseletivoseplag.services.EnderecoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository repository;

    public EnderecoServiceImpl(EnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    public EnderecoDTO create(EnderecoDTO enderecoDTO) {
        validarCamposObrigatorios(enderecoDTO);
        Endereco endereco = enderecoDTO.toModel();
        endereco = repository.save(endereco);
        return new EnderecoDTO(endereco);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<EnderecoDTO> findAll() {
        List<Endereco> enderecos = repository.findAll();
        return enderecos.stream().map(EnderecoDTO::new).toList();
    }

    @Override
    public EnderecoDTO findById(Long id) {
        Endereco endereco = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum endereco encontrado", this));
        return new EnderecoDTO(endereco);
    }

    @Override
    public EnderecoDTO update(EnderecoDTO enderecoDTO, Long id) {
        validarCamposObrigatorios(enderecoDTO);
        Endereco endereco = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum endereco encontrado", this));
        endereco.setTipoLogradouro(enderecoDTO.getTipoLogradouro());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setCidade(enderecoDTO.getCidade().toModel());
        endereco = repository.save(endereco);
        return new EnderecoDTO(endereco);
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }

    private void validarCamposObrigatorios(EnderecoDTO enderecoDTO) {
        if (enderecoDTO.getTipoLogradouro() == null) {
            throw new BusinessException("O tipo de logradouro é obrigatório", this);
        }
        if (enderecoDTO.getLogradouro() == null || enderecoDTO.getLogradouro().isEmpty()) {
            throw new BusinessException("O logradouro é obrigatório", this);
        }
        if (enderecoDTO.getNumero() == null || enderecoDTO.getNumero().isEmpty()) {
            throw new BusinessException("O número do endereco é obrigatório", this);
        }
        if (enderecoDTO.getBairro() == null || enderecoDTO.getBairro().isEmpty()) {
            throw new BusinessException("O bairro é obrigatório", this);
        }
        if (enderecoDTO.getCidade() == null) {
            throw new BusinessException("A cidade é obrigatório", this);
        }
        if (enderecoDTO.getCidade().getId() == null) {
            throw new BusinessException("A cidade é obrigatório", this);
        }
    }

}
