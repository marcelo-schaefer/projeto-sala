package com.prova.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prova.organizador.OrganizadorService;
import com.prova.pessoa.Pessoa;

@RestController
@RequestMapping("/organizador")
public class OrganizadorController {

	@Autowired
	private OrganizadorService service;
	
	@PostMapping ("/processar")
	public List<Pessoa> processar(){
		return service.processar();
	}
}
