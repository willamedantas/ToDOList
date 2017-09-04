package br.ce.edu.todolist.service;

import br.ce.edu.todolist.bean.TarefaBean;

public class CadastrarTarefaService {
	
	public boolean verificaCampos(TarefaBean tarefa) {
			
			Boolean isValido = true;
			
			if(tarefa.getTitulo() == null && tarefa.getTitulo().equals("")){
				isValido = false;
			}
			
			if(tarefa.getDescricao() == null && tarefa.getDescricao().equals("")){
				isValido = false;
			}
			
			if(tarefa.getStatus() == null && tarefa.getStatus().equals("")){
				isValido = false;
			}
			
			if(tarefa.getUsuarioId() == null){
				isValido = false;
			}
			
			return isValido;
		}

}
