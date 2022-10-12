package com.claudiowork.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiowork.algafood.domain.exception.CidadeNaoEncontradaException;
import com.claudiowork.algafood.domain.exception.EntidadeEmUsoException;
import com.claudiowork.algafood.domain.model.Cidade;
import com.claudiowork.algafood.domain.model.Estado;
import com.claudiowork.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroEstadoService estadoService;

	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoService.buscarOuFalhar(estadoId);

		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);

	}

	public Cidade buscarOuFalhar(Long id) {
		return cidadeRepository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id));
	}

	
	public void remover(Long id) {
		try {

			cidadeRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, id));
		}
	}
}
