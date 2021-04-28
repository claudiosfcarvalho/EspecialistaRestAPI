package com.claudiowork.algafood.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.claudiowork.algafood.domain.model.Cozinha;
import com.claudiowork.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	private CadastroCozinhaService cozinhaService;

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaService.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Optional<Cozinha> c = cozinhaService.buscar(id);
		if (c.isPresent()) {
			return ResponseEntity.ok(c.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
		Cozinha c = cozinhaService.salvar(cozinha);
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
		Optional<Cozinha> c = cozinhaService.buscar(id);
		if (c.isPresent()) {
			BeanUtils.copyProperties(cozinha, c.get(), "id");
			return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.salvar(c.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cozinhaService.remover(id);
	}

}
