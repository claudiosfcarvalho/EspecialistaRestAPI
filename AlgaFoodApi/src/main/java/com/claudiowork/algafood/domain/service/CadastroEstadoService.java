package com.claudiowork.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiowork.algafood.domain.exception.EntidadeEmUsoException;
import com.claudiowork.algafood.domain.exception.EstadoNaoEncontradoException;
import com.claudiowork.algafood.domain.model.Estado;
import com.claudiowork.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public Estado buscarOuFalhar(Long id) {
		return estadoRepository.findById(id).orElseThrow(()->new EstadoNaoEncontradoException(id));
	}
	
	public void remover(Long id) {
		try {
			
			estadoRepository.deleteById(id);
		
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, id));
		}
	}
}
