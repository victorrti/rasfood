package com.rasmoo.api.rasfood.repository.especification;

import com.rasmoo.api.rasfood.entity.Cardapio;
import org.springframework.data.jpa.domain.Specification;

public class CardapioEspc {

    public static Specification<Cardapio> nome(String nome){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nome"),"%"+nome+"%");
    }
    public static Specification<Cardapio> disponibilidade(Boolean disponilidade){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("disponibilidade"),disponilidade);
    }
    public static Specification<Cardapio> categoria(Integer categoria){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoria"),categoria);
    }
}
