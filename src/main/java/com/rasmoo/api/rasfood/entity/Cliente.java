package com.rasmoo.api.rasfood.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clientes")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente {
    @EmbeddedId
    private ClienteId clienteId = new ClienteId();
    private String nome;

    private String cep;
    @Embedded
    private Contato contato;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<Endereco>();

    public Cliente(){}



    public Cliente(String nome,String email, String cpf, String cep) {
        ClienteId clienteId =  new ClienteId();
        clienteId.setCpf(cpf);
        clienteId.setEmail(email);
        this.clienteId = clienteId;
        this.nome = nome;
        this.cep = cep;
    }
    public void addEndereco(Endereco endereco){
        endereco.setCliente(this);
        this.getEnderecos().add(endereco);
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.clienteId.getCpf();
    }

    public void setCpf(String cpf) {
        this.clienteId.setCpf(cpf);
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public String getDDD(){
        return this.contato.getDdd();
    }

    public String getNumero(){
       return this.contato.getNumero();
    }

    public String getEmail(){
        return this.clienteId.getEmail();
    }
    public void setEmail(String email){
        this.clienteId.setEmail(email);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId=" + clienteId +
                ", nome='" + nome + '\'' +
                ", cep='" + cep + '\'' +
                ", contato=" + contato +
                ", enderecos=" + enderecos +
                '}';
    }
}
