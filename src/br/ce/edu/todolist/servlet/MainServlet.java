package br.ce.edu.todolist.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ce.edu.todolist.command.Command;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = -8703808823935681056L;
	
	private Map<String, String> comandos = new HashMap<String, String>(); 
	
	@Override
	public void init() throws ServletException {
		comandos.put("login", "br.ce.edu.todolist.command.LoginCommand");
		comandos.put("cadastrarTarefa", "br.ce.edu.todolist.command.CadastrarTarefaCommand");
		comandos.put("page-cadastro", "br.ce.edu.todolist.command.PageCadastrarTarefa");
		comandos.put("home", "br.ce.edu.todolist.command.IndexCommand");
		comandos.put("editarTarefa", "br.ce.edu.todolist.command.PageEditarTarefa");
		comandos.put("removerTarefa", "br.ce.edu.todolist.command.RemoverTarefaCommand");
		comandos.put("sair", "br.ce.edu.todolist.command.LogoutCommand");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		String proximaPage = null;
		Command comando = null;
		
		try {
			comando = verificarComando(acao);
			proximaPage = comando.execute(request);
			request.getRequestDispatcher(proximaPage).forward(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	private Command verificarComando(String acao) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Command comando = null;
		for(String key : comandos.keySet()){
			if(key.equals(acao)){
				Class classe = Class.forName(comandos.get(key));
				comando = (Command) classe.newInstance();
			}
		}
		return comando;
	}
	
	

}
