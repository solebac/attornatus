package com.attornatus.avaliacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attornatus.avaliacao.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
