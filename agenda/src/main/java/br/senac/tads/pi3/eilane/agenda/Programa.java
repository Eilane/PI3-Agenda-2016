
package br.senac.tads.pi3.eilane.agenda;

/**
 *
 * @author iosato
 */
public class Programa {
    
    public static void main(String[] args) {
        
        Agenda agenda = new Agenda();
        
        agenda.listarContatos();
        agenda.excluirContato(2);
        
        System.out.println("");
        
        agenda.listarContatos();
        
    }
  
}
