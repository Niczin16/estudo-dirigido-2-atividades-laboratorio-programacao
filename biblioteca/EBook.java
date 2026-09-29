/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.biblioteca;

/**
 *
 * @author Aluno
 */
public class EBook extends Livro{
    private double TamanhoArquivo;

    public EBook(double TamanhoArquivo, Integer Controle, String Titulo, String Autor, Integer NumeroPaginas) {
        super(Controle, Titulo, Autor, NumeroPaginas);
        this.TamanhoArquivo = TamanhoArquivo;
    }    
    
    public double getTamanhoArquivo() {
        return TamanhoArquivo;
    }

    public void setTamanhoArquivo(double TamanhoArquivo) {
        this.TamanhoArquivo = TamanhoArquivo;
    }

    @Override
    public void descricao() {
        System.out.printf(" [%-2s] | Titulo: %-22s | Nome Autor: %-18s | Numero Paginas: %3d pags. | Tamanho Arquivo: %.2f MB\n", 
                getControle(), getTitulo(), getAutor(), getNumeroPaginas(), getTamanhoArquivo());
    }            

}
