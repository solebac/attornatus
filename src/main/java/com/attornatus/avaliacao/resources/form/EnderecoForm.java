package com.attornatus.avaliacao.resources.form;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.attornatus.avaliacao.entities.Endereco;
import com.attornatus.avaliacao.entities.Pessoa;
import com.attornatus.avaliacao.entities.enums.StatusEndereco;
import com.attornatus.avaliacao.repositories.PessoaRepository;
import com.attornatus.avaliacao.services.exceptions.ResourceNotFoundException;

public class EnderecoForm {
	private Long id;
	@NotNull
	@NotBlank
	private String logradouro;
	@NotNull
	@NotBlank
	private String cep;
	@NotNull
	@NotBlank
	private String numero;
	@NotNull
	private Long pessoa_id;
	private StatusEndereco status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public StatusEndereco getStatus() {
		return status;
	}

	public void setStatus(StatusEndereco status) {
		this.status = status;
	}

	public Long getPessoa_id() {
		return pessoa_id;
	}

	public void setPessoa_id(Long pessoa_id) {
		this.pessoa_id = pessoa_id;
	}

	public Endereco converter(PessoaRepository repositoryPessoa) {
		Optional<Pessoa> obj = repositoryPessoa.findById(pessoa_id);
		if (!obj.isPresent()) {
			throw new ResourceNotFoundException(pessoa_id);
		}
		return new Endereco(logradouro, cep, numero, obj.get());
	}

}
