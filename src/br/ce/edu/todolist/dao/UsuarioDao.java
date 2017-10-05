package br.ce.edu.todolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ce.edu.todolist.bean.UsuarioBean;
import br.ce.edu.todolist.util.Conexao;
import br.ce.edu.todolist.util.PersistenceException;

public class UsuarioDao {
	
	public UsuarioBean buscarUsuario(String email) throws PersistenceException{
		
		Connection conn = Conexao.getInstance().getConnection();
		
		String sql = "SELECT * FROM USUARIO WHERE USR_EMAIL = ?";
		PreparedStatement statement = null;
		
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			
			UsuarioBean usuario = null;
			
			if(result.next()){	
				usuario = new UsuarioBean();
				usuario.setId(result.getInt("USR_ID"));
				usuario.setNome(result.getString("USR_NOME"));
				usuario.setEmail(result.getString("USR_EMAIL"));
				usuario.setSenha(result.getString("USR_SENHA"));
			}
			
			statement.close();
			conn.close();
			
			return usuario;
			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
	
	public List<UsuarioBean> listaUsuarios() throws PersistenceException{
		
		Connection conn = Conexao.getInstance().getConnection();
		
		String sql = "SELECT * FROM USUARIO";
		PreparedStatement statement = null;
		
		try {
			statement = conn.prepareStatement(sql);

			ResultSet result = statement.executeQuery();
			
			List<UsuarioBean> usuarios = new ArrayList<>();
			while(result.next()){
				UsuarioBean usuario = new UsuarioBean();
				usuario.setId(result.getInt("USR_ID"));
				usuario.setNome(result.getString("USR_NOME"));
				usuario.setEmail(result.getString("USR_EMAIL"));
				usuarios.add(usuario);
			}
			
			statement.close();
			conn.close();
			
			return usuarios;
			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} 
	}

}
