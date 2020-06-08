package com.curso.spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.spring.models.entities.Pedido;
import com.curso.spring.repositories.PedidoRepository;
import com.curso.spring.services.PedidoService;
import com.curso.spring.services.exceptions.ResourceNotFoundException;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public Pedido find(Long id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma categoria com o id " + id + " encontrada"));
	}
	
}
