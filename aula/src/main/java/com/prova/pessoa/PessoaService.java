package com.prova.pessoa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.sala.SalaService;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	@Autowired
	private SalaService salaService;

	public List<Pessoa> buscarTodos() {
		return repository.findAll();
	}

	public Pessoa criar(Pessoa pessoa) {
		return repository.save(pessoa);
	}

	public Pessoa buscarPorId(String id) {
		Pessoa pessoa = repository.findById(id).orElseThrow(RuntimeException::new);
		if (pessoa.getTrilha() != null && !pessoa.getTrilha().isEmpty()) {
			pessoa.setSalas(salaService.buscarTodasSalasDaTrilha(pessoa.getTrilha()));
		}
		return pessoa;
	}

	public void salvar(List<Pessoa> pessoas) {
		repository.saveAll(pessoas);
	}

	public List<Pessoa> buscarTodosPorSalaId(String id) {
		return repository.findAllByTrilha(id);
	}

}
