package com.claudiowork.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudiowork.algafood.domain.model.Cozinha;
import com.claudiowork.algafood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping(value = "/cozinhas") //, produces = MediaType.APPLICATION_JSON_VALUE
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    /*
     * content negotiation - é possível especificar um ou mais tipos de conteúdo aceito
     * neste caso xml ou Json (para o xml é necessario adicionar a dependencia no pom
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    */
    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }
    
    @GetMapping("/{id}")
    public Cozinha buscar(@PathVariable Long id) {
    	return cozinhaRepository.buscar(id);
    }

}
