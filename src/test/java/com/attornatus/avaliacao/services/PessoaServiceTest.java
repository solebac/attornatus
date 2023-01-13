package com.attornatus.avaliacao.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.attornatus.avaliacao.entities.Endereco;
import com.attornatus.avaliacao.entities.Pessoa;
import com.attornatus.avaliacao.entities.enums.StatusEndereco;
import com.attornatus.avaliacao.repositories.EnderecoRepository;
import com.attornatus.avaliacao.repositories.PessoaRepository;

@DataJpaTest
class PessoaServiceTest {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Test
	void test() {
		Pessoa p1 = new Pessoa(null, "Fulano-01", LocalDate.now());
		
		Endereco e1 = new Endereco(null, "Rua Test-01", "12345678", "100", p1);
		Endereco e3 = new Endereco(null, "Rua Alfa-03", "99545678", "102", p1);
		Endereco e4 = new Endereco(null, "Rua Test-04", "99540008", "103", p1);
		e3.setStatus(StatusEndereco.PRINCIPAL);
		
		pessoaRepository.saveAll(Arrays.asList(p1));
		enderecoRepository.saveAll(Arrays.asList(e1, e3, e4));
		
		PessoaService service = new PessoaService();
		assertNotNull(service);
		
		service.pessoaEnderecoPrincipal(2l);
		assertEquals("PRINCIPAL", p1.getEnderecos().get(0).getStatus());

	}

}
