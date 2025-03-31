package com.example.desafioseletivoseplag.services.impl;

import com.example.desafioseletivoseplag.dtos.ServidorEfetivoDTO;
import com.example.desafioseletivoseplag.models.ServidorEfetivo;
import com.example.desafioseletivoseplag.models.enums.LayerEnum;
import com.example.desafioseletivoseplag.repository.ServidorEfetivoRepository;
import com.example.desafioseletivoseplag.services.ServidorEfetivoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ServidorEfetivoServiceImpl implements ServidorEfetivoService {

    private final ServidorEfetivoRepository servidorEfetivoRepository;

    public ServidorEfetivoServiceImpl(ServidorEfetivoRepository servidorEfetivoRepository) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
    }

    @Override
    public ServidorEfetivoDTO create(ServidorEfetivoDTO servidorEfetivoDTO) {
        ServidorEfetivo servidorEfetivo = servidorEfetivoDTO.toModel();
        servidorEfetivo = servidorEfetivoRepository.save(servidorEfetivo);
        return new ServidorEfetivoDTO(servidorEfetivo);
    }

    @Override
    public void delete(Long pessoaId) {
        servidorEfetivoRepository.deleteById(pessoaId);
    }

    @Override
    public List<ServidorEfetivoDTO> findAll() {
        List<ServidorEfetivo> servidores = servidorEfetivoRepository.findAll();
        return servidores.stream()
                .map(servidor -> {
                    if (servidor.getPessoa() != null) {
                        return new ServidorEfetivoDTO(servidor, servidor.getPessoa());
                    }
                    return new ServidorEfetivoDTO(servidor);
                })
                .collect(Collectors.toList());
    }

    @Override
    public ServidorEfetivoDTO findById(Long pessoaId) {
        Optional<ServidorEfetivo> servidorEfetivo = servidorEfetivoRepository.findById(pessoaId);
        return servidorEfetivo.map(servidor -> new ServidorEfetivoDTO(servidor, servidor.getPessoa())).orElse(null);
    }

    @Override
    public ServidorEfetivoDTO update(ServidorEfetivoDTO servidorEfetivoDTO, Long pessoaId) {
        Optional<ServidorEfetivo> optionalServidorEfetivo = servidorEfetivoRepository.findById(pessoaId);
        if (optionalServidorEfetivo.isPresent()) {
            ServidorEfetivo servidorEfetivo = optionalServidorEfetivo.get();
            servidorEfetivo.setMatricula(servidorEfetivoDTO.getMatricula());
            servidorEfetivo = servidorEfetivoRepository.save(servidorEfetivo);
            return new ServidorEfetivoDTO(servidorEfetivo);
        }
        return null;
    }


    @Override
    public String getClassName() {
        return ServidorEfetivo.class.getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }
}
