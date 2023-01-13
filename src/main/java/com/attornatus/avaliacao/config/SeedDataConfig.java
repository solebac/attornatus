package com.attornatus.avaliacao.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.attornatus.avaliacao.entities.Endereco;
import com.attornatus.avaliacao.entities.Pessoa;
import com.attornatus.avaliacao.entities.enums.StatusEndereco;
import com.attornatus.avaliacao.repositories.EnderecoRepository;
import com.attornatus.avaliacao.repositories.PessoaRepository;

@Configuration
@Profile("test")
public class SeedDataConfig implements CommandLineRunner{

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Pessoa p1 = new Pessoa(null, "Fulano-01", LocalDate.now());
		Pessoa p2 = new Pessoa(null, "Fulano-02", LocalDate.now());
		Pessoa p3 = new Pessoa(null, "Flavio", LocalDate.now());
		
		Endereco e1 = new Endereco(null, "Rua Test-01", "12345678", "100", p1);
		Endereco e2 = new Endereco(null, "Rua Beira Mar-02", "99345678", "101", p2);
		Endereco e3 = new Endereco(null, "Rua Alfa-03", "99545678", "102", p1);
		Endereco e4 = new Endereco(null, "Rua Test-04", "99540008", "103", p1);
		Endereco e5 = new Endereco(null, "Rua Young-05", "99300000", "107", p2);
		e3.setStatus(StatusEndereco.PRINCIPAL);
		e2.setStatus(StatusEndereco.PRINCIPAL);
		
		pessoaRepository.saveAll(Arrays.asList(p1, p2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5));
	}

}
