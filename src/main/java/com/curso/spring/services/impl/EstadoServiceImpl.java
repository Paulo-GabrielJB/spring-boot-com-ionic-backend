package com.curso.spring.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.spring.models.entities.Cidade;
import com.curso.spring.models.entities.Estado;
import com.curso.spring.repositories.EstadoRepository;
import com.curso.spring.services.CidadeService;
import com.curso.spring.services.EstadoService;
import com.curso.spring.services.exceptions.ResourceNotFoundException;

@Service
public class EstadoServiceImpl implements EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeService cidadeService;

	@Override
	public List<Estado> findAll() {
		return estadoRepository.findAllByOrderByNome();
	}

	@Override
	public List<Cidade> findCidadeByEstado(Long idEstado) {
		
		Estado obj = estadoRepository.findById(idEstado).orElseThrow(() -> new ResourceNotFoundException("Estado não localizado"));
		
		return cidadeService.findByEstado(obj.getId());
	}

}
