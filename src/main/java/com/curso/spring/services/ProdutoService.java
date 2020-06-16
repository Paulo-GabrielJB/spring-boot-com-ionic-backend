package com.curso.spring.services;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.curso.spring.models.entities.Produto;

public interface ProdutoService {
	
	Produto find(Long id);
	Page<Produto> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction);
	URI uploadPicture(MultipartFile multipartFile, Produto obj);
	
}
