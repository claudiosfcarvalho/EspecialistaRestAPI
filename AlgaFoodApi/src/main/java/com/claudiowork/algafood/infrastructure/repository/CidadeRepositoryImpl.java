package com.claudiowork.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.claudiowork.algafood.domain.model.Cidade;
import com.claudiowork.algafood.domain.repository.CidadeRepository;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cidade> listar() {
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long id) {
		return manager.find(Cidade.class, id);
	}

	@Override
	@Transactional
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Cidade cidade = buscar(id);
		
		if (cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(cidade);
	}

}
