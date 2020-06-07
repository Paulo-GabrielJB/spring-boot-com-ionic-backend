package com.curso.spring.configurations;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.curso.spring.models.entities.Categoria;
import com.curso.spring.models.entities.Produto;
import com.curso.spring.repositories.CategoriaRepository;
import com.curso.spring.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		
		Categoria c1 = new Categoria(null, "Informatica");
		Categoria c2 = new Categoria(null, "Escritorio");
		Categoria c3 = new Categoria(null, "Livros");
		
		categoriaRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Produto p1 = new Produto(null, "Computador", 2250.0);
		Produto p2 = new Produto(null, "Andre Matos - O Maestro do Heavy Metal", 79.9);
		Produto p3 = new Produto(null, "Impressora", 800.0);
		Produto p4 = new Produto(null, "Mouse", 80.0);
		Produto p5 = new Produto(null, "Cadeira", 250.0);
	
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c3));
		p3.getCategorias().addAll(Arrays.asList(c1, c2));
		p4.getCategorias().addAll(Arrays.asList(c1));
		p5.getCategorias().addAll(Arrays.asList(c2));
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		
		
	}

}
