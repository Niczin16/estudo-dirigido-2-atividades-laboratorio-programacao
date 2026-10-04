package projeto.biblioteca.telas;

import java.util.ArrayList;
import projeto.biblioteca.Biblioteca;
import projeto.biblioteca.Emprestimo;
import projeto.biblioteca.GerirEmprestimos;
import projeto.biblioteca.GestaoPessoas;
import projeto.biblioteca.Livro;
import projeto.biblioteca.Pessoa;

/**
 * Guarda tudo o que as telas precisam usar.
 *
 * As classes Biblioteca, GestaoPessoas e GerirEmprestimos guardam suas listas
 * como "private" e não têm um método que devolva a lista (os métodos listar
 * só imprimem no console). Como não podemos alterar essas classes, guardamos
 * aqui uma cópia de cada lista, só para mostrar nas tabelas.
 *
 * As cópias guardam os MESMOS objetos, então quando um método original edita
 * um livro ou uma pessoa, a mudança aparece na tabela também.
 */
public class Dados {

    // Gerenciadores originais do projeto (não foram alterados)
    public Biblioteca biblioteca = new Biblioteca();
    public GestaoPessoas gestaoPessoas = new GestaoPessoas();
    public GerirEmprestimos gerirEmprestimos = new GerirEmprestimos();

    // Cópias das listas, só para mostrar nas tabelas
    public ArrayList<Livro> livros = new ArrayList<>();
    public ArrayList<Pessoa> pessoas = new ArrayList<>();
    public ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    // Contadores de ID (antes eram gIdLivros, gIdPessoas e gIdEmprestimos na main)
    public int proximoIdLivro = 1;
    public int proximoIdPessoa = 1;
    public int proximoIdEmprestimo = 1;
}
