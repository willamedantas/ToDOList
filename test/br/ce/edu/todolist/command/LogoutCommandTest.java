package br.ce.edu.todolist.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;

import br.ce.edu.todolist.util.PersistenceException;

public class LogoutCommandTest {
	
	@Test
	public void deveFazerLogout() throws PersistenceException{
		
		LogoutCommand logout = new LogoutCommand();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(httpSession);
		String pagina = logout.execute(request);
		
		verify(request).setAttribute("msgSucesso", "Feito logout com sucesso.");
		Assert.assertEquals("login.jsp", pagina);	
	}

}
