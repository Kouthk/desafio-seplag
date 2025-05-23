package com.example.desafioseletivoseplag.repository;

import com.example.desafioseletivoseplag.models.Lotacao;
import com.example.desafioseletivoseplag.models.Unidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LotacaoRepository  extends JpaRepository<Lotacao, Long> {

    @Query("SELECT l " +
            "FROM Lotacao l " +
            "JOIN l.pessoa p " +
            "JOIN ServidorEfetivo se ON p.id = se.id " +
            "WHERE l.unidade.id = :unidadeId")
    Page<Lotacao> findServidorEfetivoByUnidadeId(Long unidadeId, Pageable pageable);

    @Query("SELECT l " +
            "FROM Lotacao l " +
            "JOIN l.pessoa p " +
            "JOIN ServidorTemporario se ON p.id = se.id " +
            "WHERE l.unidade.id = :unidadeId")
    Page<Lotacao> findServidorTemporarioByUnidadeId(Long unidadeId, Pageable pageable);


    @Query("""
    SELECT l
    FROM Lotacao l
    WHERE (:portaria IS NULL OR UPPER(l.portaria) LIKE CONCAT('%', CONCAT(UPPER(:portaria), '%')))
    OR (:dataLotacao IS NULL AND :dataRemocao IS NULL
        OR (:dataLotacao IS NOT NULL AND :dataRemocao IS NULL AND l.dataLotacao = :dataLotacao)
        OR (:dataLotacao IS NULL AND :dataRemocao IS NOT NULL AND l.dataRemocao = :dataRemocao)
        OR (:dataLotacao IS NOT NULL AND :dataRemocao IS NOT NULL
            AND l.dataLotacao BETWEEN :dataLotacao AND :dataRemocao)
        )
    """)
    Page<Lotacao> findByFilter(String portaria, LocalDate dataLotacao, LocalDate dataRemocao, Pageable pageable);

}
