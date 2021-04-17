package com.claudiowork.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiowork.algafood.domain.exception.EntidadeEmUsoException;
import com.claudiowork.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.claudiowork.algafood.domain.model.Cidade;
import com.claudiowork.algafood.domain.model.Estado;
import com.claudiowork.algafood.domain.repository.CidadeRepository;
import com.claudiowork.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(estadoId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Estado com o id %d não encontrado", estadoId)));

		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);

	}

	public Optional<Cidade> buscar(Long id) {
		return cidadeRepository.findById(id);
	}

	public void remover(Long id) {
		try {

			cidadeRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade de código %d não encontrada", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade de código %d não pode ser removida, pois está em uso", id));
		}
	}
}
