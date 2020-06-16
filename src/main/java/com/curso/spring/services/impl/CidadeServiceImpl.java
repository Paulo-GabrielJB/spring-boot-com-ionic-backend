package com.curso.spring.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.spring.models.entities.Cidade;
import com.curso.spring.repositories.CidadeRepository;
import com.curso.spring.services.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public List<Cidade> findByEstado(Long idEstado) {
		return cidadeRepository.findCidades(idEstado);
	}
	
	

}
