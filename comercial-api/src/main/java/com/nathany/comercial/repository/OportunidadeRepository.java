package com.nathany.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nathany.comercial.model.Oportunidade;
//spring data jpa
//responsável por fazer as consultas nas tabelas, operações de pesquisa, salvar nova oportunidade
public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long> {
	
	Optional<Oportunidade> findByDescricaoAndNomeProspecto(String descricao, String nomeProspecto);
	

}
