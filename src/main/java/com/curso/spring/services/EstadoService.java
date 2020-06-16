package com.curso.spring.services;

import java.util.List;

import com.curso.spring.models.entities.Cidade;
import com.curso.spring.models.entities.Estado;

public interface EstadoService {
	
	List<Estado> findAll();
	List<Cidade> findCidadeByEstado(Long idEstado);

}
