package com.attornatus.avaliacao.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.attornatus.avaliacao.entities.Pessoa;

@DataJpaTest
class PessoaRepositoryTest {
	@Autowired
	private PessoaRepository repository;
	
	@Test
	public void deveCompararFindPessoaPorId() {
		Pessoa obj = new Pessoa(null, "Fulano-01", LocalDate.of(1978, 12, 25));
		Pessoa obj1 = new Pessoa(null, "Fulano-02", LocalDate.of(1978, 12, 25));
		repository.save(obj);
		repository.save(obj1);
		Long id = 2L;
		Pessoa obj2 = repository.findById(id).get();	
		assertThat(obj.getId()).isEqualTo(obj2.getId());
		assertThat(obj.getDataDeNascimento()).isEqualTo(obj2.getDataDeNascimento());
	}
	
	@Test
	public void deveDeletePessoaPorId() {
		Pessoa obj = new Pessoa(null, "Fulano-01", LocalDate.of(1978, 12, 25));
		Pessoa obj1 = new Pessoa(null, "Fulano-02", LocalDate.of(1979, 1, 5));
		Pessoa obj2 = new Pessoa(null, "Fulano-03", LocalDate.of(1980, 2, 10));
		repository.saveAll(Arrays.asList(obj,obj1,obj2));
		
		List<Pessoa> pessoas = repository.findAll();
		assertThat(pessoas).hasSize(3).contains(obj, obj1, obj2);
		
		repository.deleteById(obj1.getId());
		
		assertThat(pessoas).hasSize(2).contains(obj, obj2);
		
	}
	
	@Test
	public void deveDeleteTodasPessoas() {
		Pessoa obj = new Pessoa(null, "Fulano-01", LocalDate.of(1978, 12, 25));
		Pessoa obj1 = new Pessoa(null, "Fulano-02", LocalDate.of(1979, 1, 5));
		Pessoa obj2 = new Pessoa(null, "Fulano-03", LocalDate.of(1980, 2, 10));
		repository.saveAll(Arrays.asList(obj,obj1,obj2));
		
		repository.deleteAll();
		
		assertThat(repository.findAll()).isEmpty();
		
	}

}
