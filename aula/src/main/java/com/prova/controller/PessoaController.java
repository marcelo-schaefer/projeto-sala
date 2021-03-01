package com.prova.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prova.pessoa.Pessoa;
import com.prova.pessoa.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@GetMapping
	public List<Pessoa> buscarTodos() {
		return this.service.buscarTodos();
	}

	@GetMapping("/{id}")
	public Pessoa buscarPorId(@PathVariable("id") String id) {
		return this.service.buscarPorId(id);
	}

	@PostMapping ("/criar")
	public Pessoa criar(@RequestBody Pessoa pessoa) {
		return this.service.criar(pessoa);
	}
}
