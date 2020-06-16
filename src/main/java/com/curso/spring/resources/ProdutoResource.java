package com.curso.spring.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.curso.spring.models.dto.ProdutoDTO;
import com.curso.spring.models.entities.Produto;
import com.curso.spring.resources.utils.URL;
import com.curso.spring.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> find(@PathVariable(value = "id") Long id){
		return ResponseEntity.ok().body(produtoService.find(id));
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "lines", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "order", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias){
		
		Page<Produto> pagina = produtoService.search(URL.decodeParam(nome), URL.decodeLongList(categorias), page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> paginaDTO = pagina.map(ProdutoDTO::new);
		return ResponseEntity.ok().body(paginaDTO);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/picture")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file, @RequestBody Produto obj){
		URI uri = produtoService.uploadPicture(file, obj);
		return ResponseEntity.created(uri).build();
	}

}
