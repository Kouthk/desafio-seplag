package com.example.desafioseletivoseplag.services.impl;


import com.example.desafioseletivoseplag.dtos.ServidorTemporarioDTO;
import com.example.desafioseletivoseplag.models.ServidorTemporario;
import com.example.desafioseletivoseplag.models.enums.LayerEnum;
import com.example.desafioseletivoseplag.repository.ServidorTemporarioRepository;
import com.example.desafioseletivoseplag.services.ServidorTemporarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServidorTemporarioServiceImpl implements ServidorTemporarioService {

    private final ServidorTemporarioRepository servidorTemporarioRepository;

    public ServidorTemporarioServiceImpl(ServidorTemporarioRepository servidorTemporarioRepository) {
        this.servidorTemporarioRepository = servidorTemporarioRepository;
    }

    @Override
    public ServidorTemporarioDTO create(ServidorTemporarioDTO servidorTemporarioDTO) {
        ServidorTemporario servidorTemporario = servidorTemporarioDTO.toModel();
        servidorTemporario = servidorTemporarioRepository.save(servidorTemporario);
        return new ServidorTemporarioDTO(servidorTemporario);
    }

    @Override
    public void delete(Long aLong) {
        servidorTemporarioRepository.deleteById(aLong);
    }

    @Override
    public List<ServidorTemporarioDTO> findAll() {
        return servidorTemporarioRepository.findAll().stream()
                .map(ServidorTemporarioDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ServidorTemporarioDTO findById(Long aLong) {
        return servidorTemporarioRepository.findById(aLong)
                .map(ServidorTemporarioDTO::new)
                .orElse(null);
    }

    @Override
    public ServidorTemporarioDTO update(ServidorTemporarioDTO servidorTemporarioDTO, Long aLong) {
        if (!servidorTemporarioRepository.existsById(aLong)) {
            return null;
        }
        ServidorTemporario servidorTemporario = servidorTemporarioDTO.toModel();
        servidorTemporario.setPessoaId(aLong);
        servidorTemporario = servidorTemporarioRepository.save(servidorTemporario);
        return new ServidorTemporarioDTO(servidorTemporario);
    }

    @Override
    public String getClassName() {
        return "ServidorTemporario";
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }
}
