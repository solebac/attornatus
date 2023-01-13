package com.attornatus.avaliacao.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.attornatus.avaliacao.entities.Pessoa;
import com.attornatus.avaliacao.repositories.PessoaRepository;
import com.attornatus.avaliacao.services.exceptions.DatabaseExceptionOwn;
import com.attornatus.avaliacao.services.exceptions.ResourceNotFoundException;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository repository;

	public List<Pessoa> findAll() {
		return repository.findAll();
	}

	public Pessoa findById(Long id) {
		Optional<Pessoa> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Pessoa insert(Pessoa obj) {
		return repository.save(obj);
	}

	@Transactional
	public void delete(Long id) {
		if (repository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseExceptionOwn(e.getMessage());
		}
	}

	@Transactional
	public Pessoa update(Long id, Pessoa obj) {
		try {
			System.out.println("Key.: " + id);
			Pessoa entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Pessoa entity, Pessoa obj) {
		entity.setNome(obj.getNome());
		entity.setDataDeNascimento(obj.getDataDeNascimento());
	}

}
