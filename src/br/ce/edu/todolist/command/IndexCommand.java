package br.ce.edu.todolist.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ce.edu.todolist.bean.TarefaBean;
import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.dao.TarefaDAO;
import br.ce.edu.todolist.util.PersistenceException;

public class IndexCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		String page = "index.jsp";
		
		UsuarioBean usuario = (UsuarioBean) request.getSession().getAttribute("usuario");
		TarefaDAO tarefaDAO = new TarefaDAO();
		
		try {
			List<TarefaBean> tarefas = tarefaDAO.listarTarefaPorUsuario(usuario.getId());
			request.setAttribute("tarefas", tarefas);
			
		} catch (PersistenceException e) {
			request.setAttribute("msgErro", "Erro ao consultar tarefas no banco de dados.");
		}
		
		return page;
	}

}
