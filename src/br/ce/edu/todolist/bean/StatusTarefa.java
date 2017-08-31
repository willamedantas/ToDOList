package br.ce.edu.todolist.bean;

public enum StatusTarefa {
	
	A_FAZER("A fazer","A"),
	EM_ANDAMENTO("Em andamento","E"),
	CONCLUIDO("Concluido","C");
	
	private String descricao;
	private String sigla;
	
	private StatusTarefa(String descricao, String sigla) {
		this.descricao = descricao;
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
}
