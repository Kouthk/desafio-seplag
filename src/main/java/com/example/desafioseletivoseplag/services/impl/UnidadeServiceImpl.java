package com.example.desafioseletivoseplag.services.impl;

import com.example.desafioseletivoseplag.dtos.EnderecoDTO;
import com.example.desafioseletivoseplag.dtos.UnidadeDTO;
import com.example.desafioseletivoseplag.dtos.filters.UnidadeFilterDTO;
import com.example.desafioseletivoseplag.models.Endereco;
import com.example.desafioseletivoseplag.models.Unidade;
import com.example.desafioseletivoseplag.models.enums.LayerEnum;
import com.example.desafioseletivoseplag.providers.exceptions.BusinessException;
import com.example.desafioseletivoseplag.providers.exceptions.ResourceNotFoundException;
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

    public UnidadeServiceImpl(UnidadeRepository repository, EnderecoService enderecoService) {
        this.repository = repository;
        this.enderecoService = enderecoService;
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

        // Cria e associa os endereços diretamente
        Set<EnderecoDTO> enderecos = unidadeDTO.getEnderecos().stream()
                .map(enderecoService::create)
                .collect(Collectors.toSet());
        unidade.setEnderecos(enderecos.stream().map(EnderecoDTO::toModel).collect(Collectors.toSet()));

        UnidadeDTO dto = new UnidadeDTO(unidade);
        return dto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Unidade unidade = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma unidade encontrada", this));

        // Excluir endereços associados
        unidade.getEnderecos().forEach(endereco -> enderecoService.delete(endereco.getId()));
        repository.deleteById(id);
    }

    @Override
    public List<UnidadeDTO> findAll() {
        return repository.findAll().stream().map(UnidadeDTO::new).collect(Collectors.toList());
    }

    @Override
    public UnidadeDTO findById(Long id) {
        Unidade unidade = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma unidade encontrada", this));
        UnidadeDTO unidadeDTO = new UnidadeDTO(unidade);

        // Associa os endereços corretamente
        unidadeDTO.setEnderecos(unidade.getEnderecos().stream()
                .map(endereco -> new EnderecoDTO(endereco))
                .collect(Collectors.toSet()));

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

        // Atualizar endereços
        unidadeDTO.setEnderecos(gerirRelacionamentoComEndereco(id, unidadeDTO.getEnderecos()));

        UnidadeDTO dto = new UnidadeDTO(unidade);
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
        for (EnderecoDTO endereco : enderecos) {
            if (endereco.getId() == null) {
                enderecoService.create(endereco);  // Cria e persiste novo endereço
            } else {
                enderecoService.update(endereco, endereco.getId());  // Atualiza endereço existente
            }
            enderecosSalvos.add(endereco);
        }
        return enderecosSalvos;
    }

    private void validarCamposObrigatorios(UnidadeDTO unidadeDTO) {
        if (unidadeDTO.getNome() == null || unidadeDTO.getNome().isEmpty()) {
            throw new BusinessException("O nome da unidade é obrigatório", this);
        }
        if (unidadeDTO.getSigla() == null || unidadeDTO.getSigla().isEmpty()) {
            throw new BusinessException("A sigla da unidade é obrigatória", this);
        }
        if (unidadeDTO.getEnderecos().isEmpty()) {
            throw new BusinessException("Ao menos um endereço deve ser informado", this);
        }
    }
}
