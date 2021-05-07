package com.claudiowork.algafood.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.claudiowork.algafood.api.utils.Utilitario;
import com.claudiowork.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.claudiowork.algafood.domain.exception.NegocioException;
import com.claudiowork.algafood.domain.model.Restaurante;
import com.claudiowork.algafood.domain.service.CadastroRestauranteService;

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
	public Restaurante buscar(@PathVariable Long id) {
		return restauranteService.buscarOuFalhar(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {
		try {
			return restauranteService.salvar(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public Restaurante atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		Restaurante restAtual = restauranteService.buscarOuFalhar(id);
		BeanUtils.copyProperties(restaurante, restAtual, "id", "formasPagamento", "endereco", "dataCadastro",
				"produtos");
		try {
			return restauranteService.salvar(restAtual);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PatchMapping("/{id}")
	public Restaurante atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		Restaurante restAtual = restauranteService.buscarOuFalhar(id);
		Utilitario.merge(campos, restAtual);
		return atualizar(id, restAtual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		restauranteService.remover(id);
	}

}
