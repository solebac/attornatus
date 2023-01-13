package com.attornatus.avaliacao.resources;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.avaliacao.entities.Pessoa;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	@GetMapping
	public ResponseEntity<Pessoa> findAll() {
		return ResponseEntity.ok().body(new Pessoa(1l, "Flavio", LocalDate.now()));
	}
}
