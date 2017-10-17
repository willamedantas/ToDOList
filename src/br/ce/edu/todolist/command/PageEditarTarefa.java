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
	
	private UsuarioDao usuarioDAO;
	private TarefaDAO tarefaDAO;
	
	public PageEditarTarefa() {
		usuarioDAO = new UsuarioDao();
		tarefaDAO = new TarefaDAO();
	}
	
	public PageEditarTarefa(UsuarioDao usuarioDAO, TarefaDAO tarefaDAO){
		this.usuarioDAO = usuarioDAO;
		this.tarefaDAO = tarefaDAO;
	}

	@Override
	public String execute(HttpServletRequest request) {
		
		String proximaPage = "cadastrar-tarefa.jsp";
		Integer tarefaId = Integer.parseInt(request.getParameter("id_tarefa"));
		
		try {
			List<StatusTarefa> statusTarefas = Arrays.asList(StatusTarefa.values());
			List<UsuarioBean> usuarios = usuarioDAO.listaUsuarios();
			TarefaBean tarefa = tarefaDAO.getTarefa(tarefaId);
			
			request.setAttribute("tarefa", tarefa); 
			request.setAttribute("aba", "cadastro");
			request.getSession().setAttribute("statusTarefas", statusTarefas);
			request.getSession().setAttribute("usuarios", usuarios);
			
		} catch (PersistenceException | NullPointerException e) {
			request.setAttribute("msgErro", "Erro ao consultar dados no banco de dados.");
		}
		return proximaPage;
	}
}
