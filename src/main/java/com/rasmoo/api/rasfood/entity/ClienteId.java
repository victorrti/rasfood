package com.rasmoo.api.rasfood.entity;


import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class ClienteId implements Serializable {
    private String email;
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClienteId(){}

    public ClienteId(String email, String cpf) {
        this.email = email;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "ClienteId{" +
                "email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
