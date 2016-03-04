/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.eilane.agenda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author temp.cas
 */
public class Agenda {

    //Atributos
    private int idContato;
    private String nomeContato;
    private String dataNasc;
    private String telefone;
    private String email;

    private Connection conn = null;

    //##########################################################################
    // Conexão com Banco
    public Connection obterConexao() throws SQLException, ClassNotFoundException {

        // Passo 1: Regitrar driver JDBC 
        Class.forName("org.apache.derby.jdbc.ClientDataSource");

        // Passo 2: Abrir a conexão 
        conn = DriverManager.getConnection( //SecurityMechanism - padrão que define que será informado usuário e senha
                "jdbc:derby://localhost:1527/agendadb;SecurityMechanism=3",
                "app", // usuario 
                "app");// senha 

        return conn;

    }

    //##########################################################################
    public void listarContatos() {
        Statement stmt = null;
        Connection conn = null;

        String sql = "SELECT ID_CONTATO, NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL FROM TB_CONTATO";
        try {
            conn = obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

            while (resultados.next()) {
                Long id = resultados.getLong("ID_CONTATO");
                String nome = resultados.getString("NM_CONTATO");
                Date dataNasc = resultados.getDate("DT_NASCIMENTO");
                String email = resultados.getString("VL_EMAIL");
                String telefone = resultados.getString("VL_TELEFONE");
                System.out.println(String.valueOf(id) + ", " + nome + ", " + formatadorData.format(dataNasc) + ", " + email + ", " + telefone);
            }

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

    //##########################################################################
    public void alterarContatos() {
        PreparedStatement pst = null;
        Connection conn = null;

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite a opção a ser alterada");
            int opcao = scanner.nextInt();

            while (opcao != 0) {
                switch (opcao) {

                    case 1:
                        System.out.println("Digite Nome: ");
                        Scanner inputNome = new Scanner(System.in);
                        setNomeContato(inputNome.next());
                        
                        String nome = "UPDATE TB_CONTATO "
                                + "SET NM_CONTATO  = ? "
                                + "WHERE ID_CONTATO = ? ";
                        
                        conn = obterConexao();
                        pst = conn.prepareStatement(nome);

                        pst.setString(1, this.nomeContato);
                        pst.setInt(2, this.idContato);

                        scanner.reset();

                        break;

                    case 2:
                        System.out.println("Digite a Data de Nascimento ex.(27/02/1999: ");
                        Scanner inputDataNasc = new Scanner(System.in);
                        this.dataNasc = inputDataNasc.next();
                        String dtNasc = "UPDATE TB_CONTATO SET DT_NASCIMENTO WHERE ID_CONTATO = ?";
                        conn = obterConexao();
                        pst = conn.prepareStatement(dtNasc);
                        pst.executeQuery(dtNasc);

                        pst.setString(1, this.dataNasc);

                        break;

                    case 3:
                        System.out.println("Digite o Telefone ex.(11 95666-7890): ");
                        Scanner inputTelefone = new Scanner(System.in);
                        this.telefone = inputTelefone.next();
                        String tel = "UPDATE TB_CONTATO SET VL_TELEFONE WHERE ID_CONTATO = ?";
                        conn = obterConexao();
                        pst = conn.prepareStatement(tel);
                        pst.executeQuery(tel);

                        pst.setString(1, this.telefone);

                        break;

                    case 4:
                        System.out.println("Digite o Email: ");
                        Scanner inputEmail = new Scanner(System.in);
                        this.email = inputEmail.next();
                        String email = "UPDATE TB_CONTATO SET VL_EMAIL WHERE ID_CONTATO = ?";
                        conn = obterConexao();
                        pst = conn.prepareStatement(email);
                        pst.executeQuery(email);

                        pst.setString(1, this.email);

                        break;

                }
                opcao = 0;

            }

        } catch (SQLException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
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

    //##########################################################################
    public boolean excluirContato(int id) {
        Statement stmt = null;
        Connection conn = null;

        String sql = "DELETE FROM TB_CONTATO WHERE ID_CONTATO = " + id;
        try {
            conn = obterConexao();
            stmt = conn.createStatement();

            if (stmt.executeUpdate(sql, id) > 0) {
                return true;
            }

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
        return false;
    }

    //Getters e Setters ########################################################
    public int getIdContato() {
        return idContato;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
