package com.curso.spring.services;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.curso.spring.models.entities.Cliente;

public interface ClienteService {

	Cliente find(Long id);
	Cliente insert(Cliente obj);
	Cliente update(Long id, Cliente obj);
	Cliente findByEmail(String email);
	Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
	List<Cliente> findAll();
	void delete(Long id);
	URI uploadProfilePictur(MultipartFile multipartfile);
}
