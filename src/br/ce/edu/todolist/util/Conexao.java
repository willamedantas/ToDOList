package br.ce.edu.todolist.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
	
	public boolean isConnected;
    private static conexao conexaoUtil;

    public static conexao getInstance() {
        if (conexaoUtil == null) {
            conexaoUtil = new conexao();
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

    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = conexao.getInstance().getConnection();
            System.out.println(conn);

        } catch (Exception e) {
            System.out.println("Problemas ao tentar conectar com o banco de dados: " + e);
        }
    }
}
