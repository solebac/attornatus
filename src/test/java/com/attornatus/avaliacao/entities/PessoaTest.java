package com.attornatus.avaliacao.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class PessoaTest {

	@Test
	void deveriaTestarConstructorQuantoAsSuasInstanciar() {
		Pessoa p1 = new Pessoa(null, "Fulano-01", LocalDate.now());
		assertNotNull(p1);
		assertEquals("Fulano-01", p1.getNome());
	}
	
	@Test
	void deveriaTestarDataNascimentoQuantoAoLocale() {
		Pessoa p1 = new Pessoa(null, "Fulano-01", LocalDate.of(1978, 12, 24));
		DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate d = LocalDate.parse("24/12/1978",formatter);
		assertEquals(d, p1.getDataDeNascimento());
	}

}
