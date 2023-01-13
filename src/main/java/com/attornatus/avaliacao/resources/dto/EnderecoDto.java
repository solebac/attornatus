package com.attornatus.avaliacao.resources.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.attornatus.avaliacao.entities.Endereco;
import com.attornatus.avaliacao.entities.enums.StatusEndereco;

public class EnderecoDto {
	private Long id;
	private String logradouro;
	private String cep;
	private String numero;
	private StatusEndereco status;
	
	public EnderecoDto() {
	}
	
	public EnderecoDto(Endereco obj) {
		this.id = obj.getId();
		this.logradouro = obj.getLogradouro();
		this.cep = obj.getCep();
		this.numero = obj.getNumero();
		this.status = obj.getStatus();
	}

	public Long getId() {
		return id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getCep() {
		return cep;
	}

	public String getNumero() {
		return numero;
	}

	public StatusEndereco getStatus() {
		return status;
	}
	

	public void setStatus(StatusEndereco status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		EnderecoDto other = (EnderecoDto) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public static List<EnderecoDto> toListEndereco(List<Endereco> enderecos) {
		// TODO Auto-generated method stub
		return enderecos.stream().map(EnderecoDto::new).collect(Collectors.toList());
	}
	
}
