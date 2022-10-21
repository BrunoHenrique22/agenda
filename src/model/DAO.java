package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	// criando vari�veis encapsuladas para acesso ao banco
	private String driver = "com.mysql.cj.jdbc.Driver"; // <---- linha do drive
	private String url = "jdbc:mysql://10.26.49.103:3306/agenda"; // <---- linha do server o IP e o nome do banco
	private String user = "root"; // <---- linha do usuario
	private String password = "123@senac"; // <---- linha do da senha do servidor

	/**
	 * M�todo responsavel por abri uma conex�o com o bando de dados
	 * 
	 * @return con
	 */
	
	//CONECTANDO O BANCO DE DADOS COM O JAVA
	public Connection conectar() {
		// Criar um objeto
		Connection con = null;
		// tratamento de exce��es
		try {

			//logica principal pra abrir a conex�o
			//uso do Driver 
			Class.forName(driver);
			//obter os parametros da conex�o (informa��es do servidor) 
			con = DriverManager.getConnection(url, user, password); //conectar no servidor 
			//retornar a cox�o (abrir a porta da geladeira)
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}

	}
}
