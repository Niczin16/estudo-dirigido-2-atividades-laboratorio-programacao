/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projeto.biblioteca;

import projeto.biblioteca.telas.Dados;
import projeto.biblioteca.telas.JanelaPrincipal;

/**
 * Classe principal: agora abre a janela em Java Swing.
 * O menu antigo do terminal continua em ProjetoBibliotecaTerminal.java.
 *
 * @author Aluno
 */
public class ProjetoBiblioteca {

    public static void main(String[] args) {
        Dados dados = new Dados();                          // gerenciadores e listas do sistema
        JanelaPrincipal janela = new JanelaPrincipal(dados);
        janela.setVisible(true);
    }

}
