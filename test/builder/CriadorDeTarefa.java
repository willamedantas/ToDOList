package builder;

import br.ce.edu.todolist.bean.StatusTarefa;
import br.ce.edu.todolist.bean.TarefaBean;

public class CriadorDeTarefa {
	
	private TarefaBean tarefa;
	
	public CriadorDeTarefa init(String titulo){
		tarefa = new TarefaBean();
		tarefa.setTitulo(titulo);
		tarefa.setDescricao("Criador de tarefa builder.");
		tarefa.setStatus(StatusTarefa.A_FAZER);
		return this;
	}
	
	public CriadorDeTarefa descricao(String descricao){
		tarefa.setDescricao(descricao);
		return this;
	}
	
	public CriadorDeTarefa id(Integer id){
		tarefa.setId(id);
		return this;
	}
	
	public TarefaBean retornar(){
		return tarefa;
	}

}
