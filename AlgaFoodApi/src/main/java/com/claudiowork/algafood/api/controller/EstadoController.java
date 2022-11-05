package com.claudiowork.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.claudiowork.algafood.domain.model.Estado;
import com.claudiowork.algafood.domain.service.CadastroEstadoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private CadastroEstadoService estadoService;

	@GetMapping
	public List<Estado> listar() {
		return estadoService.listar();
	}

	@GetMapping("/{id}")
	public Estado buscar(@PathVariable Long id) {
		return estadoService.buscarOuFalhar(id);
	}

	@PostMapping
	public ResponseEntity<Estado> adicionar(@Valid @RequestBody Estado estado) {
		estado = estadoService.salvar(estado);
		return ResponseEntity.status(HttpStatus.CREATED).body(estado);
	}

	@PutMapping("/{id}")
	public Estado atualizar(@PathVariable Long id, @Valid @RequestBody Estado estado) {
		Estado estAtual = estadoService.buscarOuFalhar(id);
		BeanUtils.copyProperties(estado, estAtual, "id");
		return estadoService.salvar(estAtual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		estadoService.remover(id);
	}

}
