package com.claudiowork.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.claudiowork.algafood.domain.model.Cozinha;
import com.claudiowork.algafood.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cozinha> listar() {
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}

	@Override
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		if (cozinha != null) {
			Cozinha cValid = new Cozinha();
			cValid.setNome(null);
		}
		return manager.merge(cozinha);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Cozinha c = buscar(id);
		
		if (c == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(c);
	}

	@Override
	public List<Cozinha> consultarPorNomeEstudo(String nome) {
		return manager.createQuery("from Cozinha where nome like :nome",Cozinha.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
				
	}

}
