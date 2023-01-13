package com.attornatus.avaliacao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attornatus.avaliacao.entities.Endereco;
import com.attornatus.avaliacao.entities.Pessoa;
import com.attornatus.avaliacao.entities.enums.StatusEndereco;
import com.attornatus.avaliacao.repositories.EnderecoRepository;
import com.attornatus.avaliacao.repositories.PessoaRepository;
import com.attornatus.avaliacao.resources.dto.EnderecoDto;
import com.attornatus.avaliacao.resources.form.EnderecoForm;
import com.attornatus.avaliacao.services.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaRepository repositoryPessoa;

	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}

	public Endereco findById(Long id) {
		Optional<Endereco> obj = enderecoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	@Transactional
	public Endereco insert(EnderecoForm form) {
		Endereco obj = form.converter(repositoryPessoa);
		
		resetStatus(obj.getPessoa());
		obj.setStatus(StatusEndereco.PRINCIPAL); 
		
		return enderecoRepository.save(obj);
	}
	
	private void resetStatus(Pessoa pessoa) {
		List<Endereco> list = new ArrayList<>();
		List<EnderecoDto> enderecos= new ArrayList<>();
		enderecos.addAll(pessoa.getEnderecos().stream().map(EnderecoDto::new).collect(Collectors.toList()));
		enderecos.forEach(end -> {
			Pessoa person = repositoryPessoa.findById(pessoa.getId())
					.orElseThrow(() -> new ResourceNotFoundException(pessoa.getId()));
			Endereco recupera = new Endereco(end.getId(), end.getLogradouro(), end.getCep(), end.getNumero(), person);
			//recupera.setId(end.getId());
			recupera.setStatus(StatusEndereco.CONTATO);
			enderecoRepository.saveAndFlush(recupera);
		});
	}
}
