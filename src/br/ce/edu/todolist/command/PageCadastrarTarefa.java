package br.ce.edu.todolist.command;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ce.edu.todolist.bean.StatusTarefa;
import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.dao.UsuarioDao;
import br.ce.edu.todolist.util.PersistenceException;

public class PageCadastrarTarefa implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String proximaPage = "index.jsp";
		
		UsuarioDao usuarioDAO = new UsuarioDao();
		try {
			List<StatusTarefa> statusTarefas = Arrays.asList(StatusTarefa.values());
			List<UsuarioBean> usuarios = usuarioDAO.listaUsuarios();
			request.getSession().setAttribute("statusTarefas", statusTarefas);
			request.getSession().setAttribute("usuarios", usuarios);
			request.setAttribute("aba", "cadastro");
			proximaPage = "cadastrar-tarefa.jsp";
		} catch (PersistenceException e) {
			request.setAttribute("msgErro", "Erro ao consultar dados no banco de dados.");
		}
		return proximaPage;
	}
}
