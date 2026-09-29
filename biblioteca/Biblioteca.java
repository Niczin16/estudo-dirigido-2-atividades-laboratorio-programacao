/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.biblioteca;

import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author Aluno
 */
public class Biblioteca {

    private ArrayList<Livro> livros = new ArrayList<>();
    
    // <editor-fold defaultstate="collapsed" desc="incluirLivro">
    public void incluirLivro(Livro livro){
        livros.add(livro);
        System.out.println(" \n Livro adicionado com sucesso!");        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="editarLivroEbook">
    public void editarLivroEbook(
                Livro livro     ,
                Integer Controle, 
                String Autor    , 
                Double Tamanho  , 
                String Titulo   , 
                Integer NumeroPaginas 
        ){
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado na biblioteca.");
            return; 
        }
        
        for(Livro lLivro: livros){
            if(lLivro.getControle().equals(Controle)){                
                
                lLivro.setAutor(Autor);
                lLivro.setNumeroPaginas(NumeroPaginas);
                lLivro.setTitulo(Titulo);
                ((EBook) lLivro).setTamanhoArquivo(Tamanho);                          
                
                System.out.println("Livro editado com sucesso!");
            }            
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="editarLivroFisico">
    public void editarLivroFisico(
                Livro livro     ,
                Integer Controle, 
                String Autor    , 
                Double Peso  , 
                String Titulo   , 
                Integer NumeroPaginas 
        ){
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado na biblioteca.");
            return; 
        }
        
        for(Livro lLivro: livros){
            if(lLivro.getControle().equals(Controle)){                
                
                lLivro.setAutor(Autor);
                lLivro.setNumeroPaginas(NumeroPaginas);
                lLivro.setTitulo(Titulo);
                ((LivroFisico) lLivro).setPeso(Peso);                          
                
                System.out.println("Livro editado com sucesso!");
            }            
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="removerLivro">
    public boolean removerLivro(Integer controle) {        
        if (livros.isEmpty()) {
            System.out.println("Nao ha livros cadastrados para remover!");
            return false;
        }
       
        boolean foiRemovido = livros.removeIf(lLivro -> lLivro.getControle().equals(controle));

        if (foiRemovido) {
            System.out.println("Livro com o ID [" + controle + "] removido com sucesso!");
            return true;
        } else {
            System.out.println("Erro: Livro com o ID [" + controle + "] nao foi encontrado.");
            return false;
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="listarLivros">
    public void listarLivros(){
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado na biblioteca.");
            return; 
        }
        
        for (Livro lLivro : livros) {
            lLivro.descricao();
        }    
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="buscarLivro">
    public Livro buscarLivro(Integer Controle){
        for (Livro lLivro : livros){
            if (lLivro.getControle().equals(Controle)){
                return lLivro;
            }
        }
        return null;
    }
    // </editor-fold>    
    
}
