package com.rasmoo.api.rasfood.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cardapio")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private Boolean disponivel;
    private BigDecimal valor;
    @Column(name="data_de_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    @OneToMany(mappedBy = "cardapio")
    private List<OrdemCardapio> listaOrdemCardapio = new ArrayList<OrdemCardapio>();

    public Cardapio(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<OrdemCardapio> getListaOrdemCardapio() {
        return listaOrdemCardapio;
    }

    public void setListaOrdemCardapio(List<OrdemCardapio> listaOrdemCardapio) {
        this.listaOrdemCardapio = listaOrdemCardapio;
    }


}
