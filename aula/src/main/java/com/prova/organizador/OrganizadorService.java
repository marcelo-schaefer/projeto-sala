package com.prova.organizador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.pessoa.Pessoa;
import com.prova.pessoa.PessoaService;
import com.prova.sala.Sala;
import com.prova.sala.SalaService;
import com.prova.sala.SalaTipo;

@Service
public class OrganizadorService {

	@Autowired
	private SalaService salaService;

	@Autowired
	private PessoaService pessoaService;

	public List<Pessoa> processar() {
		List<Sala> salas = salaService.buscarTodos();
		List<Pessoa> pessoas = pessoaService.buscarTodos();
		pessoas = processar(salas, pessoas);
		pessoaService.salvar(pessoas);
		return pessoas;
	}
	
	public List<Pessoa> processar(List<Sala> salas, List<Pessoa> pessoas) {
		if (salas.isEmpty()) {
			throw new RuntimeException("nao ha salas disponiveis");
		}
		
		if (pessoas.isEmpty()) {
			throw new RuntimeException("nao ha inscritos");
		}

		if (pessoas.size() < salas.size()) {
			throw new RuntimeException("nao ha inscritos o suficiente");
		}

		List<Sala> cafes = salaService.filtrarPorTipo(salas, SalaTipo.cafe);
		List<Sala> auditorios = salaService.filtrarPorTipo(salas, SalaTipo.evento);
		
		if (cafes.size() != auditorios.size()) {
			throw new RuntimeException("o numero de salas e cafes sao diferentes");
		}
		
		Integer numeroGrupos = auditorios.size();
		Integer lotacaoMaximaPorSala = getLotacaoMaximaPorSala(salas);
		Integer lotacaoMaxima = numeroGrupos * lotacaoMaximaPorSala;
		
		if (lotacaoMaxima < pessoas.size()) {
			throw new RuntimeException("nao ha vagas suficientes");
		}
		
		reiniciarTrilhas(pessoas);
		List<List<Pessoa>> grupos = agruparPessoas(pessoas, numeroGrupos, lotacaoMaximaPorSala);
		
		colocarGruposNasSalas(grupos, auditorios);
		colocarGruposNasSalas(grupos, cafes);
		grupos = trocarParteDasPessoas(grupos, numeroGrupos, lotacaoMaximaPorSala);
		colocarGruposNasSalas(grupos, auditorios);
		colocarGruposNasSalas(grupos, cafes);
		
		return pessoas;
	}
	
	public int getLotacaoMaximaPorSala(List<Sala> salas) {
		return salas.stream().mapToInt(Sala::getLotacao).min().getAsInt();
	}
	
	public void reiniciarTrilhas(List<Pessoa> pessoas) {
		for (Pessoa pessoa : pessoas) {
			pessoa.setTrilha(new ArrayList<String>());
			pessoa.setSalas(new ArrayList<Sala>());
		}
	}
	
	public List<List<Pessoa>> agruparPessoas(List<Pessoa> pessoas, int numeroDeGrupos, int tamanhoDosGrupos) {
		List<List<Pessoa>> grupos = new ArrayList<List<Pessoa>>(numeroDeGrupos);
		
		for (int i = 0; i < numeroDeGrupos; i++) {
			grupos.add(new ArrayList<Pessoa>(tamanhoDosGrupos));
		}
		
		int grupo = 0;
		for (Pessoa pessoa : pessoas) {
			grupos.get(grupo).add(pessoa);			
			grupo = (grupo + 1) % numeroDeGrupos;
		}
		
		return grupos;
	}
	
	public void colocarGruposNasSalas(List<List<Pessoa>> grupos, List<Sala> salas) {
		for (int i = 0; i < grupos.size(); i++) {
			Sala sala = salas.get(i);
			List<Pessoa> grupo = grupos.get(i);
			for (Pessoa pessoa : grupo) {
				pessoa.getTrilha().add(sala.getId());
				pessoa.getSalas().add(sala);
			}
		}
	}

	public List<List<Pessoa>> trocarParteDasPessoas(List<List<Pessoa>> grupos, int numeroDeGrupos, int tamanhoDosGrupos) {
		tamanhoDosGrupos = tamanhoDosGrupos / 2;
		List<Pessoa> ultimoGrupo = grupos.get(grupos.size() - 1);
		List<Pessoa> metade = ultimoGrupo.subList(tamanhoDosGrupos, ultimoGrupo.size());
		List<List<Pessoa>> novos = new ArrayList<List<Pessoa>>(numeroDeGrupos);
		
		for (int i = 0; i < numeroDeGrupos; i++) {
			novos.add(new ArrayList<Pessoa>());
		}
		
		int grupoAtual = 1;
		for (List<Pessoa> grupo : grupos) {
			novos.get(grupoAtual).addAll(metade);
			novos.get(grupoAtual).addAll(grupo.subList(0, tamanhoDosGrupos));
			metade = grupo.subList(tamanhoDosGrupos, grupo.size());
			grupoAtual = (grupoAtual + 1) % numeroDeGrupos;
		}
		
		return novos;
	}
	
}
