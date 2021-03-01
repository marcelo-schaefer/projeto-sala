package com.prova;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.prova.organizador.OrganizadorService;
import com.prova.pessoa.Pessoa;
import com.prova.sala.Sala;
import com.prova.sala.SalaTipo;

@SpringBootTest
class OrganizadorTests {

	@Autowired
	private OrganizadorService organizadorService;
	
	private static AtomicLong autoincrement = new AtomicLong();
	
	@Test
	public void quandoNaoHaSalas() {
		ArrayList<Sala> salas = new ArrayList<Sala>();
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		
	    Exception exception = assertThrows(RuntimeException.class, () -> {
	    	organizadorService.processar(salas, pessoas);
	    });

	    String expectedMessage = "nao ha salas disponiveis";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void quandoNaoHaInscritos() {
		ArrayList<Sala> salas = new ArrayList<Sala>();
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	
		salas.add(criarUmaSala(SalaTipo.evento, 10));
		
	    Exception exception = assertThrows(RuntimeException.class, () -> {
	    	organizadorService.processar(salas, pessoas);
	    });

	    String expectedMessage = "nao ha inscritos";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void quandoNaoHaInscritosSuficiente() {
		ArrayList<Sala> salas = new ArrayList<Sala>();
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		salas.add(criarUmaSala(SalaTipo.evento, 10));
		salas.add(criarUmaSala(SalaTipo.evento, 10));
		pessoas.add(criarUmaPessoa());
		
	    Exception exception = assertThrows(RuntimeException.class, () -> {
	    	organizadorService.processar(salas, pessoas);
	    });

	    String expectedMessage = "nao ha inscritos o suficiente";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void quandoNumeroDeSalasECafesSaoDiferentes() {
		ArrayList<Sala> salas = new ArrayList<Sala>();
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		salas.add(criarUmaSala(SalaTipo.evento, 1));
		salas.add(criarUmaSala(SalaTipo.evento, 1));
		pessoas.add(criarUmaPessoa());
		pessoas.add(criarUmaPessoa());
		
	    Exception exception = assertThrows(RuntimeException.class, () -> {
	    	organizadorService.processar(salas, pessoas);
	    });

	    String expectedMessage = "o numero de salas e cafes sao diferentes";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void quandoNaoHaVagasSuficiente() {
		ArrayList<Sala> salas = new ArrayList<Sala>();
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		salas.add(criarUmaSala(SalaTipo.evento, 1));
		salas.add(criarUmaSala(SalaTipo.cafe, 1));
		pessoas.add(criarUmaPessoa());
		pessoas.add(criarUmaPessoa());
		
	    Exception exception = assertThrows(RuntimeException.class, () -> {
	    	organizadorService.processar(salas, pessoas);
	    });

	    String expectedMessage = "nao ha vagas suficientes";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}

	
	@Test
	public void quandoFunciona() {
		ArrayList<Sala> salas = new ArrayList<Sala>();
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		salas.add(criarUmaSala(SalaTipo.evento, 2));
		salas.add(criarUmaSala(SalaTipo.cafe, 2));
		salas.add(criarUmaSala(SalaTipo.evento, 2));
		salas.add(criarUmaSala(SalaTipo.cafe, 2));
		pessoas.add(criarUmaPessoa());
		pessoas.add(criarUmaPessoa());
		pessoas.add(criarUmaPessoa());
		pessoas.add(criarUmaPessoa());
		
    	organizadorService.processar(salas, pessoas);
    	
    	assertTrue(pessoas.get(0).getSalas().size() == salas.size());
    	assertTrue(pessoas.get(1).getSalas().size() == salas.size());
    	assertTrue(pessoas.get(2).getSalas().size() == salas.size());
    	assertTrue(pessoas.get(3).getSalas().size() == salas.size());
    	
    	assertTrue(pessoas.get(0).getSalas().containsAll(salas));
    	assertTrue(pessoas.get(1).getSalas().containsAll(salas));
    	assertTrue(pessoas.get(2).getSalas().containsAll(salas.subList(0, 2)));
    	assertTrue(pessoas.get(3).getSalas().containsAll(salas.subList(2, 4)));
	}

	private Sala criarUmaSala(SalaTipo tipo, int lotacao) {
		Long id = autoincrement.getAndIncrement();
		Sala sala = new Sala();
		sala.setId(id.toString());
		sala.setNome(String.format("%s - %d", tipo.name(), id));
		sala.setTipo(tipo);
		sala.setLotacao(lotacao);
		return sala;
	}

	private Pessoa criarUmaPessoa() {
		Long id = autoincrement.getAndIncrement();
		Pessoa pessoa = new Pessoa();
		pessoa.setId(id.toString());
		return pessoa;
	}
}
