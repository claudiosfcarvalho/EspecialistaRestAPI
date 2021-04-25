package com.claudiowork.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claudiowork.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository
		extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

	//o fetch força o select jpql apenas em um select, caso contrario será feito varios selects para a forma pagamento
	//@Query("from Restaurante r join r.cozinha join fetch r.formasPagamento")
	@Query("from Restaurante r join r.cozinha")
	List<Restaurante> findAll();
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

	Optional<Restaurante> findFirstByNomeContaining(String nome);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	Boolean existsByNome(String nome);

	Integer countByCozinhaId(Long cozinhaId);

	// @Query("from Restaurante where nome like %:nome% and cozinha.id =:id")
	List<Restaurante> consultaPorNome(String nome, @Param("id") Long cozinha);

}
