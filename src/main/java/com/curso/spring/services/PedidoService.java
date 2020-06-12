package com.curso.spring.services;

import org.springframework.data.domain.Page;

import com.curso.spring.models.entities.Pedido;

public interface PedidoService {
	
	Pedido find(Long id);
	Pedido insert(Pedido obj);
	Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);

}
