package com.claudiowork.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiowork.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{

	List<Cozinha> findAllByNome(String nome);
	
	List<Cozinha> findAllByNomeContaining(String nome);
	
	Optional<Cozinha> findByNome(String nome);
	
}
