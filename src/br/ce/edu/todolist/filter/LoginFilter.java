package br.ce.edu.todolist.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
	
	public static final String[] uriExclusao = {".css","login.jsp"};

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		String uri = httpRequest.getRequestURI();
		
		if(!isUriExclusao(uri,request)){
		    HttpSession session = httpRequest.getSession();
		    if(session.getAttribute("usuario") == null){
		    	request.setAttribute("msgErro", "Acesso negado, precisa fazer login no sistema.");
		    	request.getRequestDispatcher("login.jsp").forward(request, response);
		    }else{
		    	chain.doFilter(request, response);
		    }
		}else{
			chain.doFilter(request, response);			
		}	
		
	}



	private boolean isUriExclusao(String uri, ServletRequest request) {
		boolean retorno = false;
		String acao = request.getParameter("acao");
		for(String exclusao : uriExclusao){
			if(uri.endsWith(exclusao)){
				retorno = true;
			}
		}
		
		if(retorno != true && acao != null && acao.equals("login")){
			retorno = true;
		}
		
		return retorno;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
