package com.example.desafioseletivoseplag.controllers;

import com.example.desafioseletivoseplag.dtos.ServidorTemporarioDTO;
import com.example.desafioseletivoseplag.services.ServidorTemporarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/servidores-temporarios")
@Tag(name = "Servidor Temporário", description = "Gerenciamento de servidores temporários")
public class ServidorTemporarioController {

    private final ServidorTemporarioService servidorTemporarioService;

    public ServidorTemporarioController(ServidorTemporarioService servidorTemporarioService) {
        this.servidorTemporarioService = servidorTemporarioService;
    }

    @PostMapping
    @Operation(summary = "Cria um novo servidor temporário", description = "Adiciona um novo servidor temporário ao sistema.")
    @ApiResponse(responseCode = "201", description = "Servidor temporário criado com sucesso")
    public ResponseEntity<ServidorTemporarioDTO> create(@RequestBody ServidorTemporarioDTO servidorTemporarioDTO) {
        ServidorTemporarioDTO created = servidorTemporarioService.create(servidorTemporarioDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Lista todos os servidores temporários", description = "Retorna uma lista de todos os servidores temporários cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de servidores temporários recuperada com sucesso")
    public ResponseEntity<List<ServidorTemporarioDTO>> findAll() {
        List<ServidorTemporarioDTO> servidores = servidorTemporarioService.findAll();
        return new ResponseEntity<>(servidores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um servidor temporário por ID", description = "Retorna os detalhes de um servidor temporário específico pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Servidor temporário encontrado"),
            @ApiResponse(responseCode = "404", description = "Servidor temporário não encontrado")
    })
    public ResponseEntity<ServidorTemporarioDTO> findById(@PathVariable Long id) {
        ServidorTemporarioDTO servidor = servidorTemporarioService.findById(id);
        return servidor != null ? new ResponseEntity<>(servidor, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um servidor temporário", description = "Modifica os dados de um servidor temporário existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Servidor temporário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Servidor temporário não encontrado")
    })
    public ResponseEntity<ServidorTemporarioDTO> update(@RequestBody ServidorTemporarioDTO servidorTemporarioDTO, @PathVariable Long id) {
        ServidorTemporarioDTO updated = servidorTemporarioService.update(servidorTemporarioDTO, id);
        return updated != null ? new ResponseEntity<>(updated, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um servidor temporário", description = "Remove um servidor temporário do sistema pelo seu ID.")
    @ApiResponse(responseCode = "204", description = "Servidor temporário removido com sucesso")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servidorTemporarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
