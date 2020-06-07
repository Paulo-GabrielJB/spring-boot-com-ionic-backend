package com.curso.spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.spring.models.entities.Cliente;
import com.curso.spring.repositories.ClienteRepository;
import com.curso.spring.services.ClienteService;
import com.curso.spring.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente buscar(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente com o id " + id + "não encontrado"));
	}

}
