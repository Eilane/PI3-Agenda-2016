/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.eilane.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author temp.cas
 */
public class Agenda {
    
    private Connection obterConexao() throws SQLException, ClassNotFoundException{
    Connection conn = null;
    
    // Passo 1: Regitrar driver JDBC 
    Class.forName("org.apache.derby.jdbc.ClientDataSource");
    
    // Passo 2: Abrir a conexão 
    conn = DriverManager.getConnection(           //SecurityMechanism - padrão que define que será informado usuário e senha
            "jdbc:derby://localhost:1527/agendadb;SecurityMechanism=3",
            "app", // usuario 
            "app");// senha 
    
    return conn;
    
    
    }
    
    
}
