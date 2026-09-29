package projeto.biblioteca.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Tela de boas-vindas: mostra quantos livros, pessoas e empréstimos existem.
 */
public class PainelInicio extends JPanel {

    private Dados dados;

    // Labels que mostram os números (atualizados sempre que a tela abre)
    private JLabel lblTotalLivros = new JLabel("0");
    private JLabel lblTotalPessoas = new JLabel("0");
    private JLabel lblTotalEmprestimos = new JLabel("0");

    public PainelInicio(Dados dados) {
        this.dados = dados;

        setLayout(new BorderLayout(20, 24));
        setBackground(Cores.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        add(Estilo.criarCabecalho("Bem-vindo à Biblioteca",
                "Escolha uma opção no menu ao lado para começar."), BorderLayout.NORTH);

        // Três cartões coloridos, lado a lado
        JPanel cartoes = new JPanel(new GridLayout(1, 3, 20, 0));
        cartoes.setOpaque(false);
        cartoes.add(criarCartaoNumero(lblTotalLivros, "Livros cadastrados", Cores.AZUL));
        cartoes.add(criarCartaoNumero(lblTotalPessoas, "Pessoas cadastradas", Cores.VERDE));
        cartoes.add(criarCartaoNumero(lblTotalEmprestimos, "Empréstimos ativos", Cores.AMARELO));

        JPanel centro = new JPanel(new BorderLayout(0, 24));
        centro.setOpaque(false);
        centro.add(cartoes, BorderLayout.NORTH);
        centro.add(criarCartaoDicas(), BorderLayout.CENTER);

        // Painel extra só para o conteúdo ficar no topo, sem esticar até o fim da tela
        JPanel topo = new JPanel(new BorderLayout());
        topo.setOpaque(false);
        topo.add(centro, BorderLayout.NORTH);

        add(topo, BorderLayout.CENTER);
    }

    // Cartão colorido com um número grande e uma descrição embaixo
    private JPanel criarCartaoNumero(JLabel lblNumero, String descricao, Color cor) {
        lblNumero.setFont(Cores.FONTE_NUMERO);
        lblNumero.setForeground(Color.WHITE);

        JLabel lblDescricao = new JLabel(descricao);
        lblDescricao.setFont(Cores.FONTE_NEGRITO);
        lblDescricao.setForeground(Color.WHITE);

        JPanel cartao = new JPanel(new GridLayout(2, 1));
        cartao.setBackground(cor);
        cartao.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));
        cartao.add(lblNumero);
        cartao.add(lblDescricao);
        return cartao;
    }

    // Cartão branco com um passo a passo de uso
    private JPanel criarCartaoDicas() {
        JPanel cartao = Estilo.criarCartao();

        JLabel titulo = new JLabel("Como usar");
        titulo.setFont(Cores.FONTE_NEGRITO);
        titulo.setForeground(Cores.TEXTO);

        JPanel passos = new JPanel(new GridLayout(3, 1, 0, 8));
        passos.setOpaque(false);
        passos.add(criarPasso("1.  Em Livros, cadastre E-Books e livros físicos."));
        passos.add(criarPasso("2.  Em Pessoas, cadastre membros e funcionários."));
        passos.add(criarPasso("3.  Em Empréstimos, escolha o membro, o funcionário e os livros."));

        cartao.add(titulo, BorderLayout.NORTH);
        cartao.add(passos, BorderLayout.CENTER);
        return cartao;
    }

    private JLabel criarPasso(String texto) {
        JLabel passo = new JLabel(texto);
        passo.setFont(Cores.FONTE_NORMAL);
        passo.setForeground(Cores.TEXTO_CLARO);
        return passo;
    }

    // Recalcula os números dos cartões
    public void atualizar() {
        lblTotalLivros.setText(String.valueOf(dados.livros.size()));
        lblTotalPessoas.setText(String.valueOf(dados.pessoas.size()));
        lblTotalEmprestimos.setText(String.valueOf(dados.emprestimos.size()));
    }
}
