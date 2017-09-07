package br.ce.edu.todolist.command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().invalidate();
		request.setAttribute("msgSucesso", "Feito logout com sucesso.");
		return "login.jsp";
	}

}
