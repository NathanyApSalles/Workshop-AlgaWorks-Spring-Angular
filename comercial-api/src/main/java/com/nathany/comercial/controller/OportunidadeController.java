package com.nathany.comercial.controller;

import java.awt.List;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nathany.comercial.model.Oportunidade;
import com.nathany.comercial.repository.OportunidadeRepository;
@CrossOrigin //origem cruzada 
@RestController // notação para indicar que a classe é um controlador REST
@RequestMapping("/oportunidades") // essa notação mapeia a URI que vai receber requisições para essa classe
public class OportunidadeController {
	
	@Autowired //injeção de dependências do spring
	private OportunidadeRepository oportunidades;
	
	
	@GetMapping //método para tratar as requisições GET
	public java.util.List<Oportunidade> listar() {
		return oportunidades.findAll(); //faz select na tabela e transforma em Oportunidade e devolve uma lista
	}
	
	@GetMapping("/{id}") // por exemplo: localhost/oportunidades/1
	public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {//indica que é uma variavel que vai vir no path
		//responseEntity permite mexer no código de resposta (200,400)
		
		Optional<Oportunidade> op = oportunidades.findById(id);
		
		if(!op.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(op.get());
		
	}
	
	@PostMapping //incluir 
	@ResponseStatus(HttpStatus.CREATED) //retorna o status http CREATE
	public Oportunidade adicionar(@Valid @RequestBody Oportunidade op) {
		// @Valid: valida antes de inserir
		// @RequestBody: pega o json do corpo da requisição e transforma em objeto
		
		Optional<Oportunidade> opExistente = oportunidades.findByDescricaoAndNomeProspecto(op.getDescricao(), op.getNomeProspecto());
		
		if(opExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma oportunidade para este prospecto com a mesma descrição"); 
			
		}
		return oportunidades.save(op);
	}
	
	//exclusão e atualização

}
