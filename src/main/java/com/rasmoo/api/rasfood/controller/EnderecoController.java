package com.rasmoo.api.rasfood.controller;

import com.rasmoo.api.rasfood.entity.Endereco;
import com.rasmoo.api.rasfood.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @GetMapping("/cep/{cep}")
    public ResponseEntity<List<Endereco>> findEnderecoByCep(@PathVariable("cep") final String cep){
        List<Endereco> listaEndereco = enderecoRepository.findByCep(cep);
        return ResponseEntity.status(HttpStatus.OK).body(listaEndereco);

    }



}
