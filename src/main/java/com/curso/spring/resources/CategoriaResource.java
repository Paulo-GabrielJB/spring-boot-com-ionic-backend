package com.curso.spring.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.models.entities.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar(){
		Categoria c1 = new Categoria(1L, "Eletronicos");
		Categoria c2 = new Categoria(2L, "Livros");
		return ResponseEntity.ok().body(new ArrayList<Categoria>(Arrays.asList(c1, c2)));
	}

}
