package com.curso.spring.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.models.dto.CidadeDTO;
import com.curso.spring.models.dto.EstadoDTO;
import com.curso.spring.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<EstadoDTO> estados = estadoService.findAll().stream().map(EstadoDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(estados);
	}
	
	@GetMapping(value = "/{id_estado}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidade(@PathVariable("id_estado") Long idEstado){
		List<CidadeDTO> cidades = estadoService.findCidadeByEstado(idEstado).stream().map(CidadeDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(cidades);
	}

}
