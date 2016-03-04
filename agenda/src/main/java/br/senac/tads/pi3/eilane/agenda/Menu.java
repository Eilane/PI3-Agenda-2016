/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.eilane.agenda;

import java.util.Scanner;

/**
 *
 * @author iosato
 */
public class Menu {
    
    private int escolha;
    
    
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
    
    // Exibe menu e lê a opção escolhida
    public int menuInicial(Scanner input, Opcao o) {   //chama o logo inicial

        System.out.println("");
        System.out.println("#################################################################");
        System.out.println("#################################################################");
        System.out.println("############################## MENU #############################");
        System.out.println("#################################################################");
        System.out.println("#################### (1)  Buscar Contato     ####################");
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
    
    public int alterarDados(Scanner input, Opcao o) {   //chama o logo inicial

        System.out.println("");
        System.out.println("#################################################################");
        System.out.println("#################################################################");
        System.out.println("############################## MENU #############################");
        System.out.println("#################################################################");
        System.out.println("#################### (1)  Buscar Contato     ####################");
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
    
    
}
