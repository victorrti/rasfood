package com.rasmoo.api.rasfood.repository;

import com.rasmoo.api.rasfood.dto.CardapioDto;
import com.rasmoo.api.rasfood.entity.Cardapio;
import com.rasmoo.api.rasfood.entity.Cliente;
import com.rasmoo.api.rasfood.repository.projection.CardapioProjection;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CardapioRepository extends JpaRepository<Cardapio,Integer> , JpaSpecificationExecutor<Cardapio> {
    @Query("SELECT new com.rasmoo.api.rasfood.dto.CardapioDto(c.nome, c.descricao, c.valor, c.categoria.nome) " +
            "FROM Cardapio c WHERE c.nome LIKE %:nome% AND c.disponivel = true")
    Page<CardapioDto> findByNome(String nome,final Pageable pageable);
    @Query(value = "select c.nome as nome , " +
            " c.descricao  as descricao , " +
            " c.valor  as valor , " +
            " cat.nome as categoria " +
            " from cardapio c " +
            " join categorias cat on c.categoria_id = cat.id " +
            " where c.id = ?1",
            nativeQuery = true,
    countQuery = "select count(*) from Cardapio")
    Page<CardapioProjection> findAllByCategoria(final Integer id, final Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Cardapio c SET c.disponivel = " +
            "CASE c.disponivel " +
            "WHEN true THEN false " +
            "ELSE true END " +
            "WHERE c.id = :id")
    Integer updateDisponibilidade(@Param("id") Integer id);


}
