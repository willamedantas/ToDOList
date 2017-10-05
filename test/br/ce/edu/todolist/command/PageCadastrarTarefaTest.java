package br.ce.edu.todolist.command;

import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.dao.UsuarioDao;
import br.ce.edu.todolist.util.PersistenceException;
import builder.CriadorUsuario;

public class PageCadastrarTarefaTest {
	
	@Test
	public void deveRetornaPaginaCadastro() throws PersistenceException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		UsuarioDao dao = mock(UsuarioDao.class);
		CriadorUsuario criadorUsuario = new CriadorUsuario();
		List<UsuarioBean> usuarios = new ArrayList<>();
		PageCadastrarTarefa pageCadastrarTarefa = new PageCadastrarTarefa(dao);
		
		usuarios.add(criadorUsuario.init("Teste 1").retornar());
		usuarios.add(criadorUsuario.init("Teste 2").retornar());
		when(dao.listaUsuarios()).thenReturn(usuarios);
		when(request.getSession()).thenReturn(httpSession);
		String pagina = pageCadastrarTarefa.execute(request);
		
		Assert.assertEquals("cadastrar-tarefa.jsp", pagina);
		
	}
	
	@Test
	public void deveRetornaMensagemErroRequest() throws PersistenceException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession httpSession = mock(HttpSession.class);
		CriadorUsuario criadorUsuario = new CriadorUsuario();
		List<UsuarioBean> usuarios = new ArrayList<>();
		PageCadastrarTarefa pageCadastrarTarefa = new PageCadastrarTarefa(null);
		
		usuarios.add(criadorUsuario.init("Teste 1").retornar());
		usuarios.add(criadorUsuario.init("Teste 2").retornar());
		when(request.getSession()).thenReturn(httpSession);
		pageCadastrarTarefa.execute(request);
		
		verify(request).setAttribute("msgErro", "Erro ao consultar dados no banco de dados.");
		
	}

}
