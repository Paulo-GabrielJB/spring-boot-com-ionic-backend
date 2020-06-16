package com.curso.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.curso.spring.models.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	@Transactional(readOnly =  true)
	@Query("SELECT obj FROM Cidade obj "
			+ "WHERE obj.estado.id = :estado "
			+ "ORDER BY obj.nome")
	List<Cidade> findCidades(@Param("estado") Long id_estado);

}
