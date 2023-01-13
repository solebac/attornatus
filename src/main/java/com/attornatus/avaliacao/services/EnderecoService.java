package com.attornatus.avaliacao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attornatus.avaliacao.entities.Endereco;
import com.attornatus.avaliacao.repositories.EnderecoRepository;
import com.attornatus.avaliacao.services.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}

	public Endereco findById(Long id) {
		Optional<Endereco> obj = enderecoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
}
