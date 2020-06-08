package com.curso.spring.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.curso.spring.models.entities.Cliente;

public interface ClienteService {

	Cliente find(Long id);
	Cliente insert(Cliente obj);
	Cliente update(Long id, Cliente obj);
	Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
	List<Cliente> findAll();
	void delete(Long id);
}
