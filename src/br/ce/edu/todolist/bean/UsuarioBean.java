package br.ce.edu.todolist.bean;

import java.io.Serializable;

public class UsuarioBean implements Serializable {
	
	private static final long serialVersionUID = -5520626846875557474L;

	private Integer id;
	
	private String nome;
	
	private String email;
	
	private String senha;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
