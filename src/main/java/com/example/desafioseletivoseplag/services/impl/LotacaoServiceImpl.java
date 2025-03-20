package com.example.desafioseletivoseplag.services.impl;
import com.example.desafioseletivoseplag.dtos.LotacaoDTO;
import com.example.desafioseletivoseplag.dtos.filters.LotacaoFilterDTO;
import com.example.desafioseletivoseplag.models.Lotacao;
import com.example.desafioseletivoseplag.models.enums.LayerEnum;
import com.example.desafioseletivoseplag.providers.exceptions.BusinessException;
import com.example.desafioseletivoseplag.providers.exceptions.DatabaseException;
import com.example.desafioseletivoseplag.providers.exceptions.ResourceNotFoundException;
import com.example.desafioseletivoseplag.repository.LotacaoRepository;
import com.example.desafioseletivoseplag.services.LotacaoService;
import com.example.desafioseletivoseplag.services.PessoaService;
import com.example.desafioseletivoseplag.services.UnidadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LotacaoServiceImpl implements LotacaoService {

    private final LotacaoRepository repository;
    private final UnidadeService unidadeService;
    private final PessoaService pessoaService;

    public LotacaoServiceImpl(LotacaoRepository repository, UnidadeService unidadeService, PessoaService pessoaService) {
        this.repository = repository;
        this.unidadeService = unidadeService;
        this.pessoaService = pessoaService;
    }

    @Override
    public Page<LotacaoDTO> findByFilter(LotacaoFilterDTO filter, Pageable pageable) {
        Page<Lotacao> lotacoes = repository.findByFilter(filter.getPortaria(),
                filter.getDataLotacao(), filter.getDataRemocao(), pageable);
        return lotacoes.map(LotacaoDTO::new);
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }

    @Override
    @Transactional
    public LotacaoDTO create(LotacaoDTO lotacaoDTO) {
        validarCamposObrigatorios(lotacaoDTO);
        try {
            Lotacao lotacao = new Lotacao();
            lotacao.setPortaria(lotacaoDTO.getPortaria());
            lotacao.setDataLotacao(lotacaoDTO.getDataLotacao());
            lotacao.setDataRemocao(lotacaoDTO.getDataRemocao());
            lotacao.setUnidade(unidadeService.findById(lotacaoDTO.getUnidade().getId()).toModel());
            lotacao.setPessoa(pessoaService.findById(lotacaoDTO.getPessoa().getId()).toModel());

            //Eaw, vai bota varias pessoas em  uma lotacao né? Ou vai ser uma por lotação?
//            if (lotacaoDTO.getPessoas() != null && !lotacaoDTO.getPessoas().isEmpty()) {
//                List<Pessoa> pessoas = lotacaoDTO.getPessoas().stream()
//                        .map(pessoaDTO -> pessoaService.findById(pessoaDTO.getId()).toModel())
//                        .collect(Collectors.toList());
//                lotacao.setPessoas(pessoas);
//            }

            Lotacao savedLotacao = repository.save(lotacao);
            return new LotacaoDTO(savedLotacao);
        } catch (DatabaseException e) {
            throw new DatabaseException ("Erro ao tentar salvar a Lotacao", this);
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);

    }


    @Override
    public List<LotacaoDTO> findAll() {
        return repository.findAll().stream().map(LotacaoDTO::new).collect(Collectors.toList());
    }

    @Override
    public LotacaoDTO findById(Long id) {
        return repository.findById(id).map(LotacaoDTO::new).orElseThrow(() -> new ResourceNotFoundException("Nenhuma Lotacao foi encontrada", this));
    }

    @Override
    @Transactional
    public LotacaoDTO update(LotacaoDTO lotacaoDTO, Long id) {
        validarCamposObrigatorios(lotacaoDTO);
        Lotacao lotacao = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lotacao não encontrada",this));
        try {
        lotacao.setPortaria(lotacaoDTO.getPortaria());
        lotacao.setDataLotacao(lotacaoDTO.getDataLotacao());
        lotacao.setDataRemocao(lotacaoDTO.getDataRemocao());
        lotacao.setUnidade(unidadeService.findById(lotacaoDTO.getUnidade().getId()).toModel());
        lotacao.setPessoa(pessoaService.findById(lotacaoDTO.getPessoa().getId()).toModel());

        // Se houverem pessoas associadas, atualiza a lista de pessoas?
//        if (lotacaoDTO.getPessoas() != null && !lotacaoDTO.getPessoas().isEmpty()) {
//            List<Pessoa> pessoas = lotacaoDTO.getPessoas().stream()
//                    .map(pessoaDTO -> pessoaService.findById(pessoaDTO.getId()).toModel())
//                    .collect(Collectors.toList());
//            lotacao.setPessoas(pessoas);
//        }

            Lotacao updatedLotacao = repository.save(lotacao);
            return new LotacaoDTO(updatedLotacao);
        } catch (DatabaseException e) {
            throw new DatabaseException("Erro ao tentar atualizar a Lotacao", this);
        }
    }

    private void validarCamposObrigatorios(LotacaoDTO lotacaoDTO) {
        if (lotacaoDTO.getPortaria() == null || lotacaoDTO.getPortaria().isEmpty()) {
            throw new BusinessException("Portaria é obrigatória.", this);
        }

        if (lotacaoDTO.getDataLotacao() == null) {
            throw new BusinessException("Data de Lotação é obrigatória.", this);
        }

        if (lotacaoDTO.getUnidade() == null || lotacaoDTO.getUnidade().getId() == null) {
            throw new BusinessException("Unidade é obrigatória.", this);
        }

        if (lotacaoDTO.getDataRemocao() != null && lotacaoDTO.getDataRemocao().isBefore(lotacaoDTO.getDataLotacao())) {
            throw new BusinessException("A data de remoção não pode ser anterior à data de lotação.", this);
        }
    }
}
