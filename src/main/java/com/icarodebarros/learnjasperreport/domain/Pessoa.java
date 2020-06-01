package com.icarodebarros.learnjasperreport.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String sexo;
	
	@Column(nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private String email;
	
	// @JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<Desempenho> desempenhos = new ArrayList<Desempenho>();

	public Pessoa() { }

	public Pessoa(Integer id, String nome, String sexo, String cpf, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.cpf = cpf;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Desempenho> getDesempenhos() {
		return desempenhos;
	}

	public void setDesempenhos(List<Desempenho> desempenhos) {
		this.desempenhos = desempenhos;
	}

}
