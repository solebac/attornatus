package com.attornatus.avaliacao.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.attornatus.avaliacao.entities.Pessoa;
import com.attornatus.avaliacao.services.PessoaService;

@DataJpaTest
class PessoaRepositoryTest {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaService service;
	
	@Test
	public void deveCompararFindPessoaPorId() {
		Pessoa obj = new Pessoa(null, "Fulano-01", LocalDate.of(1978, 12, 25));
		Pessoa obj1 = new Pessoa(null, "Fulano-02", LocalDate.of(1978, 12, 25));
		pessoaRepository.save(obj);
		pessoaRepository.save(obj1);
		Long id = 1L;
		Pessoa obj2 = pessoaRepository.findById(id).get();	
		assertThat(obj.getId()).isEqualTo(obj2.getId());
		assertThat(obj.getDataDeNascimento()).isEqualTo(obj2.getDataDeNascimento());
	}
	
	@Test
	public void deveDeletePessoaPorId() {
		Pessoa obj = new Pessoa(null, "Fulano-01", LocalDate.of(1978, 12, 25));
		Pessoa obj1 = new Pessoa(null, "Fulano-02", LocalDate.of(1979, 1, 5));
		Pessoa obj2 = new Pessoa(null, "Fulano-03", LocalDate.of(1980, 2, 10));
		pessoaRepository.saveAll(Arrays.asList(obj,obj1,obj2));
		
		pessoaRepository.deleteById(obj1.getId());
		
		List<Pessoa> pessoas = pessoaRepository.findAll();
		assertThat(pessoas).hasSize(2).contains(obj, obj2);
		
	}
	
	@Test
	public void deveDeleteTodasPessoas() {
		Pessoa obj = new Pessoa(null, "Fulano-01", LocalDate.of(1978, 12, 25));
		Pessoa obj1 = new Pessoa(null, "Fulano-02", LocalDate.of(1979, 1, 5));
		Pessoa obj2 = new Pessoa(null, "Fulano-03", LocalDate.of(1980, 2, 10));
		pessoaRepository.saveAll(Arrays.asList(obj,obj1,obj2));
		
		pessoaRepository.deleteAll();
		
		assertThat(pessoaRepository.findAll()).isEmpty();
		
	}
	
	/*@Test
	public void deveVerificarOEnderecoMaindaPessoa() {
		Pessoa p1 = new Pessoa(null, "Fulano-01", LocalDate.now());
		
		Endereco e1 = new Endereco(null, "Rua Test-01", "12345678", "100", p1);
		Endereco e3 = new Endereco(null, "Rua Alfa-03", "99545678", "102", p1);
		Endereco e4 = new Endereco(null, "Rua Test-04", "99540008", "103", p1);
		e3.setStatus(StatusEndereco.PRINCIPAL);
		
		pessoaRepository.saveAll(Arrays.asList(p1));
		enderecoRepository.saveAll(Arrays.asList(e1, e3, e4));
		
		//PessoaService service = new PessoaService();
		//assertNotNull(service);
		
		service.pessoaEnderecoPrincipal(2l);
		assertEquals("PRINCIPAL", p1.getEnderecos().get(0).getStatus());
		
		
	}*/

}
