package com.curso.spring.services;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.curso.spring.models.entities.Categoria;

public interface CategoriaService {
	
	Categoria find(Long id);
	Categoria insert(Categoria obj);
	Categoria update(Long id, Categoria obj);
	Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
	List<Categoria> findAll();
	URI uploadPicture(MultipartFile multipartFile, Categoria obj);
	void delete(Long id);
}
