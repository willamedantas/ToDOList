package br.ce.edu.todolist.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public boolean isConnected;
    private static Conexao conexaoUtil;

    public static Conexao getInstance() {
        if (conexaoUtil == null) {
            conexaoUtil = new Conexao();
        }
        return conexaoUtil;
    }

    public Connection getConnection() throws PersistenceException {
    	
    	isConnected = false;
    	Connection conn = null;
    	try {
    		
	        Class.forName("com.mysql.jdbc.Driver");
	  		conn = DriverManager.getConnection("jdbc:mysql://localhost/todolist?autoReconnect=true&useSSL=false", "root", "root");
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		}
        
    	isConnected = true;
        return conn;
    }
}
