/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.eilane.agenda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author temp.cas
 */
public class Cadastro {

    public void CadastrarPessoa(String nome, String dt_nasc, String telefone, String email) {
        Agenda conexao = new Agenda();
        Statement stmt = null;
        Connection conn = null;

        DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        String dt_cadastro = "2016-01-01";
        
        
        String sql = "INSERT INTO TB_CONTATO (NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL,DT_CADASTRO) VALUES('"
                +nome+ "' , '" + dt_nasc + "'" + " , '" +telefone+"' , '"+email+ "' , '" +dt_cadastro+"');";
        try {
            conn = conexao.obterConexao();
            stmt = conn.createStatement();
              

            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
