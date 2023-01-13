package com.attornatus.avaliacao.entities;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.attornatus.avaliacao.entities.enums.StatusEndereco;

public class Endereco implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cep;
	private String numero;
	@Enumerated(EnumType.STRING)
	private StatusEndereco status = StatusEndereco.CONTATO;
	private Pessoa pessoas;
	
	public Endereco() {
	}

	public Endereco(Long id, String cep, String numero, Pessoa pessoas) {
		this.id = id;
		this.cep = cep;
		this.numero = numero;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Pessoa getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoa pessoas) {
		this.pessoas = pessoas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	
}
