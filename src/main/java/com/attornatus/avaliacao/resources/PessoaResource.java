package com.attornatus.avaliacao.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.avaliacao.entities.Pessoa;
import com.attornatus.avaliacao.resources.dto.PessoaDto;
import com.attornatus.avaliacao.services.PessoaService;

@RestController
@RequestMapping(value = "/api")
public class PessoaResource {
	
	@Autowired
	private PessoaService services;
	
	@GetMapping("/pessoas")
	public ResponseEntity<List<PessoaDto>> findAll(){
		List<Pessoa> p = services.findAll();
		return ResponseEntity.ok().body(PessoaDto.toListPessoa(p));
	}

}
