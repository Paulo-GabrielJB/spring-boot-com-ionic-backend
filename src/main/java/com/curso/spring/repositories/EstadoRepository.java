package com.curso.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.curso.spring.models.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	@Transactional(readOnly =  true)
	List<Estado> findAllByOrderByNome();
	
}
