package com.attornatus.avaliacao.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.attornatus.avaliacao.entities.Pessoa;
import com.attornatus.avaliacao.resources.dto.DetalhesPessoaEnderecoDto;
import com.attornatus.avaliacao.resources.dto.PessoaDto;
import com.attornatus.avaliacao.resources.dto.PessoaEnderecoMainDto;
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
	
	@GetMapping("/pessoas/{id}")
	public ResponseEntity<PessoaDto> findById(@PathVariable Long id){
		Pessoa obj = services.findById(id);
		return ResponseEntity.ok().body(new PessoaDto(obj));
	}
	
	@GetMapping("/pessoas/enderecoprincipal/{id}")
	public ResponseEntity<PessoaEnderecoMainDto> consultaEnderecosPrincipal(@PathVariable Long id){
		PessoaEnderecoMainDto dto = services.pessoaEnderecoPrincipal(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping("/pessoas/enderecodetalhado/{id}")
	public ResponseEntity<DetalhesPessoaEnderecoDto> detalhesEnderecosPessoa(@PathVariable Long id){
		DetalhesPessoaEnderecoDto dto = services.detalhes(id);

		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping("/pessoas")
	public ResponseEntity<Pessoa> insert(@RequestBody @Valid Pessoa obj){
		obj = services.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping("/pessoas/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		services.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/pessoas/{id}")
	public ResponseEntity<PessoaDto> update(@PathVariable Long id, @RequestBody @Valid Pessoa obj){
		obj = services.update(id, obj);
		return ResponseEntity.ok().body(new PessoaDto(obj));
	}

}
