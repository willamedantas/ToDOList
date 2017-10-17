package br.ce.edu.todolist.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;

import br.ce.edu.todolist.bean.TarefaBean;
import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.dao.TarefaDAO;
import br.ce.edu.todolist.dao.UsuarioDao;
import br.ce.edu.todolist.util.PersistenceException;
import builder.CriadorDeTarefa;
import builder.CriadorUsuario;

public class PageEditarTarefaTest {
	
	@Test
	public void deveEditarTarefa() throws PersistenceException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		TarefaDAO tarefaDAO = mock(TarefaDAO.class);
		UsuarioDao userDAO = mock(UsuarioDao.class); 
		
		CriadorUsuario criadorUsuario = new CriadorUsuario();
		CriadorDeTarefa criaTarefa = new CriadorDeTarefa();
		List<UsuarioBean> usuarios = new ArrayList<>();
		TarefaBean tarefa = new TarefaBean();

		usuarios.add(criadorUsuario.init("Willame Dantas").id(1).retornar());
		usuarios.add(criadorUsuario.init("Fernanda Dantas").id(2).retornar());
		
		when(request.getSession()).thenReturn(httpSession);
		when(userDAO.listaUsuarios()).thenReturn(usuarios);
		when(request.getParameter("id_tarefa")).thenReturn("1");
		when(tarefaDAO.getTarefa(1)).thenReturn(criaTarefa.init("Teste 1").id(1).retornar());
		
		PageEditarTarefa pageEditar = new PageEditarTarefa(userDAO, tarefaDAO);
		String pagina = pageEditar.execute(request);
		
		Assert.assertEquals("cadastrar-tarefa.jsp", pagina);	
	}
	
	@Test
	public void deveRetornaMensagemErroRequest() throws PersistenceException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		
		PageEditarTarefa pageEditar = new PageEditarTarefa(null,null);
		
		when(request.getSession()).thenReturn(httpSession);
		when(request.getParameter("id_tarefa")).thenReturn("1");
		pageEditar.execute(request);
		
		verify(request).setAttribute("msgErro", "Erro ao consultar dados no banco de dados.");
		
	}

}
