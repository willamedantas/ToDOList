package br.ce.edu.todolist.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ce.edu.todolist.bean.TarefaBean;
import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.dao.TarefaDAO;
import br.ce.edu.todolist.dao.UsuarioDao;
import br.ce.edu.todolist.util.PersistenceException;

public class LoginService{

	public boolean verificaCampos(UsuarioBean usuario) {
		
		Boolean isValido = false;
		
		if(usuario.getEmail() != null && !usuario.getEmail().equals("") & !usuario.getSenha().equals("")){
			isValido = true;
		}
		
		return isValido;
	}
	
	public boolean verificaUsuario(UsuarioBean usuario) throws PersistenceException{
	
		Boolean isValido = false;
		
		UsuarioDao usuarioDao = new UsuarioDao();
		UsuarioBean usuarioBanco = usuarioDao.buscarUsuario(usuario.getEmail());
		
		if(usuarioBanco != null && usuario.getSenha().equals(usuarioBanco.getSenha())){
			isValido = true;
		}
		
		return isValido;
		
	}
	
	public Map<String, Object> retornoLogin(String email) throws PersistenceException{
		
		UsuarioDao usuarioDao = new UsuarioDao();
		TarefaDAO tarefaDAO = new TarefaDAO();
		Map<String, Object> dados = new HashMap<>();
		
		UsuarioBean usuarioBanco = usuarioDao.buscarUsuario(email);
		List<TarefaBean> tarefas = tarefaDAO.listarTarefaPorUsuario(usuarioBanco.getId());
		
		dados.put("usuario", usuarioBanco);
		dados.put("tarefas", tarefas);

		return dados;
		
	}
	
}
















