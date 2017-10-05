package br.ce.edu.todolist.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class ConexaoTest {
	
	@Test
	public void deveConectarBancoDeDados() throws PersistenceException, SQLException{
		
		Connection conn = null;
		
        conn = Conexao.getInstance().getConnection();
        boolean validado = conn.isValid(1000);
        
        Assert.assertTrue(validado);
	}

}
