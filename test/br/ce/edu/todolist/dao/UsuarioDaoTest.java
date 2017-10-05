package br.ce.edu.todolist.dao;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.util.PersistenceException;

public class UsuarioDaoTest {
	
	private UsuarioDao usuarioDAO;
	
	@Before
	public void setUp(){
		usuarioDAO = new UsuarioDao();
	}
	
	@Test
	public void deveRetornarListaUsuarios() throws PersistenceException{
		//Cenario
		UsuarioDao usuarioDAO = new UsuarioDao();
		
		//Ação
		List<UsuarioBean> usuarios = usuarioDAO.listaUsuarios();
		
		//Validação
		assertTrue(usuarios.size() > 0);
		
	}
	
	@Test
	public void deveRetornarUsuarioPorEmail() throws PersistenceException{
		
		String email = "admin@admin.com";
		
		UsuarioBean usuario = usuarioDAO.buscarUsuario(email);
		
		assertNotNull(usuario);

	}
	
	

}






