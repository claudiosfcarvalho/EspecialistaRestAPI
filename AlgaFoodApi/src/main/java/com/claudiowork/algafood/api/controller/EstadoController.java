package com.claudiowork.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudiowork.algafood.domain.model.Estado;
import com.claudiowork.algafood.domain.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository EstadoRepository;


    @GetMapping
    public List<Estado> listar() {
        return EstadoRepository.listar();
    }

}
