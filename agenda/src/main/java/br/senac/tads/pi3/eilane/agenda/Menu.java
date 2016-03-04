package br.senac.tads.pi3.eilane.agenda;

import java.util.Scanner;

/**
 *
 * @author Eilane
 * @author Igor Sato
 */

public class Menu {

    private int escolha;
    private Scanner input = new Scanner(System.in);

    // Construtor
    public Menu() {
    }

    //Getter e Setters
    public void setEscolha(int escolha) {
        this.escolha = escolha;
    }

    public int getEscolha() {
        return escolha;
    }

    // Switch case para Menu principal do programa
    public void inicioPrograma() {
        
        Agenda agenda = new Agenda();

        int opcao = 0;

        do {

            opcao = menuInicial(); //Chama o método que exibe o menú gráfico

            switch (opcao) {

                case 1:
                    agenda.listarContatos();
                    break;

                case 2:
                    agenda.CadastrarPessoa("igOR", "2014-11-25", "11 94545-7777", "iGOR@SPREAD.COM");// consertar para não ter que passar por parâmetro
                    System.out.println("Cadastro realizado com sucesso!");
                    System.out.println("");
                    break;

                case 3:
                    
                    agenda.editarContatos();
                    System.out.println("Edição realizada com sucesso!");
                    System.out.println("");
                    break;

                case 4:
                    agenda.listarContatos();
                    System.out.println("");
                    Scanner input = new Scanner (System.in);
                    
                    System.out.println("Digite o id do contato");
                    int id = input.nextInt();

                    Boolean result = agenda.excluirContato(id);
                    if(result = true){
                    System.out.println("Exclusão realizada com sucesso!");
                    System.out.println("");
                    }
                    break;

                default:
                    System.out.println("Obrigado. Seu Mané!!!");
                    System.out.println("");
                    opcao = 0;
                    break;

            }
            opcao = -1;

        } while (opcao != 0);

    }

    // Exibe menu e lê a opção escolhida
    public int menuInicial() {

        System.out.println("");
        System.out.println("#################################################################");
        System.out.println("#################################################################");
        System.out.println("############################## MENU #############################");
        System.out.println("#################################################################");
        System.out.println("#################### (1)  Listar Contatos    ####################");
        System.out.println("#################### (2)  Cadastrar Contato  ####################");
        System.out.println("#################### (3)  Editar Contato     ####################");
        System.out.println("#################### (4)  Excluir Contato    ####################");
        System.out.println("#################### (0)  Sair               ####################");
        System.out.println("#################################################################");
        System.out.println("#################################################################");
        System.out.println("#################################################################");
        System.out.println("");

        do {
            System.out.print("Digite a opção desejada: ");
            this.setEscolha(input.nextInt());
            System.out.println("");
        } while (this.getEscolha() < 0 || this.getEscolha() > 4);
        return this.getEscolha();
    }

    public int alterarDados() {

        System.out.println("");
        System.out.println("#################################################################");
        System.out.println("############################## Oções#############################");
        System.out.println("#################################################################");
        System.out.println("#################### (1)  Nome               ####################");
        System.out.println("#################### (2)  Data de Nascimento ####################");
        System.out.println("#################### (3)  Telefone           ####################");
        System.out.println("#################### (4)  Email              ####################");
        System.out.println("#################### (0)  Sair               ####################");
        System.out.println("#################################################################");
        System.out.println("");

        do {          
            
            System.out.print("Digite a opção desejada: ");
            this.setEscolha(input.nextInt());
            System.out.println("");

        } while (this.getEscolha() < 0 || this.getEscolha() > 4);
        return this.getEscolha();
    }
    
    
    public int opcaoAlterarDados() {
        
        do {          
            
            Agenda agenda = new Agenda();
            
            agenda.listarContatos();
            System.out.println("");
            System.out.print("Digite o ID do contato: ");
            this.setEscolha(input.nextInt());         
        } while (this.getEscolha() < 0 || this.getEscolha() > 1000);
        return this.getEscolha();
    }

    public int cadastrarDados(int opcao) {

        System.out.println("");
        System.out.println("#################################################################");
        System.out.println("##########################  CADASTRO  ###########################");
        System.out.println("#################################################################");
        System.out.println("");

        do {
            System.out.print("Digite a opção desejada: ");
            this.setEscolha(input.nextInt());
            System.out.println("");
        } while (this.getEscolha() < 0 || this.getEscolha() > 4);
        return this.getEscolha();
    }

}
