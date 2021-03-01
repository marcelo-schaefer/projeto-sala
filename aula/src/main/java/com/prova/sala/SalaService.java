package com.prova.sala;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.pessoa.PessoaService;

@Service
public class SalaService {

	@Autowired
	private SalaRepository repository;
	@Autowired
	private PessoaService pessoaService;

	public List<Sala> buscarTodos() {
		return repository.findAll();
	}

	public Sala criar(Sala sala) {
		return repository.save(sala);
	}

	public Sala buscarPorId(String id) {
		Sala sala = repository.findById(id).orElseThrow(RuntimeException::new);
		sala.setPessoas(pessoaService.buscarTodosPorSalaId(id));
		return sala;
	}

	public List<Sala> buscarTodosPorTipo(SalaTipo tipo) {
		return repository.findAllByTipo(tipo);
	}

	public List<Sala> buscarTodasSalasDaTrilha(List<String> id) {
		return repository.findAllByIdIn(id);
	}
	
	public List<Sala> filtrarPorTipo(List<Sala> salas, SalaTipo tipo) {
		return salas.stream()
				.filter(sala -> sala.getTipo().equals(tipo))
				.collect(Collectors.toList());
	}

}
