package com.claudiowork.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudiowork.algafood.domain.exception.EntidadeEmUsoException;
import com.claudiowork.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.claudiowork.algafood.domain.model.Restaurante;
import com.claudiowork.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private CadastroRestauranteService restauranteService;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteService.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Restaurante restaurante = restauranteService.buscar(id);

		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Restaurante> adicionar(@RequestBody Restaurante restaurante) {
		restaurante = restauranteService.salvar(restaurante);
		return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		try {
			Restaurante restAtual = restauranteService.buscar(id);
			if (restAtual != null) {
				BeanUtils.copyProperties(restaurante, restAtual, "id");
				restaurante = restauranteService.salvar(restAtual);
				return ResponseEntity.status(HttpStatus.OK).body(restAtual);
			}
			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		try {
			Restaurante restAtual = restauranteService.buscar(id);
			if (restAtual != null) {
				merge(campos, restAtual);
				return atualizar(id, restAtual);
			}
			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		try {
			restauranteService.remover(id);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
		camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			// busca o campo na entidade
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);

			// permite acessar o atributo privado do Objeto Restaurante
			field.setAccessible(true);

			//obtem o valor convertido no tipo correto. ex de integer para bigdecimal
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
}
