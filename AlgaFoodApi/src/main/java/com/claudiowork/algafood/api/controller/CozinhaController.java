package com.claudiowork.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudiowork.algafood.api.model.CozinhasXmlWrapper;
import com.claudiowork.algafood.domain.exception.EntidadeEmUsoException;
import com.claudiowork.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.claudiowork.algafood.domain.model.Cozinha;
import com.claudiowork.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value = "/cozinhas") // , produces = MediaType.APPLICATION_JSON_VALUE
public class CozinhaController {

	@Autowired
	private CadastroCozinhaService cozinhaService;

	/*
	 * content negotiation - é possível especificar um ou mais tipos de conteúdo
	 * aceito neste caso xml ou Json (para o xml é necessario adicionar a
	 * dependencia no pom
	 * 
	 * @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
	 * MediaType.APPLICATION_XML_VALUE})
	 */
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaService.listar();
	}

//	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//	public CozinhasXmlWrapper listarXml() {
//		return new CozinhasXmlWrapper(cozinhaService.listar());
//	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Cozinha c = cozinhaService.buscar(id);
		if (c != null) {
			return ResponseEntity.ok(c);
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
		Cozinha c = cozinhaService.buscar(id);
		if (c != null) {
			BeanUtils.copyProperties(cozinha, c, "id");
			cozinha = cozinhaService.salvar(c);
			return ResponseEntity.status(HttpStatus.OK).body(c);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
		try {
			cozinhaService.remover(id);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
