package br.ce.edu.todolist.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ce.edu.todolist.command.CadastrarTarefa;
import br.ce.edu.todolist.command.Command;
import br.ce.edu.todolist.command.RemoverTarefaCommand;
import br.ce.edu.todolist.command.IndexCommand;
import br.ce.edu.todolist.command.LoginCommand;
import br.ce.edu.todolist.command.PageCadastrarTarefa;
import br.ce.edu.todolist.command.PageEditarTarefa;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = -8703808823935681056L;
	
	private Map<String, Command> comandos = new HashMap<String, Command>(); 
	
	@Override
	public void init() throws ServletException {
		comandos.put("login", new LoginCommand());
		comandos.put("cadastrarTarefa", new CadastrarTarefa());
		comandos.put("page-cadastro", new PageCadastrarTarefa());
		comandos.put("home", new IndexCommand());
		comandos.put("editarTarefa", new PageEditarTarefa());
		comandos.put("removerTarefa", new RemoverTarefaCommand());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		String proximaPage = null;
		
		Command comando = verificarComando(acao);
		proximaPage = comando.execute(request);
		request.getRequestDispatcher(proximaPage).forward(request, response);
		
	}
	
	private Command verificarComando(String acao) {
		Command comando = null;
		for(String key : comandos.keySet()){
			if(key.equals(acao)){
				comando = comandos.get(key);
			}
		}
		return comando;
	}
	
	

}
