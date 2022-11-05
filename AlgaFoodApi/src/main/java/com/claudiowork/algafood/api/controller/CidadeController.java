package com.claudiowork.algafood.api.controller;

import com.claudiowork.algafood.domain.exception.EstadoNaoEncontradoException;
import com.claudiowork.algafood.domain.exception.NegocioException;
import com.claudiowork.algafood.domain.model.Cidade;
import com.claudiowork.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cidadeService;

	@GetMapping
	public List<Cidade> listar() {
		return cidadeService.listar();
	}

	@GetMapping("/{id}")
	public Cidade buscar(@PathVariable Long id) {
		return cidadeService.buscarOuFalhar(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@Valid @RequestBody Cidade cidade) {
		try {
			return cidadeService.salvar(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{id}")
	public Cidade atualizar(@PathVariable Long id, @Valid @RequestBody Cidade cidade) {
		try {
			Cidade cidAtual = cidadeService.buscarOuFalhar(id);
			BeanUtils.copyProperties(cidade, cidAtual, "id");
			return cidadeService.salvar(cidAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cidadeService.remover(id);
	}

}
