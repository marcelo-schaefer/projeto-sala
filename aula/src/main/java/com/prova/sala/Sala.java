package com.prova.sala;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.prova.pessoa.Pessoa;

@Entity
@Table(name = "sala")
public class Sala {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "tipo")
	private SalaTipo tipo;
	
	@Column(name = "lotacao")
	private Integer lotacao;
	
	@ManyToMany(cascade = CascadeType.REFRESH)
	private List<Pessoa> pessoas = new ArrayList<>();
	
	
	Sala(){
		
	}

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
