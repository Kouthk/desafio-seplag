package com.example.desafioseletivoseplag.controllers;

import com.example.desafioseletivoseplag.dtos.PessoaDTO;
import com.example.desafioseletivoseplag.dtos.filters.PessoaFilterDTO;
import com.example.desafioseletivoseplag.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pessoas")
@Tag(name = "Pessoa", description = "Gerenciamento de Pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Operation(summary = "Cria uma nova pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO createdPessoa = pessoaService.create(pessoaDTO);
        return new ResponseEntity<>(createdPessoa, HttpStatus.CREATED);
    }

    @Operation(summary = "Recupera todas as pessoas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas recuperadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma pessoa encontrada")
    })
    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
        List<PessoaDTO> pessoas = pessoaService.findAll();
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @Operation(summary = "Recupera uma pessoa pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@Parameter(description = "ID da pessoa") @PathVariable Long id) {
        PessoaDTO pessoaDTO = pessoaService.findById(id);
        if (pessoaDTO != null) {
            return new ResponseEntity<>(pessoaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Atualiza os dados de uma pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO updatedPessoa = pessoaService.update(pessoaDTO, id);
        if (updatedPessoa != null) {
            return new ResponseEntity<>(updatedPessoa, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Deleta uma pessoa pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            pessoaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Filtra pessoas com base em critérios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas filtradas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Filtro inválido")
    })
    @PostMapping("/filter")
    public ResponseEntity<Page<PessoaDTO>> findByFilter(@RequestBody PessoaFilterDTO filter, Pageable pageable) {
        Page<PessoaDTO> pessoas = pessoaService.findByFilter(filter, pageable);
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }
}
