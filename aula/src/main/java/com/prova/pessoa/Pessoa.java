package com.prova.pessoa;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.prova.sala.Sala;

@Document
public class Pessoa {

	@Id
	private String id;
	private String nome;
	private String sobrenome;
	@JsonProperty(access = Access.READ_ONLY)
	private List<String> trilha;
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private List<Sala> salas;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public List<String> getTrilha() {
		return trilha;
	}

	public void setTrilha(List<String> trilha) {
		this.trilha = trilha;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

}
