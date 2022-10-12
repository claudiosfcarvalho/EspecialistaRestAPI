package com.claudiowork.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiowork.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.claudiowork.algafood.domain.exception.EntidadeEmUsoException;
import com.claudiowork.algafood.domain.model.Cozinha;
import com.claudiowork.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public Cozinha buscarOuFalhar(Long id) {
		return cozinhaRepository.findById(id).orElseThrow(() -> new CozinhaNaoEncontradaException(id)); 
	}
	
	public void remover(Long id) {
		try {

			cozinhaRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);
			

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_COZINHA_EM_USO, id));
		}
	}
	
}
