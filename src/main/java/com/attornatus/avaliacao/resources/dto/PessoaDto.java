package com.attornatus.avaliacao.resources.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.attornatus.avaliacao.entities.Pessoa;

public class PessoaDto {
	
	private Long id;
	private String nome;
	private LocalDate dataDeNascimento;

	public PessoaDto(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.dataDeNascimento = pessoa.getDataDeNascimento();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public static List<PessoaDto> toListPessoa(List<Pessoa> p) {
		return p.stream().map(PessoaDto::new).collect(Collectors.toList());
	}

}
