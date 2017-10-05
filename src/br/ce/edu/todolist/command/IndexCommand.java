package br.ce.edu.todolist.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ce.edu.todolist.bean.TarefaBean;
import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.dao.TarefaDAO;
import br.ce.edu.todolist.util.PersistenceException;

public class IndexCommand implements Command {
	
	private TarefaDAO tarefaDAO;
	
	public IndexCommand() {
		tarefaDAO = new TarefaDAO();
	}
	
	public IndexCommand(TarefaDAO tarefaDAO){
		this.tarefaDAO = tarefaDAO;
	}

	@Override
	public String execute(HttpServletRequest request) {
		
		String page = "index.jsp";
		
		UsuarioBean usuario = (UsuarioBean) request.getSession().getAttribute("usuario");
		
		try {
			List<TarefaBean> tarefas = tarefaDAO.listarTarefaPorUsuario(usuario.getId());
			request.setAttribute("aba", "home");
			request.setAttribute("tarefas", tarefas);
			
		} catch (PersistenceException | NullPointerException e) {
			request.setAttribute("msgErro", "Erro ao consultar tarefas no banco de dados.");
		}
		
		return page;
	}

}
