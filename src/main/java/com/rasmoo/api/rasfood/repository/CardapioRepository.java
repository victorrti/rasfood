package com.rasmoo.api.rasfood.repository;

import com.rasmoo.api.rasfood.dto.CardapioDto;
import com.rasmoo.api.rasfood.entity.Cardapio;
import com.rasmoo.api.rasfood.repository.projection.CardapioProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio,Integer> {
    @Query("select new com.rasmoo.api.rasfood.entity.Cardapio( c.nome , c.descricao, c.valor , c.categoria.nome) Cardapio c where c.nome like %:nome%")
    List<CardapioDto> findByNome(String nome);
    @Query(value = "select c.nome as nome , " +
            " c.descricao  as descricao , " +
            " c.valor  as valor , " +
            " cat.nome as categoria " +
            " from cardapio c " +
            " join categorias cat on c.id = cat.id " +
            "where c.id = ?id",nativeQuery = true)
    List<CardapioProjection> findAllByCategoria(final Integer id);
    @Modifying
    @Transactional
    @Query("UPDATE cardapio c set c = CASE c.dispobilidade WHEN treu THEN false ELSE true END WHERE c.id = :id ")
    Integer updateDisponibilidade(@Param("id") Integer id);
}
