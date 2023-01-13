package com.attornatus.avaliacao.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.avaliacao.entities.Endereco;
import com.attornatus.avaliacao.resources.dto.EnderecoDto;
import com.attornatus.avaliacao.services.EnderecoService;

@RestController
@RequestMapping(value = "/api")
public class EnderecoResource {
	@Autowired
	private EnderecoService services;
	
	@GetMapping("/enderecos")
	public ResponseEntity<List<EnderecoDto>> findAll(){
		List<Endereco> enderecos = services.findAll();

		return ResponseEntity.ok().body(EnderecoDto.toListEndereco(enderecos));
	}
	
	@GetMapping("/enderecos/{id}")
	public ResponseEntity<EnderecoDto> findById(@PathVariable Long id){
		Endereco obj = services.findById(id);
		return ResponseEntity.ok().body(new EnderecoDto(obj));
	}
}
