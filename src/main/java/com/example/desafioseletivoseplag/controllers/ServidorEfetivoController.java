package com.example.desafioseletivoseplag.controllers;

import com.example.desafioseletivoseplag.dtos.ServidorEfetivoDTO;
import com.example.desafioseletivoseplag.models.ServidorEfetivo;
import com.example.desafioseletivoseplag.services.ServidorEfetivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/servidores-efetivos")
@Tag(name = "Servidor Efetivo", description = "Gerenciamento de servidores efetivos")
public class ServidorEfetivoController {

    private final ServidorEfetivoService servidorEfetivoService;

    public ServidorEfetivoController(ServidorEfetivoService servidorEfetivoService) {
        this.servidorEfetivoService = servidorEfetivoService;
    }

    @PostMapping
    @Operation(summary = "Criar um novo servidor efetivo", description = "Adiciona um novo servidor efetivo ao sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servidor efetivo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<ServidorEfetivoDTO> create(@RequestBody ServidorEfetivoDTO servidorEfetivoDTO) {
        ServidorEfetivoDTO created = servidorEfetivoService.create(servidorEfetivoDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todos os servidores efetivos", description = "Retorna uma lista de todos os servidores efetivos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<ServidorEfetivoDTO>> findAll() {
        List<ServidorEfetivoDTO> servidores = servidorEfetivoService.findAll();
        return new ResponseEntity<>(servidores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar servidor efetivo por ID", description = "Retorna os detalhes de um servidor efetivo específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidor efetivo encontrado"),
            @ApiResponse(responseCode = "404", description = "Servidor efetivo não encontrado")
    })
    public ResponseEntity<ServidorEfetivoDTO> findById(@PathVariable Long id) {
        ServidorEfetivoDTO servidorEfetivoDTO = servidorEfetivoService.findById(id);
        if (servidorEfetivoDTO != null) {
            return new ResponseEntity<>(servidorEfetivoDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um servidor efetivo", description = "Atualiza os dados de um servidor efetivo existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidor efetivo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Servidor efetivo não encontrado")
    })
    public ResponseEntity<ServidorEfetivoDTO> update(@PathVariable Long id, @RequestBody ServidorEfetivoDTO servidorEfetivoDTO) {
        ServidorEfetivoDTO updated = servidorEfetivoService.update(servidorEfetivoDTO, id);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um servidor efetivo", description = "Remove um servidor efetivo do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servidor efetivo removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Servidor efetivo não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servidorEfetivoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
