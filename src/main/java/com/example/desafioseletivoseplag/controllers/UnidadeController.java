package com.example.desafioseletivoseplag.controllers;

import com.example.desafioseletivoseplag.dtos.UnidadeDTO;
import com.example.desafioseletivoseplag.dtos.filters.UnidadeFilterDTO;
import com.example.desafioseletivoseplag.models.enums.LayerEnum;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.services.UnidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/unidades")
@Tag(name = "Unidade", description = "Gerenciamento de Unidades")
public class UnidadeController {

    private final UnidadeService service;

    public UnidadeController(UnidadeService service) {
        this.service = service;
    }

    @Operation(summary = "Busca unidades com paginação", description = "Filtra unidades com base nos critérios fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de unidades paginada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/paginado")
    public Page<UnidadeDTO> findByFilter(@RequestBody UnidadeFilterDTO filter, Pageable pageable) {
        return service.findByFilter(filter, pageable);
    }

    @PostMapping("/paginado/endereco-unidade-por-nome-servidor")
    public Page<UnidadeDTO> findUnidadeComEnderecosByServidorNomeParte(@RequestBody String nomeServidor, Pageable pageable) {
        return service.findUnidadeComEnderecosByServidorNomeParte(nomeServidor, pageable).map(UnidadeDTO::new);
    }

    @Operation(summary = "Cria uma nova unidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Unidade criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação")
    })
    @PostMapping
    public UnidadeDTO create(@RequestBody UnidadeDTO unidadeDTO) {
        return service.create(unidadeDTO);
    }

    @Operation(summary = "Exclui uma unidade pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Unidade deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "Lista todas as unidades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de unidades retornada com sucesso")
    })
    @GetMapping
    public List<UnidadeDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Busca uma unidade pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unidade encontrada"),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @GetMapping("/{id}")
    public UnidadeDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Atualiza uma unidade pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unidade atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização"),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @PutMapping("/{id}")
    public UnidadeDTO update(@RequestBody UnidadeDTO unidadeDTO, @PathVariable Long id) {
        return service.update(unidadeDTO, id);
    }

}
