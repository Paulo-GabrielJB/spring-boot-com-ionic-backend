package com.curso.spring.services;

import java.util.List;

import com.curso.spring.models.entities.Cidade;

public interface CidadeService {
	
	List<Cidade> findByEstado(Long idEstado);

}
