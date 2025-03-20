package com.example.desafioseletivoseplag.services.impl;

import com.example.desafioseletivoseplag.dtos.EnderecoDTO;
import com.example.desafioseletivoseplag.dtos.UnidadeDTO;
import com.example.desafioseletivoseplag.dtos.filters.UnidadeFilterDTO;
import com.example.desafioseletivoseplag.models.Endereco;
import com.example.desafioseletivoseplag.models.Unidade;
import com.example.desafioseletivoseplag.models.UnidadeEndereco;
import com.example.desafioseletivoseplag.models.enums.LayerEnum;
import com.example.desafioseletivoseplag.providers.exceptions.BusinessException;
import com.example.desafioseletivoseplag.providers.exceptions.ResourceNotFoundException;
import com.example.desafioseletivoseplag.repository.UnidadeEnderecoRepository;
import com.example.desafioseletivoseplag.repository.UnidadeRepository;
import com.example.desafioseletivoseplag.services.EnderecoService;
import com.example.desafioseletivoseplag.services.UnidadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UnidadeServiceImpl implements UnidadeService {

    private final UnidadeRepository repository;
    private final EnderecoService enderecoService;
    private final UnidadeEnderecoRepository unidadeEnderecoRepository;

    public UnidadeServiceImpl(UnidadeRepository repository, EnderecoService enderecoService, UnidadeEnderecoRepository unidadeEnderecoRepository) {
        this.repository = repository;
        this.enderecoService = enderecoService;
        this.unidadeEnderecoRepository = unidadeEnderecoRepository;
    }

    @Override
    public Page<UnidadeDTO> findByFilter(UnidadeFilterDTO filter, Pageable pageable) {
        Page<Unidade> unidades = repository.findByFilter(filter.getNome(), filter.getSigla(), pageable);
        return unidades.map(UnidadeDTO::new);
    }

    @Override
    @Transactional
    public UnidadeDTO create(UnidadeDTO unidadeDTO) {
        validarCamposObrigatorios(unidadeDTO);
        Unidade unidade = unidadeDTO.toModel();
        unidade = repository.save(unidade);
        Set<EnderecoDTO> enderecos = unidadeDTO.getEnderecos().stream().map(enderecoService::create).collect(Collectors.toSet());
        for (EnderecoDTO endereco : enderecos) {
            unidadeEnderecoRepository.save(new UnidadeEndereco(unidade.getId(), endereco.getId()));
        }
        UnidadeDTO dto = new UnidadeDTO(unidade);
        unidadeDTO.setEnderecos(enderecos);
        return dto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        List<UnidadeEndereco> unidadeEnderecos = unidadeEnderecoRepository.findByUnidadeId(id);
        unidadeEnderecoRepository.deleteByUnidadeId(id);
        unidadeEnderecos.forEach(unidadeEndereco -> enderecoService.delete(unidadeEndereco.getEnderecoId()));
        repository.deleteById(id);
    }

    @Override
    public List<UnidadeDTO> findAll() {
        return repository.findAll().stream().map(UnidadeDTO::new).collect(Collectors.toList());
    }

    @Override
    public UnidadeDTO findById(Long id) {
        UnidadeDTO unidadeDTO = repository.findById(id).map(UnidadeDTO::new).orElseThrow(() -> new ResourceNotFoundException("Nenhuma unidade encontrada", this));
        List<UnidadeEndereco> unidadeEnderecos = unidadeEnderecoRepository.findByUnidadeId(id);
        unidadeDTO.setEnderecos(unidadeEnderecos.stream().map(unidadeEndereco -> enderecoService.findById(unidadeEndereco.getEnderecoId())).collect(Collectors.toSet()));
        return unidadeDTO;
    }

    @Override
    @Transactional
    public UnidadeDTO update(UnidadeDTO unidadeDTO, Long id) {
        validarCamposObrigatorios(unidadeDTO);
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhuma unidade encontrada", this);
        }
        Unidade unidade = unidadeDTO.toModel();
        unidade = repository.save(unidade);
        UnidadeDTO dto = new UnidadeDTO(unidade);
        dto.setEnderecos(gerirRelacionamentoComEndereco(id, unidadeDTO.getEnderecos()));
        return dto;
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }

    private Set<EnderecoDTO> gerirRelacionamentoComEndereco(long unidadeId, Set<EnderecoDTO> enderecos) {
        Set<EnderecoDTO> enderecosSalvos = new HashSet<>();
        unidadeEnderecoRepository.deleteByUnidadeId(unidadeId);
        EnderecoDTO enderecoDTO;
        for (EnderecoDTO endereco : enderecos) {
            if (endereco.getId() == null) {
                enderecoDTO = enderecoService.create(endereco);
            } else {
                enderecoDTO = enderecoService.update(endereco, endereco.getId());
            }
            unidadeEnderecoRepository.save(new UnidadeEndereco(unidadeId, enderecoDTO.getId()));
            enderecosSalvos.add(enderecoDTO);
        }
        return enderecosSalvos;
    }

    private void validarCamposObrigatorios(UnidadeDTO unidadeDTO) {
        if (unidadeDTO.getNome() == null || unidadeDTO.getNome().isEmpty()) {
            throw new BusinessException("O nome da unidade é obrigatório", this);
        }
        if (unidadeDTO.getSigla() == null || unidadeDTO.getSigla().isEmpty()) {
            throw new BusinessException("A sigla da unidade é obrigatório", this);
        }
        if (unidadeDTO.getEnderecos().isEmpty()) {
            throw new BusinessException("Ao menos um endereco deve ser informado", this);
        }
    }
}
