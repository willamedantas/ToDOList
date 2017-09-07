package br.ce.edu.todolist.command;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ce.edu.todolist.bean.StatusTarefa;
import br.ce.edu.todolist.bean.TarefaBean;
import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.dao.TarefaDAO;
import br.ce.edu.todolist.dao.UsuarioDao;
import br.ce.edu.todolist.util.PersistenceException;

public class PageEditarTarefa implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String proximaPage = "cadastrar-tarefa.jsp";
		
		Integer tarefaId = Integer.parseInt(request.getParameter("id_tarefa"));
		UsuarioDao usuarioDAO = new UsuarioDao();
		TarefaDAO tarefaDAO = new TarefaDAO();
		try {
			List<StatusTarefa> statusTarefas = Arrays.asList(StatusTarefa.values());
			List<UsuarioBean> usuarios = usuarioDAO.listaUsuarios();
			TarefaBean tarefa = tarefaDAO.getTarefa(tarefaId);
			
			request.setAttribute("tarefa", tarefa);
			request.getSession().setAttribute("statusTarefas", statusTarefas);
			request.getSession().setAttribute("usuarios", usuarios);
			
		} catch (PersistenceException e) {
			request.setAttribute("msgErro", "Erro ao consultar dados no banco de dados.");
		}
		return proximaPage;
	}
}
