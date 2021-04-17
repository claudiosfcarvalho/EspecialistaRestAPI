package com.claudiowork.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiowork.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{

	//List<Cozinha> consultarPorNomeEstudo(String nomeß);
	
}
