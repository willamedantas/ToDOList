package br.ce.edu.todolist.command;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

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

public class IndexCommandTest {
	
	@Test
	public void deveRetornarPaginaIndex() throws PersistenceException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		TarefaDAO dao = mock(TarefaDAO.class);
		List<TarefaBean> tarefas = new ArrayList<>();
		CriadorDeTarefa criaTarefa = new CriadorDeTarefa();
		CriadorUsuario criadorUsuario = new CriadorUsuario();
		
		UsuarioBean usuario = criadorUsuario.init("Usuario 1").retornar();
		tarefas.add(criaTarefa.init("Teste 1").retornar());
		tarefas.add(criaTarefa.init("Teste 2").retornar());
		
		when(request.getSession()).thenReturn(httpSession);
		when( httpSession.getAttribute("usuario")).thenReturn(usuario);
		when(dao.listarTarefaPorUsuario(1)).thenReturn(tarefas);
		
		IndexCommand index = new IndexCommand(dao);
		String pagina = index.execute(request);
		
		Assert.assertEquals("index.jsp", pagina);	
	}
	
	@Test
	public void deveRetornarErroNoRequest() throws PersistenceException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		CriadorUsuario criadorUsuario = new CriadorUsuario();
		
		UsuarioBean usuario = criadorUsuario.init("Usuario 1").retornar();
		when(request.getSession()).thenReturn(httpSession);
		when( httpSession.getAttribute("usuario")).thenReturn(usuario);
		
		IndexCommand index = new IndexCommand(null);
		index.execute(request);
		 
		 verify(request).setAttribute("msgErro", "Erro ao consultar tarefas no banco de dados.");
		
	}

}
