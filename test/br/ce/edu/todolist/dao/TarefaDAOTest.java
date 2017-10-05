package br.ce.edu.todolist.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ce.edu.todolist.bean.StatusTarefa;
import br.ce.edu.todolist.bean.TarefaBean;
import br.ce.edu.todolist.util.Conexao;
import br.ce.edu.todolist.util.PersistenceException;

public class TarefaDAOTest {
	
	private static Connection conn;
	private static TarefaDAO tarefaDAO;
	private static Statement statement;
	
	private static int idTarefa = 0;
	private static TarefaBean tarefa;
	
	@BeforeClass
	public static void setUpClass() {
		try {
			conn = Conexao.getInstance().getConnection();
			statement = conn.createStatement();
			tarefaDAO = new TarefaDAO();
			popularBancoDados();
		} catch (PersistenceException | SQLException e) {
			try {
				conn.close();
				statement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		tarefa = new TarefaBean();
		tarefa.setTitulo("Teste integracao 2");
		tarefa.setDescricao("Tarefa criada pelo teste de integracao para adicionar tarefa.");
		tarefa.setStatus(StatusTarefa.A_FAZER);
		tarefa.setUsuarioId(1);
	}

	private static void popularBancoDados() throws SQLException {
		
		StringBuilder sql = new StringBuilder();;
		sql.append("INSERT INTO TAREFA ");
		sql.append("(TR_TITULO, TR_DESCRICAO, TR_STATUS, USR_ID) ");
		sql.append("VALUES ('Teste integracao','Implementando os teste nas classe',");
		sql.append("'A','1')");

		statement.executeUpdate(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		ResultSet result = statement.getGeneratedKeys();
		
		if(result.next()){
			idTarefa = result.getInt(1);
		}
	}
	
	@AfterClass
	public static void tearDownClass() throws SQLException{
		String sql = "DELETE FROM TAREFA WHERE TR_ID = " + idTarefa;
		statement.executeUpdate(sql);
		statement.close();
		conn.close();
	}
	

	@Test
	public void deveConsultarTarefaPorId() throws SQLException, PersistenceException {
		//Cenario
		TarefaBean tarefaBanco = new TarefaBean();
		
		//Ação
		tarefaBanco= tarefaDAO.getTarefa(idTarefa);
		
		//Validação
		assertEquals(tarefaBanco.getId().intValue(), idTarefa);
	}
	
	@Test(expected=PersistenceException.class)
	public void naoDeveConsultarTarefaPorId() throws PersistenceException {
		//Cenario
		TarefaBean tarefaBanco = new TarefaBean();
		
		//Ação
		tarefaBanco= tarefaDAO.getTarefa(null);
		
		//Validação
		fail();
		
	}
	
	@Test
	public void deveAdicionarTarefa() throws PersistenceException, SQLException{
		
		//ação
		int id = tarefaDAO.addTarefa(tarefa);
		statement.executeUpdate("DELETE FROM TAREFA WHERE TR_ID="+id);
		
		//verificação
		assertTrue(id > 0);
		
	}
	
	@Test(expected=PersistenceException.class)
	public void naodeveAdicionarTarefa() throws PersistenceException, SQLException{
		
		//Cenario
		TarefaBean tarefaNull = new TarefaBean();
		
		//ação
		int id = tarefaDAO.addTarefa(tarefaNull);
		
		//Validação
		fail();
		
	}
	
	@Test
	public void deveEditarTarefa() throws PersistenceException, SQLException{
		//Cenario
		tarefa.setId(idTarefa);
		tarefa.setTitulo("Teste integração Editar");
		tarefa.setDescricao("Tarefa criada pelo teste de integracao para editar tarefa.");
		tarefa.setStatus(StatusTarefa.EM_ANDAMENTO);
		int retorno = 0;
		
		//Ação
		retorno = tarefaDAO.editarTarefa(tarefa);
		TarefaBean tarefaBanco = tarefaDAO.getTarefa(idTarefa);
		
		//Validação
		assertTrue(retorno > 0);
		assertEquals(tarefaBanco.getTitulo(), tarefa.getTitulo());
	}
	
	@Test(expected=PersistenceException.class)
	public void naoDeveEditarTarefa() throws PersistenceException, SQLException{
		
		//Cenario
		tarefa.setTitulo("Teste integração Editar");
		tarefa.setDescricao("Tarefa criada pelo teste de integracao para editar tarefa.");
		tarefa.setStatus(StatusTarefa.EM_ANDAMENTO);
		int retorno = 0;
		
		//Ação
		retorno = tarefaDAO.editarTarefa(tarefa);	
		
	}
	
	@Test
	public void deveRemoverTarefa() throws PersistenceException, SQLException{
		//Cenario
		TarefaBean tarefaRemover = new TarefaBean();
		tarefaRemover.setTitulo("Teste integracao Remover tarefa");
		tarefaRemover.setDescricao("Tarefa criada pelo teste de integracao para remover tarefa.");
		tarefaRemover.setStatus(StatusTarefa.A_FAZER);
		tarefaRemover.setUsuarioId(1);
		
		//Ação
		int id = tarefaDAO.addTarefa(tarefaRemover);
		int retorno = tarefaDAO.removerTarefa(id);
		TarefaBean tarefaBanco = tarefaDAO.getTarefa(id);
		
		//Validação
		assertTrue(retorno > 0);
		assertNull(tarefaBanco);

	}
	
	@Test(expected=PersistenceException.class)
	public void naoDeveRemoverTarefa() throws PersistenceException, SQLException{

		//Ação
		int retorno = tarefaDAO.removerTarefa(null);
		
		//Validação
		fail();

	}
	
	@Test
	public void deveListarTodasTarefas() throws PersistenceException{
		
		//Ação
		List<TarefaBean> lista = tarefaDAO.listarTarefas();
		
		//Validação
		assertTrue(lista.size() > 0);
	}
	
	@Test
	public void deveListarTarefaPorUsuario() throws PersistenceException{
		
		//Cenario
		int usuario = 1;
		
		//Ação
		List<TarefaBean> lista = tarefaDAO.listarTarefaPorUsuario(usuario);
		
		//Validação
		assertEquals(usuario, lista.get(0).getUsuarioId().intValue());
		
	}
	
	@Test(expected=PersistenceException.class)
	public void naoDeveListarTarefaPorUsuario() throws PersistenceException{
		
		//Ação
		List<TarefaBean> lista = tarefaDAO.listarTarefaPorUsuario(null);
		
		//Validação
		fail();
		
	}

}
