package com.claudiowork.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiowork.algafood.domain.exception.EntidadeEmUsoException;
import com.claudiowork.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.claudiowork.algafood.domain.model.Estado;
import com.claudiowork.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public Optional<Estado> buscar(Long id) {
		return estadoRepository.findById(id);
	}
	
	public void remover(Long id) {
		try {
			
			estadoRepository.deleteById(id);
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Estado de código %d não encontrada", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removida, pois está em uso", id));
		}
	}
}
