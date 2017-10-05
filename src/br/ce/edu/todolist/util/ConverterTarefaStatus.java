package br.ce.edu.todolist.util;

import br.ce.edu.todolist.bean.StatusTarefa;

public class ConverterTarefaStatus {
	
	public static StatusTarefa converterEnum(String status) {

		if (status != null) {
			switch (status) {
			case "A":
				return StatusTarefa.A_FAZER;
			case "E":
				return StatusTarefa.EM_ANDAMENTO;
			case "C":
				return StatusTarefa.CONCLUIDO;
			default:
				return null;
			}
		} else {
			return null;
		}

	}
	
}
