package br.ce.edu.todolist.command;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;

import br.ce.edu.todolist.bean.TarefaBean;
import br.ce.edu.todolist.util.PersistenceException;
import builder.CriadorDeTarefa;

public class LoginCommandTest {
	
	@Test
	public void deveRetornarPaginaIndex() throws PersistenceException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		List<TarefaBean> tarefas = new ArrayList<>();
		CriadorDeTarefa criaTarefa = new CriadorDeTarefa();

		tarefas.add(criaTarefa.init("Teste 1").retornar());
		tarefas.add(criaTarefa.init("Teste 2").retornar());
		
		when(request.getParameter("email")).thenReturn("admin@admin.com");
		when(request.getParameter("senha")).thenReturn("1234");
		when(request.getSession()).thenReturn(httpSession);
		
		LoginCommand login = new LoginCommand();
		String pagina = login.execute(request);
		
		Assert.assertEquals("index.jsp", pagina);	
	}
	
	@Test
	public void deveRetornarLoginIndex() throws PersistenceException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		List<TarefaBean> tarefas = new ArrayList<>();
		CriadorDeTarefa criaTarefa = new CriadorDeTarefa();

		tarefas.add(criaTarefa.init("Teste 1").retornar());
		tarefas.add(criaTarefa.init("Teste 2").retornar());
		
		when(request.getParameter("email")).thenReturn("willame@admin.com");
		when(request.getParameter("senha")).thenReturn("1234");
		when(request.getSession()).thenReturn(httpSession);
		
		LoginCommand login = new LoginCommand();
		String pagina = login.execute(request);
		
		Assert.assertEquals("login.jsp", pagina);	
	}
	
	@Test
	public void deveRetornarMensagemErroNoRequest() throws PersistenceException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		List<TarefaBean> tarefas = new ArrayList<>();
		CriadorDeTarefa criaTarefa = new CriadorDeTarefa();

		tarefas.add(criaTarefa.init("Teste 1").retornar());
		tarefas.add(criaTarefa.init("Teste 2").retornar());
		
		when(request.getParameter("email")).thenReturn("admin@admin.com");
		when(request.getParameter("senha")).thenReturn("1234");
		when(request.getSession()).thenReturn(null);
		
		LoginCommand login = new LoginCommand();
		login.execute(request);
		
		verify(request).setAttribute("msgErro", "Erro ao consultar banco de dados.");
	}
	
}
