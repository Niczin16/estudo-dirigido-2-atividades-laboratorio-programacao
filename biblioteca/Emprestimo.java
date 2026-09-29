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
public class Emprestimo {
    private Integer Controle;
    private String DataEmprestimo;
    private String Membro;
    private String FuncionarioAtendeu;
    private ArrayList<Livro> livros = new ArrayList<>();

    public Emprestimo(Integer Controle, String DataEmprestimo, String Membro, String FuncionarioAtendeu) {
        this.Controle = Controle;
        this.DataEmprestimo = DataEmprestimo;
        this.Membro = Membro;
        this.FuncionarioAtendeu = FuncionarioAtendeu;
    }    
    
    public Integer getControle() {
        return Controle;
    }

    public void setControle(Integer Controle) {
        this.Controle = Controle;
    }

    public String getDataEmprestimo() {
        return DataEmprestimo;
    }

    public void setDataEmprestimo(String DataEmprestimo) {
        this.DataEmprestimo = DataEmprestimo;
    }

    public String getMembro() {
        return Membro;
    }

    public void setMembro(String Membro) {
        this.Membro = Membro;
    }

    public String getFuncionarioAtendeu() {
        return FuncionarioAtendeu;
    }

    public void setFuncionarioAtendeu(String FuncionarioAtendeu) {
        this.FuncionarioAtendeu = FuncionarioAtendeu;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }
    
    public void descricaoEmprestimos(){
        System.out.printf(" id: [ %d ] | Data do emprestimo: [ %s ] | Quem pegou o livro: [ %s ] |"
                + " Funcionario que atendeu: [ %s ] ",
                getControle(), getDataEmprestimo(), getMembro(), getFuncionarioAtendeu());
        
        System.out.println("\n\n Livros em emprestimo: \n\n");
        
        for (Livro lLivro : livros){
            lLivro.descricao();
        }
        System.out.println("\n\n");
    }
        
}
