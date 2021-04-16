package com.claudiowork.algafood.domain.repository;

import java.util.List;

import com.claudiowork.algafood.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> listar();
	List<Cozinha> consultarPorNomeEstudo(String nomeß);
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Long id);
	
}
