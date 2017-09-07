package br.ce.edu.todolist.command;

import javax.servlet.http.HttpServletRequest;

import br.ce.edu.todolist.dao.TarefaDAO;
import br.ce.edu.todolist.util.PersistenceException;

public class RemoverTarefaCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String pagina = "main?acao=home";
		
		Integer id = Integer.parseInt(request.getParameter("id_tarefa"));
		TarefaDAO tarefaDAO = new TarefaDAO();
		
		try {
			tarefaDAO.removerTarefa(id);
			request.setAttribute("msgSucesso", "Tarefa removida com sucesso.");
		} catch (PersistenceException e) {
			request.setAttribute("msgErro", "Erro ao remover tarefa.");
		}
		
		return pagina;
	}

}
