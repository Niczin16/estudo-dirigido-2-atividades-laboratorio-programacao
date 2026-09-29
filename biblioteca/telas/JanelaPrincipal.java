package projeto.biblioteca.telas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Janela principal do sistema.
 * À esquerda fica o menu; à direita, a área onde as telas são trocadas.
 */
public class JanelaPrincipal extends JFrame {

    // CardLayout funciona como um baralho: mostra uma tela (carta) de cada vez
    private CardLayout cartas = new CardLayout();
    private JPanel areaTelas = new JPanel(cartas);

    // As telas do sistema
    private PainelInicio painelInicio;
    private PainelLivros painelLivros;
    private PainelPessoas painelPessoas;
    private PainelEmprestimos painelEmprestimos;

    // Botões do menu (guardados para podermos destacar o que está ativo)
    private JButton btnInicio;
    private JButton btnLivros;
    private JButton btnPessoas;
    private JButton btnEmprestimos;

    public JanelaPrincipal(Dados dados) {
        setTitle("Projeto Biblioteca");
        setSize(1280, 760);
        setMinimumSize(new Dimension(1000, 620));
        setLocationRelativeTo(null); // abre no centro da tela
        setExtendedState(JFrame.MAXIMIZED_BOTH); // abre maximizada, usando a tela toda
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Cria as telas, todas usando os mesmos dados
        painelInicio = new PainelInicio(dados);
        painelLivros = new PainelLivros(dados);
        painelPessoas = new PainelPessoas(dados);
        painelEmprestimos = new PainelEmprestimos(dados);

        // Coloca cada tela no "baralho" com um nome
        areaTelas.add(painelInicio, "inicio");
        areaTelas.add(painelLivros, "livros");
        areaTelas.add(painelPessoas, "pessoas");
        areaTelas.add(painelEmprestimos, "emprestimos");

        add(criarMenu(), BorderLayout.WEST);
        add(areaTelas, BorderLayout.CENTER);

        abrirInicio(); // tela que aparece ao abrir o programa
    }

    // Monta o menu lateral escuro
    private JPanel criarMenu() {
        JPanel menu = new JPanel(new BorderLayout());
        menu.setBackground(Cores.MENU_FUNDO);
        menu.setPreferredSize(new Dimension(230, 0));
        menu.setBorder(BorderFactory.createEmptyBorder(28, 0, 20, 0));

        // Nome do sistema no topo do menu
        JLabel logo = new JLabel("Biblioteca");
        logo.setFont(Cores.FONTE_TITULO);
        logo.setForeground(Color.WHITE);

        JLabel slogan = new JLabel("Sistema de gestão");
        slogan.setFont(Cores.FONTE_SUBTITULO);
        slogan.setForeground(Cores.MENU_TEXTO);

        JPanel topo = new JPanel(new GridLayout(2, 1));
        topo.setOpaque(false);
        topo.setBorder(BorderFactory.createEmptyBorder(0, 24, 28, 0));
        topo.add(logo);
        topo.add(slogan);

        // Botões do menu
        btnInicio = criarBotaoMenu("Início");
        btnLivros = criarBotaoMenu("Livros");
        btnPessoas = criarBotaoMenu("Pessoas");
        btnEmprestimos = criarBotaoMenu("Empréstimos");

        btnInicio.addActionListener(e -> abrirInicio());
        btnLivros.addActionListener(e -> abrirLivros());
        btnPessoas.addActionListener(e -> abrirPessoas());
        btnEmprestimos.addActionListener(e -> abrirEmprestimos());

        JPanel botoes = new JPanel(new GridLayout(4, 1, 0, 6));
        botoes.setOpaque(false);
        botoes.add(btnInicio);
        botoes.add(btnLivros);
        botoes.add(btnPessoas);
        botoes.add(btnEmprestimos);

        JPanel parteDeCima = new JPanel(new BorderLayout());
        parteDeCima.setOpaque(false);
        parteDeCima.add(topo, BorderLayout.NORTH);
        parteDeCima.add(botoes, BorderLayout.CENTER);

        // Botão Sair no rodapé do menu (era a opção -1 do terminal)
        JButton btnSair = criarBotaoMenu("Sair");
        btnSair.addActionListener(e -> System.exit(0));

        menu.add(parteDeCima, BorderLayout.NORTH);
        menu.add(btnSair, BorderLayout.SOUTH);
        return menu;
    }

    // Botão do menu: fundo escuro, texto claro, alinhado à esquerda
    private JButton criarBotaoMenu(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(Cores.FONTE_NEGRITO);
        botao.setForeground(Cores.MENU_TEXTO);
        botao.setBackground(Cores.MENU_FUNDO);
        botao.setHorizontalAlignment(SwingConstants.LEFT);
        botao.setFocusPainted(false);
        botao.setOpaque(true);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(230, 46));
        botao.setBorder(BorderFactory.createEmptyBorder(0, 28, 0, 0));
        return botao;
    }

    // Pinta todos os botões como normais e destaca só o botão da tela aberta
    private void destacarBotao(JButton ativo) {
        JButton[] todos = {btnInicio, btnLivros, btnPessoas, btnEmprestimos};
        for (JButton botao : todos) {
            botao.setBackground(Cores.MENU_FUNDO);
            botao.setForeground(Cores.MENU_TEXTO);
            botao.setBorder(BorderFactory.createEmptyBorder(0, 28, 0, 0));
        }
        ativo.setBackground(Cores.MENU_SELECIONADO);
        ativo.setForeground(Color.WHITE);
        // Faixa azul à esquerda do botão ativo
        ativo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 5, 0, 0, Cores.AZUL),
                BorderFactory.createEmptyBorder(0, 23, 0, 0)));
    }

    // Cada método abaixo atualiza a tela e depois a mostra
    private void abrirInicio() {
        painelInicio.atualizar();
        cartas.show(areaTelas, "inicio");
        destacarBotao(btnInicio);
    }

    private void abrirLivros() {
        painelLivros.atualizar();
        cartas.show(areaTelas, "livros");
        destacarBotao(btnLivros);
    }

    private void abrirPessoas() {
        painelPessoas.atualizar();
        cartas.show(areaTelas, "pessoas");
        destacarBotao(btnPessoas);
    }

    private void abrirEmprestimos() {
        painelEmprestimos.atualizar();
        cartas.show(areaTelas, "emprestimos");
        destacarBotao(btnEmprestimos);
    }
}
