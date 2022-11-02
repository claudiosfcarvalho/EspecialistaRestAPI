package com.claudiowork.algafood.api.controller;

import com.claudiowork.algafood.api.utils.Utilitario;
import com.claudiowork.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.claudiowork.algafood.domain.exception.NegocioException;
import com.claudiowork.algafood.domain.groups_validation.Groups;
import com.claudiowork.algafood.domain.model.Restaurante;
import com.claudiowork.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
	public Restaurante adicionar(@RequestBody @Validated(value = Groups.CozinhaId.class) Restaurante restaurante) {
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
	public Restaurante atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
		Restaurante restAtual = restauranteService.buscarOuFalhar(id);
		Utilitario.merge(campos, restAtual, request);
		return atualizar(id, restAtual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		restauranteService.remover(id);
	}

}
