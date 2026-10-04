/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.biblioteca;

/**
 *
 * @author Aluno
 */

/* Lembrete de estudo - classe abstrata nunca é instanciada, ela é somente uma base para as outras que a herdarão */
public class Livro {
    private Integer Controle; 
    private String Titulo;
    private String Autor;
    private Integer NumeroPaginas;

    public Livro(Integer Controle, String Titulo, String Autor, Integer NumeroPaginas) {      
        this.Controle = Controle;
        this.Titulo = Titulo;
        this.Autor = Autor;
        this.NumeroPaginas = NumeroPaginas;
    }    

    public Integer getControle() {
        return Controle;
    } 

    public void setControle(Integer Controle) {
        this.Controle = Controle;
    }
           
    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }  
    
    public String getAutor() {
        return Autor;
    }
    
    public void setAutor(String Autor) {
        this.Autor = Autor;
    }  
    
    public Integer getNumeroPaginas() {
        return NumeroPaginas;
    }

    public void setNumeroPaginas(Integer NumeroPaginas) {
        this.NumeroPaginas = NumeroPaginas;    
    }
    
    public void descricao() {
        System.out.printf(" [ %-2s ] | %-25s |  %-20s | %3d págs.\n", 
                Controle ,Titulo, Autor, NumeroPaginas);
    }
}
