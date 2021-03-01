package com.prova.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prova.sala.Sala;
import com.prova.sala.SalaService;
import com.prova.sala.SalaTipo;

@RestController
@RequestMapping("/salas")
public class SalaContoller {

	@Autowired
	private SalaService service;

	@GetMapping
	public List<Sala> buscarTodos() {
		return this.service.buscarTodos();
	}

	@GetMapping("/{id}")
	public Sala buscarPorId(@PathVariable("id") String id) {
		return this.service.buscarPorId(id);
	}

	@PostMapping("/criar")
	public Sala criar(@RequestBody Sala sala) {
		return this.service.criar(sala);
	}

	@GetMapping("/tipo/{tipo}")
	public List<Sala> buscarPorTipo(@PathVariable("tipo") SalaTipo tipo) {
		return this.service.buscarTodosPorTipo(tipo);
	}

}
