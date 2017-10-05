package br.ce.edu.todolist.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;

import br.ce.edu.todolist.bean.TarefaBean;
import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.dao.TarefaDAO;
import br.ce.edu.todolist.util.PersistenceException;
import builder.CriadorDeTarefa;
import builder.CriadorUsuario;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class CadastrarTarefaTest {
	
	@Test
	public void deveCadastrarTarefa() throws PersistenceException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		CriadorUsuario criadorUsuario = new CriadorUsuario();
		UsuarioBean usuario = criadorUsuario.init("Usuario 1").retornar();
		when(request.getParameter("id")).thenReturn("0");
		when(request.getParameter("titulo")).thenReturn("Teste mock");
		when(request.getParameter("descricao")).thenReturn("Fazendo testes.");
		when(request.getParameter("status")).thenReturn("A");
		when(request.getParameter("usuarioId")).thenReturn("1");
		when(request.getSession()).thenReturn(httpSession);
		when( httpSession.getAttribute("usuario")).thenReturn(usuario);
		
		
		TarefaDAO dao = mock(TarefaDAO.class);
		List<TarefaBean> tarefas = new ArrayList<>();
		CriadorDeTarefa criaTarefa = new CriadorDeTarefa();
		tarefas.add(criaTarefa.init("Teste 1").retornar());
		tarefas.add(criaTarefa.init("Teste 2").retornar());
		when(dao.listarTarefaPorUsuario(1)).thenReturn(tarefas);
		
		CadastrarTarefaCommand cadastrarTarefa = new CadastrarTarefaCommand(dao);
		String pagina = cadastrarTarefa.execute(request);
		
		Assert.assertEquals("index.jsp", pagina);	
	}
	
	@Test
	public void deveEditarTarefa() throws PersistenceException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		CriadorUsuario criadorUsuario = new CriadorUsuario();
		UsuarioBean usuario = criadorUsuario.init("Usuario 1").retornar();
		when(request.getParameter("id")).thenReturn("1");
		when(request.getParameter("titulo")).thenReturn("Teste mock");
		when(request.getParameter("descricao")).thenReturn("Fazendo testes.");
		when(request.getParameter("status")).thenReturn("A");
		when(request.getParameter("usuarioId")).thenReturn("1");
		when(request.getSession()).thenReturn(httpSession);
		when( httpSession.getAttribute("usuario")).thenReturn(usuario);
		
		
		TarefaDAO dao = mock(TarefaDAO.class);
		List<TarefaBean> tarefas = new ArrayList<>();
		CriadorDeTarefa criaTarefa = new CriadorDeTarefa();
		tarefas.add(criaTarefa.init("Teste 1").retornar());
		tarefas.add(criaTarefa.init("Teste 2").retornar());
		when(dao.listarTarefaPorUsuario(1)).thenReturn(tarefas);
		
		CadastrarTarefaCommand cadastrarTarefa = new CadastrarTarefaCommand(dao);
		String pagina = cadastrarTarefa.execute(request);
		
		Assert.assertEquals("index.jsp", pagina);	
	}
	
	@Test
	public void naoDeveCadastrarTarefaUsuarioNullo() throws PersistenceException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		CriadorUsuario criadorUsuario = new CriadorUsuario();
		UsuarioBean usuario = criadorUsuario.init("Usuario 1").retornar();
		when(request.getParameter("id")).thenReturn("0");
		when(request.getParameter("titulo")).thenReturn("");
		when(request.getParameter("descricao")).thenReturn("");
		when(request.getParameter("status")).thenReturn("");
		when(request.getParameter("usuarioId")).thenReturn("0");
		when(request.getSession()).thenReturn(httpSession);
		when( httpSession.getAttribute("usuario")).thenReturn(usuario);
		
		
		TarefaDAO dao = mock(TarefaDAO.class);
		when(dao.listarTarefaPorUsuario(1)).thenReturn(null);
		
		CadastrarTarefaCommand cadastrarTarefa = new CadastrarTarefaCommand(dao);
		String pagina = cadastrarTarefa.execute(request);
		
		Assert.assertEquals("cadastrar-tarefa.jsp", pagina);
	}


}
