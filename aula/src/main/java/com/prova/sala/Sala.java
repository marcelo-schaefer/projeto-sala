package com.prova.sala;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.prova.pessoa.Pessoa;

@Document
public class Sala {

	@Id
	private String id;
	private String nome;
	private SalaTipo tipo;
	private Integer lotacao;
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private List<Pessoa> pessoas;

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

	public Integer getLotacao() {
		return lotacao;
	}

	public void setLotacao(Integer lotacao) {
		this.lotacao = lotacao;
	}

	public SalaTipo getTipo() {
		return tipo;
	}

	public void setTipo(SalaTipo tipo) {
		this.tipo = tipo;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
