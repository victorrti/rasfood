package com.rasmoo.api.rasfood.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasmoo.api.rasfood.entity.Cliente;
import com.rasmoo.api.rasfood.entity.ClienteId;
import com.rasmoo.api.rasfood.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
   public ResponseEntity<List<Cliente>> findAll(){
    return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    @GetMapping("{email}/{cpf}")
    public ResponseEntity<Cliente> findByEmailCpf(@PathVariable("email") final String email,@PathVariable("cpf") final String cpf){

       return clienteRepository.findById(new ClienteId(email,cpf))
               .map(value ->  ResponseEntity.status(HttpStatus.OK).body(value))
               .orElseGet(() ->  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));

    }
    @PatchMapping("/id")
    public ResponseEntity<Cliente> atualizar(@PathVariable("id") final String id, @RequestBody final Cliente cliente) throws JsonMappingException {

        Optional<Cliente> clienteOptional = clienteRepository.findByEmailOrCpf(id);
        if(clienteOptional.isPresent()){
            objectMapper.updateValue(clienteOptional.get(),cliente);
            return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(clienteOptional.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        //Cliente clienteENCONTRADO = clienteOptional.get();
    }
}
