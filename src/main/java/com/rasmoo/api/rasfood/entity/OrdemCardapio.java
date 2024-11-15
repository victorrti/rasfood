package com.rasmoo.api.rasfood.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="ordens_cardapio")
public class OrdemCardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Ordem ordem;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cardapio cardapio;
    private BigDecimal valor;
    private Integer quantidade;

    public OrdemCardapio(){}

    public OrdemCardapio(Ordem ordem, Cardapio cardapio, Integer quantidade) {
        this.ordem = ordem;
        this.cardapio = cardapio;
        this.quantidade = quantidade;
        this.valor = (cardapio.getValor().multiply(BigDecimal.valueOf(quantidade)));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem ordem) {
        this.ordem = ordem;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "OrdemCardapio{" +
                "id=" + id +
                ", ordem=" + ordem +
                ", cardapio=" + cardapio +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                '}';
    }
}
