package com.example.desafioseletivoseplag.controllers;

import com.example.desafioseletivoseplag.dtos.LotacaoDTO;
import com.example.desafioseletivoseplag.dtos.filters.LotacaoFilterDTO;
import com.example.desafioseletivoseplag.models.enums.LayerEnum;
import com.example.desafioseletivoseplag.providers.exceptions.LayerDefinition;
import com.example.desafioseletivoseplag.services.LotacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/lotacoes")
@Tag(name = "Lotação", description = "Gerenciamento de Lotações")
public class LotacaoController {

    @Autowired
    private LotacaoService lotacaoService;


    @Operation(
            summary = "Busca uma Lotação por ID",
            description = "Este endpoint retorna uma lotação específica com base no ID fornecido."
    )
    @ApiResponse(responseCode = "200", description = "Lotação encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Lotação não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<LotacaoDTO> findById(
            @Parameter(description = "ID da lotação a ser buscada") @PathVariable Long id) {

        Optional<LotacaoDTO> lotacao = Optional.ofNullable(lotacaoService.findById(id));
        return lotacao.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(
            summary = "Busca lotações com base no filtro",
            description = "Este endpoint retorna uma lista de lotações de acordo com o filtro fornecido. A resposta será paginada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lotações encontradas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/paginado")
    public ResponseEntity<Page<LotacaoDTO>> findByFilter(
            @Parameter(description = "Portaria, Data de Lotação e Data de Remoção")
            @Valid @RequestBody LotacaoFilterDTO filter,
            @Parameter(description = "Parâmetros de paginação") Pageable pageable) {

        try {
            Page<LotacaoDTO> result = lotacaoService.findByFilter(filter, pageable);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Lista todas as lotações",
            description = "Este endpoint retorna uma lista de todas as lotações cadastradas no sistema."
    )
    @ApiResponse(responseCode = "200", description = "Lista de lotações retornada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    @GetMapping
    public ResponseEntity<List<LotacaoDTO>> findAll() {
        try {
            List<LotacaoDTO> lotacoes = lotacaoService.findAll();
            return new ResponseEntity<>(lotacoes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Cadastra uma nova Lotação",
            description = "Este endpoint cadastra uma nova Lotação no sistema com os dados fornecidos."
    )
    @ApiResponse(responseCode = "201", description = "Lotação cadastrada com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro na solicitação (dados inválidos)")
    @PostMapping
    public ResponseEntity<LotacaoDTO> cadastrar(@RequestBody LotacaoDTO lotacaoDTO) {
        LotacaoDTO createdLotacao = lotacaoService.create(lotacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLotacao);
    }

    @Operation(
            summary = "Atualiza uma lotação existente",
            description = "Este endpoint atualiza os dados de uma lotação existente"
    )
    @ApiResponse(responseCode = "200", description = "Lotação atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Lotação não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<LotacaoDTO> update(@PathVariable Long id, @RequestBody LotacaoDTO lotacaoDTO) {
        LotacaoDTO updatedLotacao = lotacaoService.update(lotacaoDTO, id);
        if (updatedLotacao == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedLotacao, HttpStatus.OK);
    }

    @Operation(
            summary = "Deleta uma lotação",
            description = "Este endpoint permite excluir uma lotação com base no seu ID."
    )
    @ApiResponse(responseCode = "204", description = "Lotação deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Lotação não encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            lotacaoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
