package br.ce.edu.todolist.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.service.LoginService;
import br.ce.edu.todolist.util.PersistenceException;

public class LoginCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		String proximaPage = "login.jsp";
				
		UsuarioBean usuario = new UsuarioBean();
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		
		LoginService service = new LoginService();
		
		try {
			if(service.verificaCampos(usuario) && service.verificaUsuario(usuario)){
				proximaPage = "index.jsp";
				Map<String, Object> retorno = service.retornoLogin(usuario.getEmail());
				request.getSession().setAttribute("usuario", retorno.get("usuario"));
				request.setAttribute("tarefas", retorno.get("tarefas"));
				request.setAttribute("aba", "home");
			}else{
				request.setAttribute("msgErro", "Usuário inválido.");
			}
		} catch (PersistenceException e) {
			request.setAttribute("msgErro", "Erro ao consultar banco de dados.");
			e.printStackTrace();
		}
		
		return proximaPage;
	}
}
