package br.ce.edu.todolist.service;

import br.ce.edu.todolist.bean.TarefaBean;

public class CadastrarTarefaService {
	
	public boolean verificaCampos(TarefaBean tarefa) {
			
			Boolean isValido = true;
			
			if(tarefa.getTitulo().equals("")){
				isValido = false;
			}
			
			if(tarefa.getDescricao().equals("")){
				isValido = false;
			}
			
			if(tarefa.getStatus().equals("N")){
				isValido = false;
			}
			
			if(tarefa.getUsuarioId() == 0){
				isValido = false;
			}
			
			return isValido;
	}
}
