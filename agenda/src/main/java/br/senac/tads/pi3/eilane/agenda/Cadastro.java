/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.eilane.agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        SimpleDateFormat formdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Formata data de cadastro

        String sql = "INSERT INTO TB_CONTATO (NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL, DT_CADASTRO)VALUES(?,?,?,?,?)";

        try {
            conn = conexao.obterConexao();
            PreparedStatement statement = conn.prepareStatement(sql);;//

            statement.setString(1, nome);
            statement.setString(2, dt_nasc);
            statement.setString(3, telefone);
            statement.setString(4, email);
            statement.setString(5, formdate.format(Calendar.getInstance().getTime()));

            statement.execute(); // executa 
            statement.close();  // Fecha

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
