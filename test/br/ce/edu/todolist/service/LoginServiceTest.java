package br.ce.edu.todolist.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.util.PersistenceException;

public class LoginServiceTest {
	
	private UsuarioBean usuario;
	private LoginService service;
	
	@Before
	public void setup(){
		usuario = new UsuarioBean();
		service = new LoginService();
	}
	
	@Test
	public void deveVerificarCamposNulos(){
		boolean validado = service.verificaCampos(usuario);
		assertFalse(validado);
	}
	
	@Test
	public void deveVerificarCamposEmBranco(){
		usuario.setEmail("");
		usuario.setNome("");
		usuario.setSenha("");
		boolean validado = service.verificaCampos(usuario);
		assertFalse(validado);
	}
	
	@Test
	public void deveVerificarCamposValidos(){
		usuario.setEmail("admin@admin.com");
		usuario.setNome("Admin");
		usuario.setSenha("1234");
		boolean validado = service.verificaCampos(usuario);
		assertTrue(validado);
	}
	
	@Test
	public void deveVerificarUsuario() throws PersistenceException{
		usuario.setEmail("admin@admin.com");
		usuario.setNome("admin");
		usuario.setSenha("1234");
		boolean validado = service.verificaUsuario(usuario);
		assertTrue(validado);
	}
	
	@Test
	public void naoDeveVerificarUsuario() throws PersistenceException{
		usuario.setEmail("adm@admin.com");
		usuario.setNome("admin");
		usuario.setSenha("1234");
		boolean validado = service.verificaUsuario(usuario);
		assertFalse(validado);
	}
	
	

}








