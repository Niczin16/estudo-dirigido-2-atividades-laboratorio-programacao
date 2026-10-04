package projeto.biblioteca.telas;

import java.awt.CardLayout;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import projeto.biblioteca.EBook;
import projeto.biblioteca.Emprestimo;
import projeto.biblioteca.Funcionario;
import projeto.biblioteca.Livro;
import projeto.biblioteca.LivroFisico;
import projeto.biblioteca.Membro;
import projeto.biblioteca.Pessoa;

/**
 * Janela única do sistema (JFrame desenhado na aba Design do NetBeans).
 *
 * À esquerda fica o menu; à direita, o painel "areaTelas" (CardLayout) com as
 * quatro telas: telaInicio, telaLivros, telaPessoas e telaEmprestimos.
 * Para ver/editar uma tela na aba Design, clique nela na janela Navigator.
 *
 * Os componentes de cada tela terminam com o nome da tela
 * (ex.: tabelaLivros, tabelaPessoas, btnCadastrarLivros).
 */
public class JanelaPrincipal extends javax.swing.JFrame {

    // Dados usados por todas as telas (gerenciadores, listas e contadores de ID)
    private final Dados dados;

    // Tela Livros: livro clicado na tabela (null quando nenhum está selecionado)
    private Livro livroSelecionado = null;

    // Tela Pessoas: pessoa clicada na tabela (null quando nenhuma está selecionada)
    private Pessoa pessoaSelecionada = null;

    // Tela Empréstimos: listas na mesma ordem dos JComboBox
    private ArrayList<Membro> membros = new ArrayList<>();
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();

    // Itens da lista de livros (ligado na aba Design: listaLivrosEmprestimos > propriedade "model")
    private DefaultListModel<String> modeloLista = new DefaultListModel<>();

    // Modelos das tabelas (as colunas foram criadas na aba Design, propriedade "model")
    private DefaultTableModel modeloTabelaLivros;
    private DefaultTableModel modeloTabelaPessoas;
    private DefaultTableModel modeloTabelaEmprestimos;

    public JanelaPrincipal() {
        this(new Dados());
    }

    public JanelaPrincipal(Dados dados) {
        this.dados = dados;
        initComponents();
        configurarComponentes();

        setSize(1280, 760);
        setLocationRelativeTo(null);             // abre no centro da tela
        setExtendedState(MAXIMIZED_BOTH);         // abre maximizada, usando a tela toda

        abrirInicio(); // tela que aparece ao abrir o programa
    }

    // Ajustes que a aba Design não consegue fazer sozinha
    private void configurarComponentes() {
        Estilo.aplicarCursorMao(btnInicio, btnLivros, btnPessoas, btnEmprestimos, btnSair);

        // Tela Livros
        modeloTabelaLivros = (DefaultTableModel) tabelaLivros.getModel();
        Estilo.estilizarTabela(tabelaLivros, rolagemTabelaLivros);
        Estilo.aplicarEfeitoHover(btnCadastrarLivros, btnSalvarLivros, btnRemoverLivros, btnLimparLivros, btnBuscarLivros);
        rolagemFormularioLivros.getVerticalScrollBar().setUnitIncrement(16); // rolagem mais rápida
        tabelaLivros.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherFormularioLivro(); // ao clicar em uma linha, o livro vai para o formulário
            }
        });

        // Tela Pessoas
        modeloTabelaPessoas = (DefaultTableModel) tabelaPessoas.getModel();
        Estilo.estilizarTabela(tabelaPessoas, rolagemTabelaPessoas);
        Estilo.aplicarEfeitoHover(btnCadastrarPessoas, btnSalvarPessoas, btnLimparPessoas);
        rolagemFormularioPessoas.getVerticalScrollBar().setUnitIncrement(16);
        tabelaPessoas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherFormularioPessoa(); // ao clicar em uma linha, a pessoa vai para o formulário
            }
        });

        // Tela Empréstimos
        modeloTabelaEmprestimos = (DefaultTableModel) tabelaEmprestimos.getModel();
        Estilo.estilizarTabela(tabelaEmprestimos, rolagemTabelaEmprestimos);
        Estilo.aplicarEfeitoHover(btnRealizarEmprestimos, btnFinalizarEmprestimos);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        parteDeCima = new javax.swing.JPanel();
        topo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblSlogan = new javax.swing.JLabel();
        painelBotoesMenu = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        btnLivros = new javax.swing.JButton();
        btnPessoas = new javax.swing.JButton();
        btnEmprestimos = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        areaTelas = new javax.swing.JPanel();
        telaInicio = new javax.swing.JPanel();
        painelCabecalhoInicio = new javax.swing.JPanel();
        lblCabecalhoTituloInicio = new javax.swing.JLabel();
        lblCabecalhoSubtituloInicio = new javax.swing.JLabel();
        painelTopoInicio = new javax.swing.JPanel();
        painelCentroInicio = new javax.swing.JPanel();
        painelCartoesInicio = new javax.swing.JPanel();
        cartaoLivrosInicio = new javax.swing.JPanel();
        lblTotalLivrosInicio = new javax.swing.JLabel();
        lblDescricaoLivrosInicio = new javax.swing.JLabel();
        cartaoPessoasInicio = new javax.swing.JPanel();
        lblTotalPessoasInicio = new javax.swing.JLabel();
        lblDescricaoPessoasInicio = new javax.swing.JLabel();
        cartaoEmprestimosInicio = new javax.swing.JPanel();
        lblTotalEmprestimosInicio = new javax.swing.JLabel();
        lblDescricaoEmprestimosInicio = new javax.swing.JLabel();
        cartaoDicasInicio = new javax.swing.JPanel();
        lblComoUsarInicio = new javax.swing.JLabel();
        painelPassosInicio = new javax.swing.JPanel();
        lblPasso1Inicio = new javax.swing.JLabel();
        lblPasso2Inicio = new javax.swing.JLabel();
        lblPasso3Inicio = new javax.swing.JLabel();
        telaLivros = new javax.swing.JPanel();
        painelCabecalhoLivros = new javax.swing.JPanel();
        lblCabecalhoTituloLivros = new javax.swing.JLabel();
        lblCabecalhoSubtituloLivros = new javax.swing.JLabel();
        rolagemFormularioLivros = new javax.swing.JScrollPane();
        cartaoFormularioLivros = new javax.swing.JPanel();
        painelConteudoLivros = new javax.swing.JPanel();
        painelCamposLivros = new javax.swing.JPanel();
        grupoTipoLivros = new javax.swing.JPanel();
        lblTipoLivros = new javax.swing.JLabel();
        comboTipoLivros = new javax.swing.JComboBox<>();
        grupoTituloLivros = new javax.swing.JPanel();
        lblTituloLivros = new javax.swing.JLabel();
        campoTituloLivros = new javax.swing.JTextField();
        grupoAutorLivros = new javax.swing.JPanel();
        lblAutorLivros = new javax.swing.JLabel();
        campoAutorLivros = new javax.swing.JTextField();
        grupoPaginasLivros = new javax.swing.JPanel();
        lblPaginasLivros = new javax.swing.JLabel();
        campoPaginasLivros = new javax.swing.JTextField();
        grupoExtraLivros = new javax.swing.JPanel();
        rotuloExtraLivros = new javax.swing.JLabel();
        campoExtraLivros = new javax.swing.JTextField();
        painelBotoesLivros = new javax.swing.JPanel();
        btnCadastrarLivros = new javax.swing.JButton();
        btnSalvarLivros = new javax.swing.JButton();
        btnRemoverLivros = new javax.swing.JButton();
        btnLimparLivros = new javax.swing.JButton();
        cartaoTabelaLivros = new javax.swing.JPanel();
        barraBuscaLivros = new javax.swing.JPanel();
        lblBuscarLivros = new javax.swing.JLabel();
        campoBuscaLivros = new javax.swing.JTextField();
        btnBuscarLivros = new javax.swing.JButton();
        rolagemTabelaLivros = new javax.swing.JScrollPane();
        tabelaLivros = new javax.swing.JTable();
        telaPessoas = new javax.swing.JPanel();
        painelCabecalhoPessoas = new javax.swing.JPanel();
        lblCabecalhoTituloPessoas = new javax.swing.JLabel();
        lblCabecalhoSubtituloPessoas = new javax.swing.JLabel();
        rolagemFormularioPessoas = new javax.swing.JScrollPane();
        cartaoFormularioPessoas = new javax.swing.JPanel();
        painelConteudoPessoas = new javax.swing.JPanel();
        painelCamposPessoas = new javax.swing.JPanel();
        grupoTipoPessoas = new javax.swing.JPanel();
        lblTipoPessoas = new javax.swing.JLabel();
        comboTipoPessoas = new javax.swing.JComboBox<>();
        grupoNomePessoas = new javax.swing.JPanel();
        lblNomePessoas = new javax.swing.JLabel();
        campoNomePessoas = new javax.swing.JTextField();
        grupoIdadePessoas = new javax.swing.JPanel();
        lblIdadePessoas = new javax.swing.JLabel();
        campoIdadePessoas = new javax.swing.JTextField();
        grupoCpfPessoas = new javax.swing.JPanel();
        lblCpfPessoas = new javax.swing.JLabel();
        campoCpfPessoas = new javax.swing.JTextField();
        grupoCepPessoas = new javax.swing.JPanel();
        lblCepPessoas = new javax.swing.JLabel();
        campoCepPessoas = new javax.swing.JTextField();
        grupoExtraPessoas = new javax.swing.JPanel();
        rotuloExtraPessoas = new javax.swing.JLabel();
        campoExtraPessoas = new javax.swing.JTextField();
        painelBotoesPessoas = new javax.swing.JPanel();
        btnCadastrarPessoas = new javax.swing.JButton();
        btnSalvarPessoas = new javax.swing.JButton();
        btnLimparPessoas = new javax.swing.JButton();
        cartaoTabelaPessoas = new javax.swing.JPanel();
        rolagemTabelaPessoas = new javax.swing.JScrollPane();
        tabelaPessoas = new javax.swing.JTable();
        telaEmprestimos = new javax.swing.JPanel();
        painelCabecalhoEmprestimos = new javax.swing.JPanel();
        lblCabecalhoTituloEmprestimos = new javax.swing.JLabel();
        lblCabecalhoSubtituloEmprestimos = new javax.swing.JLabel();
        cartaoFormularioEmprestimos = new javax.swing.JPanel();
        painelCamposEmprestimos = new javax.swing.JPanel();
        grupoDataEmprestimos = new javax.swing.JPanel();
        lblDataEmprestimos = new javax.swing.JLabel();
        campoDataEmprestimos = new javax.swing.JTextField();
        grupoMembroEmprestimos = new javax.swing.JPanel();
        lblMembroEmprestimos = new javax.swing.JLabel();
        comboMembroEmprestimos = new javax.swing.JComboBox<>();
        grupoFuncionarioEmprestimos = new javax.swing.JPanel();
        lblFuncionarioEmprestimos = new javax.swing.JLabel();
        comboFuncionarioEmprestimos = new javax.swing.JComboBox<>();
        grupoLivrosEmprestimos = new javax.swing.JPanel();
        lblLivrosEmprestimos = new javax.swing.JLabel();
        rolagemLivrosEmprestimos = new javax.swing.JScrollPane();
        listaLivrosEmprestimos = new javax.swing.JList<>();
        btnRealizarEmprestimos = new javax.swing.JButton();
        cartaoTabelaEmprestimos = new javax.swing.JPanel();
        lblEmprestimosAtivosEmprestimos = new javax.swing.JLabel();
        rolagemTabelaEmprestimos = new javax.swing.JScrollPane();
        tabelaEmprestimos = new javax.swing.JTable();
        painelRodapeEmprestimos = new javax.swing.JPanel();
        btnFinalizarEmprestimos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Projeto Biblioteca");
        setMinimumSize(new java.awt.Dimension(1000, 620));

        menu.setBackground(new java.awt.Color(30, 41, 59));
        menu.setBorder(javax.swing.BorderFactory.createEmptyBorder(28, 0, 20, 0));
        menu.setPreferredSize(new java.awt.Dimension(230, 0));
        menu.setLayout(new java.awt.BorderLayout());

        parteDeCima.setOpaque(false);
        parteDeCima.setLayout(new java.awt.BorderLayout());

        topo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 24, 28, 0));
        topo.setOpaque(false);
        topo.setLayout(new java.awt.GridLayout(2, 1, 0, 0));

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("Biblioteca");
        topo.add(lblLogo);

        lblSlogan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSlogan.setForeground(new java.awt.Color(203, 213, 225));
        lblSlogan.setText("Sistema de gestão");
        topo.add(lblSlogan);

        parteDeCima.add(topo, java.awt.BorderLayout.NORTH);

        painelBotoesMenu.setOpaque(false);
        painelBotoesMenu.setLayout(new java.awt.GridLayout(4, 1, 0, 6));

        btnInicio.setBackground(new java.awt.Color(30, 41, 59));
        btnInicio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(203, 213, 225));
        btnInicio.setText("Início");
        btnInicio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 28, 0, 0));
        btnInicio.setFocusPainted(false);
        btnInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInicio.setOpaque(true);
        btnInicio.setPreferredSize(new java.awt.Dimension(230, 46));
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        painelBotoesMenu.add(btnInicio);

        btnLivros.setBackground(new java.awt.Color(30, 41, 59));
        btnLivros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLivros.setForeground(new java.awt.Color(203, 213, 225));
        btnLivros.setText("Livros");
        btnLivros.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 28, 0, 0));
        btnLivros.setFocusPainted(false);
        btnLivros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLivros.setOpaque(true);
        btnLivros.setPreferredSize(new java.awt.Dimension(230, 46));
        btnLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLivrosActionPerformed(evt);
            }
        });
        painelBotoesMenu.add(btnLivros);

        btnPessoas.setBackground(new java.awt.Color(30, 41, 59));
        btnPessoas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPessoas.setForeground(new java.awt.Color(203, 213, 225));
        btnPessoas.setText("Pessoas");
        btnPessoas.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 28, 0, 0));
        btnPessoas.setFocusPainted(false);
        btnPessoas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPessoas.setOpaque(true);
        btnPessoas.setPreferredSize(new java.awt.Dimension(230, 46));
        btnPessoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPessoasActionPerformed(evt);
            }
        });
        painelBotoesMenu.add(btnPessoas);

        btnEmprestimos.setBackground(new java.awt.Color(30, 41, 59));
        btnEmprestimos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEmprestimos.setForeground(new java.awt.Color(203, 213, 225));
        btnEmprestimos.setText("Empréstimos");
        btnEmprestimos.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 28, 0, 0));
        btnEmprestimos.setFocusPainted(false);
        btnEmprestimos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEmprestimos.setOpaque(true);
        btnEmprestimos.setPreferredSize(new java.awt.Dimension(230, 46));
        btnEmprestimos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmprestimosActionPerformed(evt);
            }
        });
        painelBotoesMenu.add(btnEmprestimos);

        parteDeCima.add(painelBotoesMenu, java.awt.BorderLayout.CENTER);

        menu.add(parteDeCima, java.awt.BorderLayout.NORTH);

        btnSair.setBackground(new java.awt.Color(30, 41, 59));
        btnSair.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSair.setForeground(new java.awt.Color(203, 213, 225));
        btnSair.setText("Sair");
        btnSair.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 28, 0, 0));
        btnSair.setFocusPainted(false);
        btnSair.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSair.setOpaque(true);
        btnSair.setPreferredSize(new java.awt.Dimension(230, 46));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        menu.add(btnSair, java.awt.BorderLayout.SOUTH);

        getContentPane().add(menu, java.awt.BorderLayout.WEST);

        areaTelas.setLayout(new java.awt.CardLayout());

        telaInicio.setBackground(new java.awt.Color(241, 245, 249));
        telaInicio.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        telaInicio.setLayout(new java.awt.BorderLayout(20, 24));

        painelCabecalhoInicio.setOpaque(false);
        painelCabecalhoInicio.setLayout(new java.awt.GridLayout(2, 1, 0, 0));

        lblCabecalhoTituloInicio.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblCabecalhoTituloInicio.setForeground(new java.awt.Color(15, 23, 42));
        lblCabecalhoTituloInicio.setText("Bem-vindo à Biblioteca");
        painelCabecalhoInicio.add(lblCabecalhoTituloInicio);

        lblCabecalhoSubtituloInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCabecalhoSubtituloInicio.setForeground(new java.awt.Color(100, 116, 139));
        lblCabecalhoSubtituloInicio.setText("Escolha uma opção no menu ao lado para começar.");
        painelCabecalhoInicio.add(lblCabecalhoSubtituloInicio);

        telaInicio.add(painelCabecalhoInicio, java.awt.BorderLayout.NORTH);

        painelTopoInicio.setOpaque(false);
        painelTopoInicio.setLayout(new java.awt.BorderLayout());

        painelCentroInicio.setOpaque(false);
        painelCentroInicio.setLayout(new java.awt.BorderLayout(0, 24));

        painelCartoesInicio.setOpaque(false);
        painelCartoesInicio.setLayout(new java.awt.GridLayout(1, 3, 20, 0));

        cartaoLivrosInicio.setBackground(new java.awt.Color(37, 99, 235));
        cartaoLivrosInicio.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 24, 22, 24));
        cartaoLivrosInicio.setLayout(new java.awt.GridLayout(2, 1, 0, 0));

        lblTotalLivrosInicio.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        lblTotalLivrosInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalLivrosInicio.setText("0");
        cartaoLivrosInicio.add(lblTotalLivrosInicio);

        lblDescricaoLivrosInicio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDescricaoLivrosInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblDescricaoLivrosInicio.setText("Livros cadastrados");
        cartaoLivrosInicio.add(lblDescricaoLivrosInicio);

        painelCartoesInicio.add(cartaoLivrosInicio);

        cartaoPessoasInicio.setBackground(new java.awt.Color(22, 163, 74));
        cartaoPessoasInicio.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 24, 22, 24));
        cartaoPessoasInicio.setLayout(new java.awt.GridLayout(2, 1, 0, 0));

        lblTotalPessoasInicio.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        lblTotalPessoasInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalPessoasInicio.setText("0");
        cartaoPessoasInicio.add(lblTotalPessoasInicio);

        lblDescricaoPessoasInicio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDescricaoPessoasInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblDescricaoPessoasInicio.setText("Pessoas cadastradas");
        cartaoPessoasInicio.add(lblDescricaoPessoasInicio);

        painelCartoesInicio.add(cartaoPessoasInicio);

        cartaoEmprestimosInicio.setBackground(new java.awt.Color(245, 158, 11));
        cartaoEmprestimosInicio.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 24, 22, 24));
        cartaoEmprestimosInicio.setLayout(new java.awt.GridLayout(2, 1, 0, 0));

        lblTotalEmprestimosInicio.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        lblTotalEmprestimosInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalEmprestimosInicio.setText("0");
        cartaoEmprestimosInicio.add(lblTotalEmprestimosInicio);

        lblDescricaoEmprestimosInicio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDescricaoEmprestimosInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblDescricaoEmprestimosInicio.setText("Empréstimos ativos");
        cartaoEmprestimosInicio.add(lblDescricaoEmprestimosInicio);

        painelCartoesInicio.add(cartaoEmprestimosInicio);

        painelCentroInicio.add(painelCartoesInicio, java.awt.BorderLayout.NORTH);

        cartaoDicasInicio.setBackground(new java.awt.Color(255, 255, 255));
        cartaoDicasInicio.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(18, 18, 18, 18)));
        cartaoDicasInicio.setLayout(new java.awt.BorderLayout(10, 10));

        lblComoUsarInicio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblComoUsarInicio.setForeground(new java.awt.Color(15, 23, 42));
        lblComoUsarInicio.setText("Como usar");
        cartaoDicasInicio.add(lblComoUsarInicio, java.awt.BorderLayout.NORTH);

        painelPassosInicio.setOpaque(false);
        painelPassosInicio.setLayout(new java.awt.GridLayout(3, 1, 0, 8));

        lblPasso1Inicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPasso1Inicio.setForeground(new java.awt.Color(100, 116, 139));
        lblPasso1Inicio.setText("1.  Em Livros, cadastre E-Books e livros físicos.");
        painelPassosInicio.add(lblPasso1Inicio);

        lblPasso2Inicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPasso2Inicio.setForeground(new java.awt.Color(100, 116, 139));
        lblPasso2Inicio.setText("2.  Em Pessoas, cadastre membros e funcionários.");
        painelPassosInicio.add(lblPasso2Inicio);

        lblPasso3Inicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPasso3Inicio.setForeground(new java.awt.Color(100, 116, 139));
        lblPasso3Inicio.setText("3.  Em Empréstimos, escolha o membro, o funcionário e os livros.");
        painelPassosInicio.add(lblPasso3Inicio);

        cartaoDicasInicio.add(painelPassosInicio, java.awt.BorderLayout.CENTER);

        painelCentroInicio.add(cartaoDicasInicio, java.awt.BorderLayout.CENTER);

        painelTopoInicio.add(painelCentroInicio, java.awt.BorderLayout.NORTH);

        telaInicio.add(painelTopoInicio, java.awt.BorderLayout.CENTER);

        areaTelas.add(telaInicio, "inicio");

        telaLivros.setBackground(new java.awt.Color(241, 245, 249));
        telaLivros.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        telaLivros.setLayout(new java.awt.BorderLayout(20, 20));

        painelCabecalhoLivros.setOpaque(false);
        painelCabecalhoLivros.setLayout(new java.awt.GridLayout(2, 1, 0, 0));

        lblCabecalhoTituloLivros.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblCabecalhoTituloLivros.setForeground(new java.awt.Color(15, 23, 42));
        lblCabecalhoTituloLivros.setText("Livros");
        painelCabecalhoLivros.add(lblCabecalhoTituloLivros);

        lblCabecalhoSubtituloLivros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCabecalhoSubtituloLivros.setForeground(new java.awt.Color(100, 116, 139));
        lblCabecalhoSubtituloLivros.setText("Cadastre, edite e remova E-Books e livros físicos.");
        painelCabecalhoLivros.add(lblCabecalhoSubtituloLivros);

        telaLivros.add(painelCabecalhoLivros, java.awt.BorderLayout.NORTH);

        rolagemFormularioLivros.setBorder(null);
        rolagemFormularioLivros.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        rolagemFormularioLivros.setPreferredSize(new java.awt.Dimension(300, 0));

        cartaoFormularioLivros.setBackground(new java.awt.Color(255, 255, 255));
        cartaoFormularioLivros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(18, 18, 18, 18)));
        cartaoFormularioLivros.setLayout(new java.awt.BorderLayout(10, 10));

        painelConteudoLivros.setOpaque(false);
        painelConteudoLivros.setLayout(new java.awt.BorderLayout(0, 16));

        painelCamposLivros.setOpaque(false);
        painelCamposLivros.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

        grupoTipoLivros.setOpaque(false);
        grupoTipoLivros.setLayout(new java.awt.BorderLayout(0, 4));

        lblTipoLivros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTipoLivros.setForeground(new java.awt.Color(100, 116, 139));
        lblTipoLivros.setText("Tipo");
        grupoTipoLivros.add(lblTipoLivros, java.awt.BorderLayout.NORTH);

        comboTipoLivros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboTipoLivros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "E-Book", "Livro Físico" }));
        comboTipoLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoLivrosActionPerformed(evt);
            }
        });
        grupoTipoLivros.add(comboTipoLivros, java.awt.BorderLayout.CENTER);

        painelCamposLivros.add(grupoTipoLivros);

        grupoTituloLivros.setOpaque(false);
        grupoTituloLivros.setLayout(new java.awt.BorderLayout(0, 4));

        lblTituloLivros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTituloLivros.setForeground(new java.awt.Color(100, 116, 139));
        lblTituloLivros.setText("Título");
        grupoTituloLivros.add(lblTituloLivros, java.awt.BorderLayout.NORTH);

        campoTituloLivros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoTituloLivros.setForeground(new java.awt.Color(15, 23, 42));
        campoTituloLivros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        grupoTituloLivros.add(campoTituloLivros, java.awt.BorderLayout.CENTER);

        painelCamposLivros.add(grupoTituloLivros);

        grupoAutorLivros.setOpaque(false);
        grupoAutorLivros.setLayout(new java.awt.BorderLayout(0, 4));

        lblAutorLivros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAutorLivros.setForeground(new java.awt.Color(100, 116, 139));
        lblAutorLivros.setText("Autor");
        grupoAutorLivros.add(lblAutorLivros, java.awt.BorderLayout.NORTH);

        campoAutorLivros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoAutorLivros.setForeground(new java.awt.Color(15, 23, 42));
        campoAutorLivros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        grupoAutorLivros.add(campoAutorLivros, java.awt.BorderLayout.CENTER);

        painelCamposLivros.add(grupoAutorLivros);

        grupoPaginasLivros.setOpaque(false);
        grupoPaginasLivros.setLayout(new java.awt.BorderLayout(0, 4));

        lblPaginasLivros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPaginasLivros.setForeground(new java.awt.Color(100, 116, 139));
        lblPaginasLivros.setText("Número de páginas");
        grupoPaginasLivros.add(lblPaginasLivros, java.awt.BorderLayout.NORTH);

        campoPaginasLivros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoPaginasLivros.setForeground(new java.awt.Color(15, 23, 42));
        campoPaginasLivros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        grupoPaginasLivros.add(campoPaginasLivros, java.awt.BorderLayout.CENTER);

        painelCamposLivros.add(grupoPaginasLivros);

        grupoExtraLivros.setOpaque(false);
        grupoExtraLivros.setLayout(new java.awt.BorderLayout(0, 4));

        rotuloExtraLivros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rotuloExtraLivros.setForeground(new java.awt.Color(100, 116, 139));
        rotuloExtraLivros.setText("Tamanho do arquivo (MB)");
        grupoExtraLivros.add(rotuloExtraLivros, java.awt.BorderLayout.NORTH);

        campoExtraLivros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoExtraLivros.setForeground(new java.awt.Color(15, 23, 42));
        campoExtraLivros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        grupoExtraLivros.add(campoExtraLivros, java.awt.BorderLayout.CENTER);

        painelCamposLivros.add(grupoExtraLivros);

        painelConteudoLivros.add(painelCamposLivros, java.awt.BorderLayout.NORTH);

        painelBotoesLivros.setOpaque(false);
        painelBotoesLivros.setLayout(new java.awt.GridLayout(0, 1, 0, 8));

        btnCadastrarLivros.setBackground(new java.awt.Color(37, 99, 235));
        btnCadastrarLivros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCadastrarLivros.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrarLivros.setText("Cadastrar");
        btnCadastrarLivros.setBorderPainted(false);
        btnCadastrarLivros.setFocusPainted(false);
        btnCadastrarLivros.setOpaque(true);
        btnCadastrarLivros.setPreferredSize(new java.awt.Dimension(160, 36));
        btnCadastrarLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarLivrosActionPerformed(evt);
            }
        });
        painelBotoesLivros.add(btnCadastrarLivros);

        btnSalvarLivros.setBackground(new java.awt.Color(22, 163, 74));
        btnSalvarLivros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalvarLivros.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarLivros.setText("Salvar alterações");
        btnSalvarLivros.setBorderPainted(false);
        btnSalvarLivros.setFocusPainted(false);
        btnSalvarLivros.setOpaque(true);
        btnSalvarLivros.setPreferredSize(new java.awt.Dimension(160, 36));
        btnSalvarLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarLivrosActionPerformed(evt);
            }
        });
        painelBotoesLivros.add(btnSalvarLivros);

        btnRemoverLivros.setBackground(new java.awt.Color(220, 38, 38));
        btnRemoverLivros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRemoverLivros.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoverLivros.setText("Remover selecionado");
        btnRemoverLivros.setBorderPainted(false);
        btnRemoverLivros.setFocusPainted(false);
        btnRemoverLivros.setOpaque(true);
        btnRemoverLivros.setPreferredSize(new java.awt.Dimension(160, 36));
        btnRemoverLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverLivrosActionPerformed(evt);
            }
        });
        painelBotoesLivros.add(btnRemoverLivros);

        btnLimparLivros.setBackground(new java.awt.Color(100, 116, 139));
        btnLimparLivros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimparLivros.setForeground(new java.awt.Color(255, 255, 255));
        btnLimparLivros.setText("Limpar");
        btnLimparLivros.setBorderPainted(false);
        btnLimparLivros.setFocusPainted(false);
        btnLimparLivros.setOpaque(true);
        btnLimparLivros.setPreferredSize(new java.awt.Dimension(160, 36));
        btnLimparLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparLivrosActionPerformed(evt);
            }
        });
        painelBotoesLivros.add(btnLimparLivros);

        painelConteudoLivros.add(painelBotoesLivros, java.awt.BorderLayout.CENTER);

        cartaoFormularioLivros.add(painelConteudoLivros, java.awt.BorderLayout.NORTH);

        rolagemFormularioLivros.setViewportView(cartaoFormularioLivros);

        telaLivros.add(rolagemFormularioLivros, java.awt.BorderLayout.WEST);

        cartaoTabelaLivros.setBackground(new java.awt.Color(255, 255, 255));
        cartaoTabelaLivros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(18, 18, 18, 18)));
        cartaoTabelaLivros.setLayout(new java.awt.BorderLayout(10, 10));

        barraBuscaLivros.setOpaque(false);
        barraBuscaLivros.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));

        lblBuscarLivros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBuscarLivros.setForeground(new java.awt.Color(100, 116, 139));
        lblBuscarLivros.setText("Buscar por ID:");
        barraBuscaLivros.add(lblBuscarLivros);

        campoBuscaLivros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoBuscaLivros.setForeground(new java.awt.Color(15, 23, 42));
        campoBuscaLivros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        campoBuscaLivros.setPreferredSize(new java.awt.Dimension(90, 36));
        barraBuscaLivros.add(campoBuscaLivros);

        btnBuscarLivros.setBackground(new java.awt.Color(37, 99, 235));
        btnBuscarLivros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscarLivros.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarLivros.setText("Buscar");
        btnBuscarLivros.setBorderPainted(false);
        btnBuscarLivros.setFocusPainted(false);
        btnBuscarLivros.setOpaque(true);
        btnBuscarLivros.setPreferredSize(new java.awt.Dimension(100, 36));
        btnBuscarLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarLivrosActionPerformed(evt);
            }
        });
        barraBuscaLivros.add(btnBuscarLivros);

        cartaoTabelaLivros.add(barraBuscaLivros, java.awt.BorderLayout.NORTH);

        rolagemTabelaLivros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));

        tabelaLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tipo", "Título", "Autor", "Páginas", "Tamanho / Peso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaLivros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabelaLivros.setForeground(new java.awt.Color(15, 23, 42));
        tabelaLivros.setGridColor(new java.awt.Color(226, 232, 240));
        tabelaLivros.setRowHeight(30);
        tabelaLivros.setSelectionBackground(new java.awt.Color(219, 234, 254));
        tabelaLivros.setSelectionForeground(new java.awt.Color(15, 23, 42));
        tabelaLivros.getTableHeader().setReorderingAllowed(false);
        rolagemTabelaLivros.setViewportView(tabelaLivros);
        if (tabelaLivros.getColumnModel().getColumnCount() > 0) {
            tabelaLivros.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        cartaoTabelaLivros.add(rolagemTabelaLivros, java.awt.BorderLayout.CENTER);

        telaLivros.add(cartaoTabelaLivros, java.awt.BorderLayout.CENTER);

        areaTelas.add(telaLivros, "livros");

        telaPessoas.setBackground(new java.awt.Color(241, 245, 249));
        telaPessoas.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        telaPessoas.setLayout(new java.awt.BorderLayout(20, 20));

        painelCabecalhoPessoas.setOpaque(false);
        painelCabecalhoPessoas.setLayout(new java.awt.GridLayout(2, 1, 0, 0));

        lblCabecalhoTituloPessoas.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblCabecalhoTituloPessoas.setForeground(new java.awt.Color(15, 23, 42));
        lblCabecalhoTituloPessoas.setText("Pessoas");
        painelCabecalhoPessoas.add(lblCabecalhoTituloPessoas);

        lblCabecalhoSubtituloPessoas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCabecalhoSubtituloPessoas.setForeground(new java.awt.Color(100, 116, 139));
        lblCabecalhoSubtituloPessoas.setText("Cadastre e edite membros e funcionários da biblioteca.");
        painelCabecalhoPessoas.add(lblCabecalhoSubtituloPessoas);

        telaPessoas.add(painelCabecalhoPessoas, java.awt.BorderLayout.NORTH);

        rolagemFormularioPessoas.setBorder(null);
        rolagemFormularioPessoas.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        rolagemFormularioPessoas.setPreferredSize(new java.awt.Dimension(300, 0));

        cartaoFormularioPessoas.setBackground(new java.awt.Color(255, 255, 255));
        cartaoFormularioPessoas.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(18, 18, 18, 18)));
        cartaoFormularioPessoas.setLayout(new java.awt.BorderLayout(10, 10));

        painelConteudoPessoas.setOpaque(false);
        painelConteudoPessoas.setLayout(new java.awt.BorderLayout(0, 16));

        painelCamposPessoas.setOpaque(false);
        painelCamposPessoas.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

        grupoTipoPessoas.setOpaque(false);
        grupoTipoPessoas.setLayout(new java.awt.BorderLayout(0, 4));

        lblTipoPessoas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTipoPessoas.setForeground(new java.awt.Color(100, 116, 139));
        lblTipoPessoas.setText("Tipo");
        grupoTipoPessoas.add(lblTipoPessoas, java.awt.BorderLayout.NORTH);

        comboTipoPessoas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboTipoPessoas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Membro", "Funcionário" }));
        comboTipoPessoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoPessoasActionPerformed(evt);
            }
        });
        grupoTipoPessoas.add(comboTipoPessoas, java.awt.BorderLayout.CENTER);

        painelCamposPessoas.add(grupoTipoPessoas);

        grupoNomePessoas.setOpaque(false);
        grupoNomePessoas.setLayout(new java.awt.BorderLayout(0, 4));

        lblNomePessoas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNomePessoas.setForeground(new java.awt.Color(100, 116, 139));
        lblNomePessoas.setText("Nome");
        grupoNomePessoas.add(lblNomePessoas, java.awt.BorderLayout.NORTH);

        campoNomePessoas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoNomePessoas.setForeground(new java.awt.Color(15, 23, 42));
        campoNomePessoas.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        grupoNomePessoas.add(campoNomePessoas, java.awt.BorderLayout.CENTER);

        painelCamposPessoas.add(grupoNomePessoas);

        grupoIdadePessoas.setOpaque(false);
        grupoIdadePessoas.setLayout(new java.awt.BorderLayout(0, 4));

        lblIdadePessoas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblIdadePessoas.setForeground(new java.awt.Color(100, 116, 139));
        lblIdadePessoas.setText("Idade");
        grupoIdadePessoas.add(lblIdadePessoas, java.awt.BorderLayout.NORTH);

        campoIdadePessoas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoIdadePessoas.setForeground(new java.awt.Color(15, 23, 42));
        campoIdadePessoas.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        grupoIdadePessoas.add(campoIdadePessoas, java.awt.BorderLayout.CENTER);

        painelCamposPessoas.add(grupoIdadePessoas);

        grupoCpfPessoas.setOpaque(false);
        grupoCpfPessoas.setLayout(new java.awt.BorderLayout(0, 4));

        lblCpfPessoas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCpfPessoas.setForeground(new java.awt.Color(100, 116, 139));
        lblCpfPessoas.setText("CPF");
        grupoCpfPessoas.add(lblCpfPessoas, java.awt.BorderLayout.NORTH);

        campoCpfPessoas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoCpfPessoas.setForeground(new java.awt.Color(15, 23, 42));
        campoCpfPessoas.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        grupoCpfPessoas.add(campoCpfPessoas, java.awt.BorderLayout.CENTER);

        painelCamposPessoas.add(grupoCpfPessoas);

        grupoCepPessoas.setOpaque(false);
        grupoCepPessoas.setLayout(new java.awt.BorderLayout(0, 4));

        lblCepPessoas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCepPessoas.setForeground(new java.awt.Color(100, 116, 139));
        lblCepPessoas.setText("CEP");
        grupoCepPessoas.add(lblCepPessoas, java.awt.BorderLayout.NORTH);

        campoCepPessoas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoCepPessoas.setForeground(new java.awt.Color(15, 23, 42));
        campoCepPessoas.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        grupoCepPessoas.add(campoCepPessoas, java.awt.BorderLayout.CENTER);

        painelCamposPessoas.add(grupoCepPessoas);

        grupoExtraPessoas.setOpaque(false);
        grupoExtraPessoas.setLayout(new java.awt.BorderLayout(0, 4));

        rotuloExtraPessoas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rotuloExtraPessoas.setForeground(new java.awt.Color(100, 116, 139));
        rotuloExtraPessoas.setText("Preferência");
        grupoExtraPessoas.add(rotuloExtraPessoas, java.awt.BorderLayout.NORTH);

        campoExtraPessoas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoExtraPessoas.setForeground(new java.awt.Color(15, 23, 42));
        campoExtraPessoas.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        grupoExtraPessoas.add(campoExtraPessoas, java.awt.BorderLayout.CENTER);

        painelCamposPessoas.add(grupoExtraPessoas);

        painelConteudoPessoas.add(painelCamposPessoas, java.awt.BorderLayout.NORTH);

        painelBotoesPessoas.setOpaque(false);
        painelBotoesPessoas.setLayout(new java.awt.GridLayout(0, 1, 0, 8));

        btnCadastrarPessoas.setBackground(new java.awt.Color(37, 99, 235));
        btnCadastrarPessoas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCadastrarPessoas.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrarPessoas.setText("Cadastrar");
        btnCadastrarPessoas.setBorderPainted(false);
        btnCadastrarPessoas.setFocusPainted(false);
        btnCadastrarPessoas.setOpaque(true);
        btnCadastrarPessoas.setPreferredSize(new java.awt.Dimension(160, 36));
        btnCadastrarPessoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarPessoasActionPerformed(evt);
            }
        });
        painelBotoesPessoas.add(btnCadastrarPessoas);

        btnSalvarPessoas.setBackground(new java.awt.Color(22, 163, 74));
        btnSalvarPessoas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalvarPessoas.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarPessoas.setText("Salvar alterações");
        btnSalvarPessoas.setBorderPainted(false);
        btnSalvarPessoas.setFocusPainted(false);
        btnSalvarPessoas.setOpaque(true);
        btnSalvarPessoas.setPreferredSize(new java.awt.Dimension(160, 36));
        btnSalvarPessoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPessoasActionPerformed(evt);
            }
        });
        painelBotoesPessoas.add(btnSalvarPessoas);

        btnLimparPessoas.setBackground(new java.awt.Color(100, 116, 139));
        btnLimparPessoas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimparPessoas.setForeground(new java.awt.Color(255, 255, 255));
        btnLimparPessoas.setText("Limpar");
        btnLimparPessoas.setBorderPainted(false);
        btnLimparPessoas.setFocusPainted(false);
        btnLimparPessoas.setOpaque(true);
        btnLimparPessoas.setPreferredSize(new java.awt.Dimension(160, 36));
        btnLimparPessoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparPessoasActionPerformed(evt);
            }
        });
        painelBotoesPessoas.add(btnLimparPessoas);

        painelConteudoPessoas.add(painelBotoesPessoas, java.awt.BorderLayout.CENTER);

        cartaoFormularioPessoas.add(painelConteudoPessoas, java.awt.BorderLayout.NORTH);

        rolagemFormularioPessoas.setViewportView(cartaoFormularioPessoas);

        telaPessoas.add(rolagemFormularioPessoas, java.awt.BorderLayout.WEST);

        cartaoTabelaPessoas.setBackground(new java.awt.Color(255, 255, 255));
        cartaoTabelaPessoas.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(18, 18, 18, 18)));
        cartaoTabelaPessoas.setLayout(new java.awt.BorderLayout(10, 10));

        rolagemTabelaPessoas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));

        tabelaPessoas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tipo", "Nome", "Idade", "CPF", "CEP", "Pref. / Registro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaPessoas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabelaPessoas.setForeground(new java.awt.Color(15, 23, 42));
        tabelaPessoas.setGridColor(new java.awt.Color(226, 232, 240));
        tabelaPessoas.setRowHeight(30);
        tabelaPessoas.setSelectionBackground(new java.awt.Color(219, 234, 254));
        tabelaPessoas.setSelectionForeground(new java.awt.Color(15, 23, 42));
        tabelaPessoas.getTableHeader().setReorderingAllowed(false);
        rolagemTabelaPessoas.setViewportView(tabelaPessoas);
        if (tabelaPessoas.getColumnModel().getColumnCount() > 0) {
            tabelaPessoas.getColumnModel().getColumn(0).setMaxWidth(50);
            tabelaPessoas.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        cartaoTabelaPessoas.add(rolagemTabelaPessoas, java.awt.BorderLayout.CENTER);

        telaPessoas.add(cartaoTabelaPessoas, java.awt.BorderLayout.CENTER);

        areaTelas.add(telaPessoas, "pessoas");

        telaEmprestimos.setBackground(new java.awt.Color(241, 245, 249));
        telaEmprestimos.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        telaEmprestimos.setLayout(new java.awt.BorderLayout(20, 20));

        painelCabecalhoEmprestimos.setOpaque(false);
        painelCabecalhoEmprestimos.setLayout(new java.awt.GridLayout(2, 1, 0, 0));

        lblCabecalhoTituloEmprestimos.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblCabecalhoTituloEmprestimos.setForeground(new java.awt.Color(15, 23, 42));
        lblCabecalhoTituloEmprestimos.setText("Empréstimos");
        painelCabecalhoEmprestimos.add(lblCabecalhoTituloEmprestimos);

        lblCabecalhoSubtituloEmprestimos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCabecalhoSubtituloEmprestimos.setForeground(new java.awt.Color(100, 116, 139));
        lblCabecalhoSubtituloEmprestimos.setText("Registre quem pegou quais livros e finalize na devolução.");
        painelCabecalhoEmprestimos.add(lblCabecalhoSubtituloEmprestimos);

        telaEmprestimos.add(painelCabecalhoEmprestimos, java.awt.BorderLayout.NORTH);

        cartaoFormularioEmprestimos.setBackground(new java.awt.Color(255, 255, 255));
        cartaoFormularioEmprestimos.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(18, 18, 18, 18)));
        cartaoFormularioEmprestimos.setPreferredSize(new java.awt.Dimension(320, 0));
        cartaoFormularioEmprestimos.setLayout(new java.awt.BorderLayout(10, 10));

        painelCamposEmprestimos.setOpaque(false);
        painelCamposEmprestimos.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

        grupoDataEmprestimos.setOpaque(false);
        grupoDataEmprestimos.setLayout(new java.awt.BorderLayout(0, 4));

        lblDataEmprestimos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDataEmprestimos.setForeground(new java.awt.Color(100, 116, 139));
        lblDataEmprestimos.setText("Data do empréstimo");
        grupoDataEmprestimos.add(lblDataEmprestimos, java.awt.BorderLayout.NORTH);

        campoDataEmprestimos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoDataEmprestimos.setForeground(new java.awt.Color(15, 23, 42));
        campoDataEmprestimos.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        grupoDataEmprestimos.add(campoDataEmprestimos, java.awt.BorderLayout.CENTER);

        painelCamposEmprestimos.add(grupoDataEmprestimos);

        grupoMembroEmprestimos.setOpaque(false);
        grupoMembroEmprestimos.setLayout(new java.awt.BorderLayout(0, 4));

        lblMembroEmprestimos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMembroEmprestimos.setForeground(new java.awt.Color(100, 116, 139));
        lblMembroEmprestimos.setText("Membro (quem pega o livro)");
        grupoMembroEmprestimos.add(lblMembroEmprestimos, java.awt.BorderLayout.NORTH);

        comboMembroEmprestimos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        grupoMembroEmprestimos.add(comboMembroEmprestimos, java.awt.BorderLayout.CENTER);

        painelCamposEmprestimos.add(grupoMembroEmprestimos);

        grupoFuncionarioEmprestimos.setOpaque(false);
        grupoFuncionarioEmprestimos.setLayout(new java.awt.BorderLayout(0, 4));

        lblFuncionarioEmprestimos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFuncionarioEmprestimos.setForeground(new java.awt.Color(100, 116, 139));
        lblFuncionarioEmprestimos.setText("Funcionário (quem atende)");
        grupoFuncionarioEmprestimos.add(lblFuncionarioEmprestimos, java.awt.BorderLayout.NORTH);

        comboFuncionarioEmprestimos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        grupoFuncionarioEmprestimos.add(comboFuncionarioEmprestimos, java.awt.BorderLayout.CENTER);

        painelCamposEmprestimos.add(grupoFuncionarioEmprestimos);

        cartaoFormularioEmprestimos.add(painelCamposEmprestimos, java.awt.BorderLayout.NORTH);

        grupoLivrosEmprestimos.setOpaque(false);
        grupoLivrosEmprestimos.setLayout(new java.awt.BorderLayout(0, 4));

        lblLivrosEmprestimos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLivrosEmprestimos.setForeground(new java.awt.Color(100, 116, 139));
        lblLivrosEmprestimos.setText("Livros (Ctrl + clique para vários)");
        grupoLivrosEmprestimos.add(lblLivrosEmprestimos, java.awt.BorderLayout.NORTH);

        rolagemLivrosEmprestimos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));

        listaLivrosEmprestimos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        listaLivrosEmprestimos.setForeground(new java.awt.Color(15, 23, 42));
        listaLivrosEmprestimos.setFixedCellHeight(26);
        listaLivrosEmprestimos.setModel(modeloLista);
        listaLivrosEmprestimos.setSelectionBackground(new java.awt.Color(219, 234, 254));
        listaLivrosEmprestimos.setSelectionForeground(new java.awt.Color(15, 23, 42));
        rolagemLivrosEmprestimos.setViewportView(listaLivrosEmprestimos);

        grupoLivrosEmprestimos.add(rolagemLivrosEmprestimos, java.awt.BorderLayout.CENTER);

        cartaoFormularioEmprestimos.add(grupoLivrosEmprestimos, java.awt.BorderLayout.CENTER);

        btnRealizarEmprestimos.setBackground(new java.awt.Color(37, 99, 235));
        btnRealizarEmprestimos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRealizarEmprestimos.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizarEmprestimos.setText("Realizar empréstimo");
        btnRealizarEmprestimos.setBorderPainted(false);
        btnRealizarEmprestimos.setFocusPainted(false);
        btnRealizarEmprestimos.setOpaque(true);
        btnRealizarEmprestimos.setPreferredSize(new java.awt.Dimension(160, 36));
        btnRealizarEmprestimos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarEmprestimosActionPerformed(evt);
            }
        });
        cartaoFormularioEmprestimos.add(btnRealizarEmprestimos, java.awt.BorderLayout.SOUTH);

        telaEmprestimos.add(cartaoFormularioEmprestimos, java.awt.BorderLayout.WEST);

        cartaoTabelaEmprestimos.setBackground(new java.awt.Color(255, 255, 255));
        cartaoTabelaEmprestimos.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(18, 18, 18, 18)));
        cartaoTabelaEmprestimos.setLayout(new java.awt.BorderLayout(10, 10));

        lblEmprestimosAtivosEmprestimos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEmprestimosAtivosEmprestimos.setForeground(new java.awt.Color(100, 116, 139));
        lblEmprestimosAtivosEmprestimos.setText("EMPRÉSTIMOS ATIVOS");
        cartaoTabelaEmprestimos.add(lblEmprestimosAtivosEmprestimos, java.awt.BorderLayout.NORTH);

        rolagemTabelaEmprestimos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));

        tabelaEmprestimos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data", "Membro", "Funcionário", "Livros"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaEmprestimos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabelaEmprestimos.setForeground(new java.awt.Color(15, 23, 42));
        tabelaEmprestimos.setGridColor(new java.awt.Color(226, 232, 240));
        tabelaEmprestimos.setRowHeight(30);
        tabelaEmprestimos.setSelectionBackground(new java.awt.Color(219, 234, 254));
        tabelaEmprestimos.setSelectionForeground(new java.awt.Color(15, 23, 42));
        tabelaEmprestimos.getTableHeader().setReorderingAllowed(false);
        rolagemTabelaEmprestimos.setViewportView(tabelaEmprestimos);
        if (tabelaEmprestimos.getColumnModel().getColumnCount() > 0) {
            tabelaEmprestimos.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        cartaoTabelaEmprestimos.add(rolagemTabelaEmprestimos, java.awt.BorderLayout.CENTER);

        painelRodapeEmprestimos.setOpaque(false);
        painelRodapeEmprestimos.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        btnFinalizarEmprestimos.setBackground(new java.awt.Color(22, 163, 74));
        btnFinalizarEmprestimos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFinalizarEmprestimos.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizarEmprestimos.setText("Finalizar selecionado");
        btnFinalizarEmprestimos.setBorderPainted(false);
        btnFinalizarEmprestimos.setFocusPainted(false);
        btnFinalizarEmprestimos.setOpaque(true);
        btnFinalizarEmprestimos.setPreferredSize(new java.awt.Dimension(200, 38));
        btnFinalizarEmprestimos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarEmprestimosActionPerformed(evt);
            }
        });
        painelRodapeEmprestimos.add(btnFinalizarEmprestimos);

        cartaoTabelaEmprestimos.add(painelRodapeEmprestimos, java.awt.BorderLayout.SOUTH);

        telaEmprestimos.add(cartaoTabelaEmprestimos, java.awt.BorderLayout.CENTER);

        areaTelas.add(telaEmprestimos, "emprestimos");

        getContentPane().add(areaTelas, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        abrirInicio();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLivrosActionPerformed
        abrirLivros();
    }//GEN-LAST:event_btnLivrosActionPerformed

    private void btnPessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPessoasActionPerformed
        abrirPessoas();
    }//GEN-LAST:event_btnPessoasActionPerformed

    private void btnEmprestimosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmprestimosActionPerformed
        abrirEmprestimos();
    }//GEN-LAST:event_btnEmprestimosActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0); // era a opção -1 do terminal
    }//GEN-LAST:event_btnSairActionPerformed

    private void comboTipoLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoLivrosActionPerformed
        atualizarRotuloExtraLivro(); // muda o texto do último campo
    }//GEN-LAST:event_comboTipoLivrosActionPerformed

    private void btnCadastrarLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarLivrosActionPerformed
        cadastrarLivro();
    }//GEN-LAST:event_btnCadastrarLivrosActionPerformed

    private void btnSalvarLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarLivrosActionPerformed
        salvarLivro();
    }//GEN-LAST:event_btnSalvarLivrosActionPerformed

    private void btnRemoverLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverLivrosActionPerformed
        removerLivro();
    }//GEN-LAST:event_btnRemoverLivrosActionPerformed

    private void btnLimparLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparLivrosActionPerformed
        limparCamposLivro();
    }//GEN-LAST:event_btnLimparLivrosActionPerformed

    private void btnBuscarLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarLivrosActionPerformed
        buscarLivro();
    }//GEN-LAST:event_btnBuscarLivrosActionPerformed

    private void comboTipoPessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoPessoasActionPerformed
        atualizarRotuloExtraPessoa(); // muda o texto do último campo
    }//GEN-LAST:event_comboTipoPessoasActionPerformed

    private void btnCadastrarPessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarPessoasActionPerformed
        cadastrarPessoa();
    }//GEN-LAST:event_btnCadastrarPessoasActionPerformed

    private void btnSalvarPessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPessoasActionPerformed
        salvarPessoa();
    }//GEN-LAST:event_btnSalvarPessoasActionPerformed

    private void btnLimparPessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparPessoasActionPerformed
        limparCamposPessoa();
    }//GEN-LAST:event_btnLimparPessoasActionPerformed

    private void btnRealizarEmprestimosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarEmprestimosActionPerformed
        realizarEmprestimo();
    }//GEN-LAST:event_btnRealizarEmprestimosActionPerformed

    private void btnFinalizarEmprestimosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarEmprestimosActionPerformed
        finalizarEmprestimo();
    }//GEN-LAST:event_btnFinalizarEmprestimosActionPerformed

    // =================================================================
    // Menu: troca de telas
    // =================================================================

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

    // CardLayout funciona como um baralho: mostra uma tela (carta) de cada vez
    private void mostrarTela(String nome) {
        CardLayout cartas = (CardLayout) areaTelas.getLayout();
        cartas.show(areaTelas, nome);
    }

    // Cada método abaixo atualiza a tela e depois a mostra
    private void abrirInicio() {
        atualizarTelaInicio();
        mostrarTela("inicio");
        destacarBotao(btnInicio);
    }

    private void abrirLivros() {
        atualizarTelaLivros();
        mostrarTela("livros");
        destacarBotao(btnLivros);
    }

    private void abrirPessoas() {
        atualizarTelaPessoas();
        mostrarTela("pessoas");
        destacarBotao(btnPessoas);
    }

    private void abrirEmprestimos() {
        atualizarTelaEmprestimos();
        mostrarTela("emprestimos");
        destacarBotao(btnEmprestimos);
    }

    // =================================================================
    // Tela Início
    // =================================================================

    // Recalcula os números dos cartões (chamado sempre que a tela abre)
    public void atualizarTelaInicio() {
        lblTotalLivrosInicio.setText(String.valueOf(dados.livros.size()));
        lblTotalPessoasInicio.setText(String.valueOf(dados.pessoas.size()));
        lblTotalEmprestimosInicio.setText(String.valueOf(dados.emprestimos.size()));
    }

    // =================================================================
    // Tela Livros
    // =================================================================

    // ---------------------------------------------------------------
    // Ações dos botões
    // ---------------------------------------------------------------

    private void cadastrarLivro() {
        if (!camposLivroValidos()) {
            return;
        }

        String titulo = campoTituloLivros.getText().trim();
        String autor = campoAutorLivros.getText().trim();
        int paginas = Integer.parseInt(campoPaginasLivros.getText().trim());
        double valorExtra = lerDecimal(campoExtraLivros.getText());

        Livro livro;
        if (comboTipoLivros.getSelectedIndex() == 0) {
            livro = new EBook(valorExtra, dados.proximoIdLivro, titulo, autor, paginas);
        } else {
            livro = new LivroFisico(valorExtra, dados.proximoIdLivro, titulo, autor, paginas);
        }
        dados.proximoIdLivro++;

        dados.biblioteca.incluirLivro(livro); // método original
        dados.livros.add(livro);              // cópia para a tabela

        atualizarTabelaLivros();
        limparCamposLivro();
        JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
    }

    private void salvarLivro() {
        if (livroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Clique em um livro da tabela para editar.");
            return;
        }
        if (!camposLivroValidos()) {
            return;
        }

        String titulo = campoTituloLivros.getText().trim();
        String autor = campoAutorLivros.getText().trim();
        int paginas = Integer.parseInt(campoPaginasLivros.getText().trim());
        double valorExtra = lerDecimal(campoExtraLivros.getText());
        Integer id = livroSelecionado.getControle();

        // Chama o método de edição certo para cada tipo de livro
        if (livroSelecionado instanceof EBook) {
            dados.biblioteca.editarLivroEbook(livroSelecionado, id, autor, valorExtra, titulo, paginas);
        } else {
            dados.biblioteca.editarLivroFisico(livroSelecionado, id, autor, valorExtra, titulo, paginas);
        }

        atualizarTabelaLivros();
        limparCamposLivro();
        JOptionPane.showMessageDialog(this, "Livro editado com sucesso!");
    }

    private void removerLivro() {
        if (livroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Clique em um livro da tabela para remover.");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this,
                "Você realmente quer remover o livro \"" + livroSelecionado.getTitulo() + "\"?",
                "Remover livro", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            dados.biblioteca.removerLivro(livroSelecionado.getControle()); // método original
            dados.livros.remove(livroSelecionado);                        // cópia para a tabela
            atualizarTabelaLivros();
            limparCamposLivro();
        }
    }

    private void buscarLivro() {
        int id;
        try {
            id = Integer.parseInt(campoBuscaLivros.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O ID deve ser um número inteiro.");
            return;
        }

        Livro encontrado = dados.biblioteca.buscarLivro(id); // método original

        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "Livro com o ID " + id + " não foi encontrado.");
        } else {
            // Seleciona a linha do livro encontrado (isso também preenche o formulário)
            int linha = dados.livros.indexOf(encontrado);
            tabelaLivros.setRowSelectionInterval(linha, linha);
            tabelaLivros.scrollRectToVisible(tabelaLivros.getCellRect(linha, 0, true));
        }
    }

    // ---------------------------------------------------------------
    // Métodos de apoio
    // ---------------------------------------------------------------

    // Chamado sempre que a tela abre: recarrega a tabela e limpa o formulário
    public void atualizarTelaLivros() {
        atualizarTabelaLivros();
        limparCamposLivro();
    }

    // Apaga todas as linhas da tabela e preenche de novo com a lista atual
    private void atualizarTabelaLivros() {
        modeloTabelaLivros.setRowCount(0);

        for (Livro livro : dados.livros) {
            String tipo;
            String extra;
            if (livro instanceof EBook) {
                tipo = "E-Book";
                extra = String.format("%.2f MB", ((EBook) livro).getTamanhoArquivo());
            } else {
                tipo = "Físico";
                extra = String.format("%.2f kg", ((LivroFisico) livro).getPeso());
            }

            modeloTabelaLivros.addRow(new Object[]{
                livro.getControle(), tipo, livro.getTitulo(), livro.getAutor(),
                livro.getNumeroPaginas(), extra
            });
        }
    }

    // Copia os dados do livro clicado para os campos do formulário
    private void preencherFormularioLivro() {
        int linha = tabelaLivros.getSelectedRow();
        if (linha == -1) {
            return; // nenhuma linha selecionada
        }

        livroSelecionado = dados.livros.get(linha);

        campoTituloLivros.setText(livroSelecionado.getTitulo());
        campoAutorLivros.setText(livroSelecionado.getAutor());
        campoPaginasLivros.setText(String.valueOf(livroSelecionado.getNumeroPaginas()));

        if (livroSelecionado instanceof EBook) {
            comboTipoLivros.setSelectedIndex(0);
            campoExtraLivros.setText(String.valueOf(((EBook) livroSelecionado).getTamanhoArquivo()));
        } else {
            comboTipoLivros.setSelectedIndex(1);
            campoExtraLivros.setText(String.valueOf(((LivroFisico) livroSelecionado).getPeso()));
        }

        comboTipoLivros.setEnabled(false); // o tipo de um livro já cadastrado não muda
    }

    private void limparCamposLivro() {
        livroSelecionado = null;
        tabelaLivros.clearSelection();
        comboTipoLivros.setEnabled(true);
        campoTituloLivros.setText("");
        campoAutorLivros.setText("");
        campoPaginasLivros.setText("");
        campoExtraLivros.setText("");
    }

    private void atualizarRotuloExtraLivro() {
        if (comboTipoLivros.getSelectedIndex() == 0) {
            rotuloExtraLivros.setText("Tamanho do arquivo (MB)");
        } else {
            rotuloExtraLivros.setText("Peso (kg)");
        }
    }

    // Mesmas regras do menu do terminal: nada vazio e números maiores que zero
    private boolean camposLivroValidos() {
        if (campoTituloLivros.getText().trim().isEmpty() || campoAutorLivros.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O título e o autor não podem ficar em branco.");
            return false;
        }

        try {
            int paginas = Integer.parseInt(campoPaginasLivros.getText().trim());
            double valorExtra = lerDecimal(campoExtraLivros.getText());

            if (paginas <= 0 || valorExtra <= 0) {
                JOptionPane.showMessageDialog(this, "Páginas e " + rotuloExtraLivros.getText()
                        + " devem ser maiores que zero.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Digite números válidos. Páginas: número inteiro. Tamanho/Peso: ex. 10.5");
            return false;
        }
        return true;
    }

    // Aceita tanto 10.5 quanto 10,5
    private double lerDecimal(String texto) {
        return Double.parseDouble(texto.trim().replace(",", "."));
    }

    // =================================================================
    // Tela Pessoas
    // =================================================================

    // ---------------------------------------------------------------
    // Ações dos botões
    // ---------------------------------------------------------------

    private void cadastrarPessoa() {
        if (!camposPessoaValidos()) {
            return;
        }

        String nome = campoNomePessoas.getText().trim();
        int idade = Integer.parseInt(campoIdadePessoas.getText().trim());
        String cpf = campoCpfPessoas.getText().trim();
        String cep = campoCepPessoas.getText().trim();
        String extra = campoExtraPessoas.getText().trim();

        Pessoa pessoa;
        if (comboTipoPessoas.getSelectedIndex() == 0) {
            pessoa = new Membro(extra, dados.proximoIdPessoa, nome, idade, cpf, cep);
        } else {
            pessoa = new Funcionario(extra, dados.proximoIdPessoa, nome, idade, cpf, cep);
        }
        dados.proximoIdPessoa++;

        dados.gestaoPessoas.cadastrarPessoa(pessoa); // método original
        dados.pessoas.add(pessoa);                   // cópia para a tabela

        atualizarTabelaPessoas();
        limparCamposPessoa();
        JOptionPane.showMessageDialog(this, "Pessoa cadastrada com sucesso!");
    }

    private void salvarPessoa() {
        if (pessoaSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Clique em uma pessoa da tabela para editar.");
            return;
        }
        if (!camposPessoaValidos()) {
            return;
        }

        String nome = campoNomePessoas.getText().trim();
        int idade = Integer.parseInt(campoIdadePessoas.getText().trim());
        String cpf = campoCpfPessoas.getText().trim();
        String cep = campoCepPessoas.getText().trim();
        String extra = campoExtraPessoas.getText().trim();
        Integer id = pessoaSelecionada.getControle();

        // Chama o método de edição certo para cada tipo de pessoa
        if (pessoaSelecionada instanceof Membro) {
            dados.gestaoPessoas.editarCadastroMembro(pessoaSelecionada, id, nome, idade, cpf, cep, extra);
        } else {
            dados.gestaoPessoas.editarCadastroFuncionario(pessoaSelecionada, id, nome, idade, cpf, cep, extra);
        }

        atualizarTabelaPessoas();
        limparCamposPessoa();
        JOptionPane.showMessageDialog(this, "Cadastro editado com sucesso!");
    }

    // ---------------------------------------------------------------
    // Métodos de apoio
    // ---------------------------------------------------------------

    // Chamado sempre que a tela abre: recarrega a tabela e limpa o formulário
    public void atualizarTelaPessoas() {
        atualizarTabelaPessoas();
        limparCamposPessoa();
    }

    // Apaga todas as linhas da tabela e preenche de novo com a lista atual
    private void atualizarTabelaPessoas() {
        modeloTabelaPessoas.setRowCount(0);

        for (Pessoa pessoa : dados.pessoas) {
            String tipo;
            String extra;
            if (pessoa instanceof Membro) {
                tipo = "Membro";
                extra = ((Membro) pessoa).getPreferencia();
            } else {
                tipo = "Funcionário";
                extra = ((Funcionario) pessoa).getNumeroRegistro();
            }

            modeloTabelaPessoas.addRow(new Object[]{
                pessoa.getControle(), tipo, pessoa.getNome(), pessoa.getIdade(),
                pessoa.getCpf(), pessoa.getCep(), extra
            });
        }
    }

    private void preencherFormularioPessoa() {
        int linha = tabelaPessoas.getSelectedRow();
        if (linha == -1) {
            return;
        }

        pessoaSelecionada = dados.pessoas.get(linha);

        campoNomePessoas.setText(pessoaSelecionada.getNome());
        campoIdadePessoas.setText(String.valueOf(pessoaSelecionada.getIdade()));
        campoCpfPessoas.setText(pessoaSelecionada.getCpf());
        campoCepPessoas.setText(pessoaSelecionada.getCep());

        if (pessoaSelecionada instanceof Membro) {
            comboTipoPessoas.setSelectedIndex(0);
            campoExtraPessoas.setText(((Membro) pessoaSelecionada).getPreferencia());
        } else {
            comboTipoPessoas.setSelectedIndex(1);
            campoExtraPessoas.setText(((Funcionario) pessoaSelecionada).getNumeroRegistro());
        }

        comboTipoPessoas.setEnabled(false); // o tipo de uma pessoa já cadastrada não muda
    }

    private void limparCamposPessoa() {
        pessoaSelecionada = null;
        tabelaPessoas.clearSelection();
        comboTipoPessoas.setEnabled(true);
        campoNomePessoas.setText("");
        campoIdadePessoas.setText("");
        campoCpfPessoas.setText("");
        campoCepPessoas.setText("");
        campoExtraPessoas.setText("");
    }

    private void atualizarRotuloExtraPessoa() {
        if (comboTipoPessoas.getSelectedIndex() == 0) {
            rotuloExtraPessoas.setText("Preferência");
        } else {
            rotuloExtraPessoas.setText("Número de registro");
        }
    }

    private boolean camposPessoaValidos() {
        if (campoNomePessoas.getText().trim().isEmpty() || campoCpfPessoas.getText().trim().isEmpty()
                || campoCepPessoas.getText().trim().isEmpty() || campoExtraPessoas.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return false;
        }

        try {
            int idade = Integer.parseInt(campoIdadePessoas.getText().trim());
            if (idade <= 0) {
                JOptionPane.showMessageDialog(this, "A idade deve ser maior que zero.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "A idade deve ser um número inteiro.");
            return false;
        }
        return true;
    }

    // =================================================================
    // Tela Empréstimos
    // =================================================================

    // ---------------------------------------------------------------
    // Ações dos botões
    // ---------------------------------------------------------------

    private void realizarEmprestimo() {
        String data = campoDataEmprestimos.getText().trim();
        int indiceMembro = comboMembroEmprestimos.getSelectedIndex();
        int indiceFuncionario = comboFuncionarioEmprestimos.getSelectedIndex();
        int[] livrosEscolhidos = listaLivrosEmprestimos.getSelectedIndices();

        // Validações
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a data do empréstimo.");
            return;
        }
        if (indiceMembro == -1) {
            JOptionPane.showMessageDialog(this, "Cadastre um membro na tela Pessoas antes.");
            return;
        }
        if (indiceFuncionario == -1) {
            JOptionPane.showMessageDialog(this, "Cadastre um funcionário na tela Pessoas antes.");
            return;
        }
        if (livrosEscolhidos.length == 0) {
            JOptionPane.showMessageDialog(this, "Escolha pelo menos um livro na lista.");
            return;
        }

        Membro membro = membros.get(indiceMembro);
        Funcionario funcionario = funcionarios.get(indiceFuncionario);

        // Mesmo jeito da main antiga: o empréstimo guarda os nomes das pessoas
        Emprestimo emprestimo = new Emprestimo(dados.proximoIdEmprestimo, data,
                membro.getNome(), funcionario.getNome());
        dados.proximoIdEmprestimo++;

        for (int indice : livrosEscolhidos) {
            emprestimo.getLivros().add(dados.livros.get(indice));
        }

        dados.gerirEmprestimos.realizarEmprestimo(emprestimo); // método original
        dados.emprestimos.add(emprestimo);                     // cópia para a tabela

        atualizarTelaEmprestimos();
        JOptionPane.showMessageDialog(this, "Empréstimo realizado com sucesso!");
    }

    private void finalizarEmprestimo() {
        int linha = tabelaEmprestimos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Clique em um empréstimo da tabela para finalizar.");
            return;
        }

        Emprestimo emprestimo = dados.emprestimos.get(linha);

        int resposta = JOptionPane.showConfirmDialog(this,
                "Finalizar o empréstimo " + emprestimo.getControle() + " de " + emprestimo.getMembro() + "?",
                "Finalizar empréstimo", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            dados.gerirEmprestimos.finalizarEmprestimo(emprestimo.getControle()); // método original
            dados.emprestimos.remove(emprestimo);                                // cópia para a tabela
            atualizarTabelaEmprestimos();
        }
    }

    // ---------------------------------------------------------------
    // Métodos de apoio
    // ---------------------------------------------------------------

    // Recarrega tudo: combos, lista de livros e tabela.
    // É chamado sempre que a tela abre, para mostrar o que foi cadastrado nas outras telas.
    public void atualizarTelaEmprestimos() {
        // Data de hoje já preenchida (pode ser trocada)
        campoDataEmprestimos.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Separa as pessoas em membros e funcionários
        membros.clear();
        funcionarios.clear();
        comboMembroEmprestimos.removeAllItems();
        comboFuncionarioEmprestimos.removeAllItems();

        for (Pessoa pessoa : dados.pessoas) {
            if (pessoa instanceof Membro) {
                membros.add((Membro) pessoa);
                comboMembroEmprestimos.addItem(pessoa.getControle() + " - " + pessoa.getNome());
            } else if (pessoa instanceof Funcionario) {
                funcionarios.add((Funcionario) pessoa);
                comboFuncionarioEmprestimos.addItem(pessoa.getControle() + " - " + pessoa.getNome());
            }
        }

        // Lista de livros (na mesma ordem de dados.livros)
        modeloLista.clear();
        for (Livro livro : dados.livros) {
            modeloLista.addElement(livro.getControle() + " - " + livro.getTitulo());
        }

        atualizarTabelaEmprestimos();
    }

    private void atualizarTabelaEmprestimos() {
        modeloTabelaEmprestimos.setRowCount(0);

        for (Emprestimo emprestimo : dados.emprestimos) {
            // Junta os títulos dos livros em um texto só: "Livro A, Livro B"
            String titulos = "";
            for (Livro livro : emprestimo.getLivros()) {
                if (!titulos.isEmpty()) {
                    titulos += ", ";
                }
                titulos += livro.getTitulo();
            }

            modeloTabelaEmprestimos.addRow(new Object[]{
                emprestimo.getControle(), emprestimo.getDataEmprestimo(),
                emprestimo.getMembro(), emprestimo.getFuncionarioAtendeu(), titulos
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel areaTelas;
    private javax.swing.JPanel barraBuscaLivros;
    private javax.swing.JButton btnBuscarLivros;
    private javax.swing.JButton btnCadastrarLivros;
    private javax.swing.JButton btnCadastrarPessoas;
    private javax.swing.JButton btnEmprestimos;
    private javax.swing.JButton btnFinalizarEmprestimos;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnLimparLivros;
    private javax.swing.JButton btnLimparPessoas;
    private javax.swing.JButton btnLivros;
    private javax.swing.JButton btnPessoas;
    private javax.swing.JButton btnRealizarEmprestimos;
    private javax.swing.JButton btnRemoverLivros;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvarLivros;
    private javax.swing.JButton btnSalvarPessoas;
    private javax.swing.JTextField campoAutorLivros;
    private javax.swing.JTextField campoBuscaLivros;
    private javax.swing.JTextField campoCepPessoas;
    private javax.swing.JTextField campoCpfPessoas;
    private javax.swing.JTextField campoDataEmprestimos;
    private javax.swing.JTextField campoExtraLivros;
    private javax.swing.JTextField campoExtraPessoas;
    private javax.swing.JTextField campoIdadePessoas;
    private javax.swing.JTextField campoNomePessoas;
    private javax.swing.JTextField campoPaginasLivros;
    private javax.swing.JTextField campoTituloLivros;
    private javax.swing.JPanel cartaoDicasInicio;
    private javax.swing.JPanel cartaoEmprestimosInicio;
    private javax.swing.JPanel cartaoFormularioEmprestimos;
    private javax.swing.JPanel cartaoFormularioLivros;
    private javax.swing.JPanel cartaoFormularioPessoas;
    private javax.swing.JPanel cartaoLivrosInicio;
    private javax.swing.JPanel cartaoPessoasInicio;
    private javax.swing.JPanel cartaoTabelaEmprestimos;
    private javax.swing.JPanel cartaoTabelaLivros;
    private javax.swing.JPanel cartaoTabelaPessoas;
    private javax.swing.JComboBox<String> comboFuncionarioEmprestimos;
    private javax.swing.JComboBox<String> comboMembroEmprestimos;
    private javax.swing.JComboBox<String> comboTipoLivros;
    private javax.swing.JComboBox<String> comboTipoPessoas;
    private javax.swing.JPanel grupoAutorLivros;
    private javax.swing.JPanel grupoCepPessoas;
    private javax.swing.JPanel grupoCpfPessoas;
    private javax.swing.JPanel grupoDataEmprestimos;
    private javax.swing.JPanel grupoExtraLivros;
    private javax.swing.JPanel grupoExtraPessoas;
    private javax.swing.JPanel grupoFuncionarioEmprestimos;
    private javax.swing.JPanel grupoIdadePessoas;
    private javax.swing.JPanel grupoLivrosEmprestimos;
    private javax.swing.JPanel grupoMembroEmprestimos;
    private javax.swing.JPanel grupoNomePessoas;
    private javax.swing.JPanel grupoPaginasLivros;
    private javax.swing.JPanel grupoTipoLivros;
    private javax.swing.JPanel grupoTipoPessoas;
    private javax.swing.JPanel grupoTituloLivros;
    private javax.swing.JLabel lblAutorLivros;
    private javax.swing.JLabel lblBuscarLivros;
    private javax.swing.JLabel lblCabecalhoSubtituloEmprestimos;
    private javax.swing.JLabel lblCabecalhoSubtituloInicio;
    private javax.swing.JLabel lblCabecalhoSubtituloLivros;
    private javax.swing.JLabel lblCabecalhoSubtituloPessoas;
    private javax.swing.JLabel lblCabecalhoTituloEmprestimos;
    private javax.swing.JLabel lblCabecalhoTituloInicio;
    private javax.swing.JLabel lblCabecalhoTituloLivros;
    private javax.swing.JLabel lblCabecalhoTituloPessoas;
    private javax.swing.JLabel lblCepPessoas;
    private javax.swing.JLabel lblComoUsarInicio;
    private javax.swing.JLabel lblCpfPessoas;
    private javax.swing.JLabel lblDataEmprestimos;
    private javax.swing.JLabel lblDescricaoEmprestimosInicio;
    private javax.swing.JLabel lblDescricaoLivrosInicio;
    private javax.swing.JLabel lblDescricaoPessoasInicio;
    private javax.swing.JLabel lblEmprestimosAtivosEmprestimos;
    private javax.swing.JLabel lblFuncionarioEmprestimos;
    private javax.swing.JLabel lblIdadePessoas;
    private javax.swing.JLabel lblLivrosEmprestimos;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMembroEmprestimos;
    private javax.swing.JLabel lblNomePessoas;
    private javax.swing.JLabel lblPaginasLivros;
    private javax.swing.JLabel lblPasso1Inicio;
    private javax.swing.JLabel lblPasso2Inicio;
    private javax.swing.JLabel lblPasso3Inicio;
    private javax.swing.JLabel lblSlogan;
    private javax.swing.JLabel lblTipoLivros;
    private javax.swing.JLabel lblTipoPessoas;
    private javax.swing.JLabel lblTituloLivros;
    private javax.swing.JLabel lblTotalEmprestimosInicio;
    private javax.swing.JLabel lblTotalLivrosInicio;
    private javax.swing.JLabel lblTotalPessoasInicio;
    private javax.swing.JList<String> listaLivrosEmprestimos;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel painelBotoesLivros;
    private javax.swing.JPanel painelBotoesMenu;
    private javax.swing.JPanel painelBotoesPessoas;
    private javax.swing.JPanel painelCabecalhoEmprestimos;
    private javax.swing.JPanel painelCabecalhoInicio;
    private javax.swing.JPanel painelCabecalhoLivros;
    private javax.swing.JPanel painelCabecalhoPessoas;
    private javax.swing.JPanel painelCamposEmprestimos;
    private javax.swing.JPanel painelCamposLivros;
    private javax.swing.JPanel painelCamposPessoas;
    private javax.swing.JPanel painelCartoesInicio;
    private javax.swing.JPanel painelCentroInicio;
    private javax.swing.JPanel painelConteudoLivros;
    private javax.swing.JPanel painelConteudoPessoas;
    private javax.swing.JPanel painelPassosInicio;
    private javax.swing.JPanel painelRodapeEmprestimos;
    private javax.swing.JPanel painelTopoInicio;
    private javax.swing.JPanel parteDeCima;
    private javax.swing.JScrollPane rolagemFormularioLivros;
    private javax.swing.JScrollPane rolagemFormularioPessoas;
    private javax.swing.JScrollPane rolagemLivrosEmprestimos;
    private javax.swing.JScrollPane rolagemTabelaEmprestimos;
    private javax.swing.JScrollPane rolagemTabelaLivros;
    private javax.swing.JScrollPane rolagemTabelaPessoas;
    private javax.swing.JLabel rotuloExtraLivros;
    private javax.swing.JLabel rotuloExtraPessoas;
    private javax.swing.JTable tabelaEmprestimos;
    private javax.swing.JTable tabelaLivros;
    private javax.swing.JTable tabelaPessoas;
    private javax.swing.JPanel telaEmprestimos;
    private javax.swing.JPanel telaInicio;
    private javax.swing.JPanel telaLivros;
    private javax.swing.JPanel telaPessoas;
    private javax.swing.JPanel topo;
    // End of variables declaration//GEN-END:variables
}
