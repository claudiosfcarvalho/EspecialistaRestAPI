package com.claudiowork.algafood.domain.service;

import java.lang.reflect.Method;
import java.util.List;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiowork.algafood.domain.exception.EntidadeEmUsoException;
import com.claudiowork.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.claudiowork.algafood.domain.model.Cozinha;
import com.claudiowork.algafood.domain.model.Restaurante;
import com.claudiowork.algafood.domain.repository.RestauranteRepository;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class CadastroRestauranteService {

	@Autowired
	private SmartValidator smartValidator;

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cozinhaService;
	
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}
	
	public Restaurante buscarOuFalhar(Long id) {
		return restauranteRepository.findById(id).orElseThrow(()-> new RestauranteNaoEncontradoException(id));
	}
	
	public void remover(Long id) {
		try {
			
			restauranteRepository.deleteById(id);
		
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Restaurante de código %d não pode ser removida, pois está em uso", id));
		}
	}

	@SneakyThrows
	public void validate(Restaurante restaurante, String objectName){
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		smartValidator.validate(restaurante, bindingResult);
		if (!bindingResult.getAllErrors().isEmpty()) {
			Method method = this.getClass().getDeclaredMethods()[0];
			var methodParameter = new MethodParameter(method, 0);
			throw new MethodArgumentNotValidException(methodParameter,bindingResult);
		}
	}
}
