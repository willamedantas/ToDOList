package br.ce.edu.todolist.util;

import br.ce.edu.todolist.bean.StatusTarefa;

public class ConverterTarefaStatus {
	
	public static StatusTarefa converterEnum(String status){
		
		if(status.equals("A")){
			return StatusTarefa.A_FAZER;
		}else
			if(status.equals("E")){
				return StatusTarefa.EM_ANDAMENTO;
		}else if(status.equals("C")){
			return StatusTarefa.CONCLUIDO;
		}
		
		return null;
	}

}
