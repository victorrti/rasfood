package com.rasmoo.api.rasfood.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ordens")
public class Ordem {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="valor_total")
    private BigDecimal valorTotal = BigDecimal.valueOf(0.0);
    @Column(name="data_de_criacao")
    private LocalDate dataCriacao = LocalDate.now();
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(mappedBy = "ordem", cascade = CascadeType.ALL)
    private List<OrdemCardapio> listaOrdemCardapio = new ArrayList<OrdemCardapio>();

    public Ordem(){}

    public Ordem(Cliente cliente) {

        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<OrdemCardapio> getListaOrdemCardapio() {
        return listaOrdemCardapio;
    }

    public void setListaOrdemCardapio(List<OrdemCardapio> listaOrdemCardapio) {
        this.listaOrdemCardapio = listaOrdemCardapio;
    }

    public void addOrdemCardapio(OrdemCardapio ordemCardapio){
        ordemCardapio.setOrdem(this);
        this.listaOrdemCardapio.add(ordemCardapio);
    }

    @Override
    public String toString() {
        return "Ordem{" +
                "id=" + id +
                ", valorTotal=" + valorTotal +
                ", dataCriacao=" + dataCriacao +
                ", cliente=" + cliente +
                ", listaOrdemCardapio=" + listaOrdemCardapio +
                '}';
    }
}
