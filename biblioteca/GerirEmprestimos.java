/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.biblioteca;

import java.util.ArrayList;
/**
 *
 * @author Aluno
 */
public class GerirEmprestimos {
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();   
    
    // <editor-fold defaultstate="collapsed" desc="realizarEmprestimo">
    public void realizarEmprestimo(Emprestimo emprestimo){
        if (emprestimo.getLivros().isEmpty()){
            System.out.println("Nenhum livro cadastrado!");
            return;
        }
        
        emprestimos.add(emprestimo);
        System.out.println("Emprestimo cadastrado com sucesso!");       
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="finalizarEmprestimo">
    public void finalizarEmprestimo(Integer controle){
        if (emprestimos.isEmpty()){
            System.out.println("Nao existe emprestimos para finalizar");
            return;
        }
        
        boolean foiRemovido = emprestimos.removeIf(lEmprestimo -> lEmprestimo.getControle().equals(controle));
        
        if (foiRemovido){
            System.out.println("Emprestimo com o ID [" + controle + "] finalizado com sucesso!");
        }else{
            System.out.println("Emprestimo com o ID [" + controle + "] nao foi encontrado!");
        }                
        
    }    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="listarEmprestimos">
    public void listarEmprestimos(){
        if (emprestimos.isEmpty()){
            System.out.println("A lista de emprestimos esta vazia!");
            return;
        }
        
        for (Emprestimo lEmprestimo : emprestimos){
            lEmprestimo.descricaoEmprestimos();
        }
        
    }
    // </editor-fold>
    
}
