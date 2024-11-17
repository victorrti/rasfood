package com.rasmoo.api.rasfood.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasmoo.api.rasfood.dto.CardapioDto;
import com.rasmoo.api.rasfood.entity.Cardapio;
import com.rasmoo.api.rasfood.entity.Cliente;
import com.rasmoo.api.rasfood.repository.CardapioRepository;
import com.rasmoo.api.rasfood.repository.especification.CardapioEspc;
import com.rasmoo.api.rasfood.repository.projection.CardapioProjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {
    @Autowired
    private CardapioRepository cardapioRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<Page<Cardapio>> findAll(@RequestParam("pagina") Integer pagina,
                                                  @RequestParam(value = "sort",required = false) Sort.Direction sort,
                                                  @RequestParam(value = "propriedade",required = false) String propert){
        Pageable pageable = Objects.nonNull(sort) ?
                PageRequest.of(pagina,10, Sort.by(sort,propert)) : PageRequest.of(pagina,10);

        return ResponseEntity.status(HttpStatus.OK).body(cardapioRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cardapio> findByid(@PathVariable("id") final Integer id){

        return cardapioRepository.findById(id)
                .map(value ->  ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseGet(() ->  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));

    }
     @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Integer id){

        Optional<Cardapio> cardapioOptional = cardapioRepository.findById(id);
        if(cardapioOptional.isPresent()){
            cardapioRepository.delete(cardapioOptional.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);


    }
   @PostMapping()
    public ResponseEntity<Cardapio> criar( @RequestBody final Cardapio cardapio){
        if(Objects.nonNull(cardapio)){
            return ResponseEntity.status(HttpStatus.CREATED).body(cardapioRepository.save(cardapio));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);


    }
    @PatchMapping("/id")
    public ResponseEntity<Cardapio> atualizar(@PathVariable("id") final Integer id, @RequestBody final Cardapio Cardapio) throws JsonMappingException {

        Optional<Cardapio> CardapioOptional = cardapioRepository.findById(id);
        if(CardapioOptional.isPresent()){
            objectMapper.updateValue(CardapioOptional.get(),Cardapio);
            return ResponseEntity.status(HttpStatus.OK).body(cardapioRepository.save(CardapioOptional.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);


    }
    @GetMapping("/categoria/{id}/disponivel")
    public ResponseEntity<Page<CardapioProjection>> findAllCardapioByCategoria(@PathVariable("id") final Integer id,@RequestParam("pagina") Integer pagina){
        Pageable pageable = PageRequest.of(pagina,10);
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRepository.findAllByCategoria(id,pageable));
    }
    @GetMapping("/{nome}")
    public ResponseEntity<Page<Cardapio>> findAllCardapioByNomeCategoria(@PathVariable("id") final String nome,@RequestParam("pagina") Integer pagina){
        Pageable pageable = PageRequest.of(pagina,10);
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRepository.findAll(Specification.where(CardapioEspc.nome(nome)).and(CardapioEspc.disponibilidade(Boolean.TRUE)),pageable));
    }


}
