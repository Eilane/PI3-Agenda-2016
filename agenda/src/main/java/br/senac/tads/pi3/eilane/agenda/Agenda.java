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
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eilane
 * @author Igor Sato
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
    //##########################################################################
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
    //LISTAR CONTATOS
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
    //CADASTRO DE CONTATOS
    //##########################################################################
    public void CadastrarPessoa(String nome, String dt_nasc, String telefone, String email) {
        Agenda conexao = new Agenda();
        Statement stmt = null;
        Connection conn = null;

        SimpleDateFormat formdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Formata data de cadastro

        String sql = "INSERT INTO TB_CONTATO (NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL, DT_CADASTRO)VALUES(?,?,?,?,?)";

        try {
            conn = conexao.obterConexao();
            PreparedStatement statement = conn.prepareStatement(sql);//

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

    //##########################################################################
    //EDIÇÃO DE CONTATOS
    //##########################################################################
    public void editarContatos() {
        PreparedStatement pst = null;
        Connection conn = null;

        try {
            Scanner scanner = new Scanner(System.in);
            Menu menu2 = new Menu();

            int escolha = menu2.alterarDados();
            
            System.out.println("Digite a opção a ser alterada");
            

            while (escolha != 0) {

                this.idContato = escolha;

                switch (escolha) {

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
                        pst.executeUpdate();
                        scanner.reset();

                        break;

                    case 2:
                        System.out.println("Digite a Data de Nascimento ex.(27/02/1999: ");
                        Scanner inputDataNasc = new Scanner(System.in);
                        setDataNasc(inputDataNasc.next());

                        String dtNasc = "UPDATE TB_CONTATO "
                                + "SET DT_NASCIMENTO = ?"
                                + "WHERE ID_CONTATO = ?";
                        conn = obterConexao();
                        pst = conn.prepareStatement(dtNasc);

                        pst.setString(1, this.dataNasc);
                        pst.setInt(2, this.idContato);
                        pst.executeUpdate();
                        scanner.reset();

                        break;

                    case 3:
                        System.out.println("Digite o Telefone ex.(11 95666-7890): ");
                        Scanner inputTelefone = new Scanner(System.in);
                        this.telefone = inputTelefone.next();
                        String tel = "UPDATE TB_CONTATO "
                                + "SET VL_TELEFONE = ?"
                                + "WHERE ID_CONTATO = ?";
                        conn = obterConexao();
                        pst = conn.prepareStatement(tel);

                        pst.setString(1, this.telefone);
                        pst.setInt(2, this.idContato);
                        pst.executeUpdate();
                        scanner.reset();

                        break;

                    case 4:
                        System.out.println("Digite o Email: ");
                        Scanner inputEmail = new Scanner(System.in);
                        this.email = inputEmail.next();
                        String email = "UPDATE TB_CONTATO "
                                + "SET VL_EMAIL = ?"
                                + "WHERE ID_CONTATO = ?";
                        conn = obterConexao();
                        pst = conn.prepareStatement(email);

                        pst.setString(1, this.email);
                        pst.setInt(2, this.idContato);
                        pst.executeUpdate();
                        scanner.reset();

                        break;

                    default:
                        escolha = 0;
                        break;

                }
                escolha = 0;

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
    //EXCLUIR CONTATOS
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

    //##########################################################################
    //GETTERS E SETTERS
    //##########################################################################
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
