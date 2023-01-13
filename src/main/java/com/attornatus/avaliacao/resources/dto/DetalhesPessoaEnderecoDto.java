package com.attornatus.avaliacao.resources.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.attornatus.avaliacao.entities.Pessoa;
import com.attornatus.avaliacao.entities.enums.StatusEndereco;

public class DetalhesPessoaEnderecoDto {
	private Long id;
	private String nome;
	private List<EnderecoDto> enderecos;
	
	public DetalhesPessoaEnderecoDto(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.enderecos = new ArrayList<>();
		this.enderecos.addAll(pessoa.getEnderecos().stream().map(EnderecoDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public List<EnderecoDto> getEnderecos() {
		return enderecos;
	}

	public EnderecoDto getEnderecoMain() {

		/*List<EnderecoDto> list = listEnderecos.stream().filter(p -> p.getStatus()
				.equals("PRINCIPAL")).collect(Collectors.toList());*/
		System.out.println("----------------------------------------");
		getEnderecos().forEach(p -> System.out.println(p.getId() + "-" + p.getStatus()));
		System.out.println("----------------------------------------");

		EnderecoDto obj = getEnderecos().stream().
			    filter(p -> p.getStatus().equals(StatusEndereco.PRINCIPAL)).
			    findAny().orElse(null);
		System.out.println(obj.getStatus());
		return obj;

	}

	public PessoaEnderecoMainDto getConvertEnderecoMain() {
		EnderecoDto obj = getEnderecos().stream().
	    filter(p -> p.getStatus().equals(StatusEndereco.PRINCIPAL)).
	    findAny().orElse(null);
		return new PessoaEnderecoMainDto(obj);
	}
	
}

