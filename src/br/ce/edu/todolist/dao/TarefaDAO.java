package br.ce.edu.todolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ce.edu.todolist.bean.StatusTarefa;
import br.ce.edu.todolist.bean.TarefaBean;
import br.ce.edu.todolist.util.ConverterTarefaStatus;
import br.ce.edu.todolist.util.PersistenceException;
import br.ce.edu.todolist.util.Conexao;

public class TarefaDAO {
	
	public List<TarefaBean> listarTarefas() throws PersistenceException{
		
		Connection conn = Conexao.getInstance().getConnection();
		
		String sql = "SELECT * FROM TAREFA";
		PreparedStatement statement = null;
		
		try {
			statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			List<TarefaBean> listaTarefas = new ArrayList<>();
			while(result.next()){
				TarefaBean tarefa = new TarefaBean();
				tarefa.setId(result.getInt("TR_ID"));
				tarefa.setTitulo(result.getString("TR_TITULO"));
				tarefa.setDescricao(result.getString("TR_DESCRICAO"));
				StatusTarefa status = ConverterTarefaStatus.converterEnum(result.getString("TR_STATUS"));
				tarefa.setStatus(status);
				tarefa.setUsuarioId(result.getInt("USR_ID"));
				listaTarefas.add(tarefa);
			}
			
			statement.close();
			conn.close();
			
			return listaTarefas;
			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} 
	}
	
	public List<TarefaBean> listarTarefaPorUsuario(Integer id) throws PersistenceException{
		
		Connection conn = Conexao.getInstance().getConnection();
		
		String sql = "SELECT * FROM TAREFA WHERE USR_ID = ?";
		PreparedStatement statement = null;
		
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			List<TarefaBean> listaTarefas = new ArrayList<>();
			while(result.next()){
				TarefaBean tarefa = new TarefaBean();
				tarefa.setId(result.getInt("TR_ID"));
				tarefa.setTitulo(result.getString("TR_TITULO"));
				tarefa.setDescricao(result.getString("TR_DESCRICAO"));
				StatusTarefa status = ConverterTarefaStatus.converterEnum(result.getString("TR_STATUS"));
				tarefa.setStatus(status);
				tarefa.setUsuarioId(result.getInt("USR_ID"));
				listaTarefas.add(tarefa);
			}
			
			statement.close();
			conn.close();
			
			return listaTarefas;
			
		} catch (SQLException | NullPointerException e) {
			throw new PersistenceException(e.getMessage());
		} 	
	}
	
	public TarefaBean getTarefa(Integer id) throws PersistenceException{
		
		Connection conn = Conexao.getInstance().getConnection();
		
		String sql = "SELECT * FROM TAREFA WHERE TR_ID = ?";
		PreparedStatement statement = null;
		
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			TarefaBean tarefa = null;
			if(result.next()){
				tarefa = new TarefaBean();
				tarefa.setId(result.getInt("TR_ID"));
				tarefa.setTitulo(result.getString("TR_TITULO"));
				tarefa.setDescricao(result.getString("TR_DESCRICAO"));
				StatusTarefa status = ConverterTarefaStatus.converterEnum(result.getString("TR_STATUS"));
				tarefa.setStatus(status);
				tarefa.setUsuarioId(result.getInt("USR_ID"));
			}
			
			statement.close();
			conn.close();
			
			return tarefa;
			
		} catch (SQLException | NullPointerException e) {
			throw new PersistenceException(e.getMessage());
		}	
	}
	
	public int addTarefa(TarefaBean tarefa) throws PersistenceException{
				
		Connection conn = Conexao.getInstance().getConnection();
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TAREFA ");
		sql.append("(TR_TITULO, TR_DESCRICAO, TR_STATUS, USR_ID) ");
		sql.append("VALUES (?,?,?,?)");
		
		PreparedStatement statement = null;
		
		
		try {
			statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, tarefa.getTitulo());
			statement.setString(2, tarefa.getDescricao());
			statement.setString(3, tarefa.getStatus().getSigla());
			statement.setInt(4, tarefa.getUsuarioId());
			
			statement.executeUpdate();
			ResultSet result = statement.getGeneratedKeys();
			
			int id = 0;
			if(result.next()){
				id = result.getInt(1);
			}
		
			statement.close();
			conn.close();
			
			return id;
		} catch (SQLException | NullPointerException e) {
			throw new PersistenceException(e.getMessage());
		}	
	}
	
	public int editarTarefa(TarefaBean tarefa) throws PersistenceException{
		
		Connection conn = Conexao.getInstance().getConnection();
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE TAREFA SET ");
		sql.append("TR_TITULO = ?, TR_DESCRICAO = ?, TR_STATUS = ?, USR_ID = ? ");
		sql.append("WHERE TR_ID = ?");
		
		PreparedStatement statement = null;
		
		
		try {
			statement = conn.prepareStatement(sql.toString());
			statement.setString(1, tarefa.getTitulo());
			statement.setString(2, tarefa.getDescricao());
			statement.setString(3, tarefa.getStatus().getSigla());
			statement.setInt(4, tarefa.getUsuarioId());
			statement.setInt(5, tarefa.getId());
			
			int result = statement.executeUpdate();
		
			statement.close();
			conn.close();
			
			return result;
		} catch (SQLException | NullPointerException e ) {
			throw new PersistenceException(e.getMessage());
		}
	}
	
	public int removerTarefa(Integer tarefaId) throws PersistenceException{
		
		Connection conn = Conexao.getInstance().getConnection();
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TAREFA WHERE TR_ID = ?");
		
		PreparedStatement statement = null;
		
		
		try {
			statement = conn.prepareStatement(sql.toString());
			statement.setInt(1, tarefaId);
			
			int result = statement.executeUpdate();
		
			statement.close();
			conn.close();
			
			return result;
		} catch (SQLException | NullPointerException e) {
			throw new PersistenceException(e.getMessage());
		}			
	}
}
