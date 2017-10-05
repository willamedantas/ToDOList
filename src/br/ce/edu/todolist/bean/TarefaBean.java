package br.ce.edu.todolist.bean;

import java.io.Serializable;

public class TarefaBean implements Serializable{
	
	private static final long serialVersionUID = 850119869042110417L;
	
	private Integer id;
	private String titulo;
	private String descricao;
	private StatusTarefa status;
	private Integer usuarioId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public StatusTarefa getStatus() {
		return status;
	}
	public void setStatus(StatusTarefa status) {
		this.status = status;
	}
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
}
