package projeto.biblioteca.telas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import projeto.biblioteca.Emprestimo;
import projeto.biblioteca.Funcionario;
import projeto.biblioteca.Livro;
import projeto.biblioteca.Membro;
import projeto.biblioteca.Pessoa;

/**
 * Tela "Gerir Emprestimos": realizar, finalizar e listar empréstimos.
 */
public class PainelEmprestimos extends JPanel {

    private Dados dados;

    // Listas que guardam, na mesma ordem dos JComboBox, quem aparece em cada um
    private ArrayList<Membro> membros = new ArrayList<>();
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();

    // Campos do formulário
    private JTextField campoData = Estilo.criarCampo();
    private JComboBox<String> comboMembro = new JComboBox<>();
    private JComboBox<String> comboFuncionario = new JComboBox<>();
    private DefaultListModel<String> modeloLista = new DefaultListModel<>();
    private JList<String> listaLivros = new JList<>(modeloLista);

    // Tabela
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public PainelEmprestimos(Dados dados) {
        this.dados = dados;

        setLayout(new BorderLayout(20, 20));
        setBackground(Cores.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        add(Estilo.criarCabecalho("Empréstimos",
                "Registre quem pegou quais livros e finalize na devolução."), BorderLayout.NORTH);
        add(criarFormulario(), BorderLayout.WEST);
        add(criarAreaTabela(), BorderLayout.CENTER);
    }

    // ---------------------------------------------------------------
    // Montagem da tela
    // ---------------------------------------------------------------

    private JPanel criarFormulario() {
        comboMembro.setFont(Cores.FONTE_NORMAL);
        comboMembro.setBackground(Cores.CARTAO);
        comboFuncionario.setFont(Cores.FONTE_NORMAL);
        comboFuncionario.setBackground(Cores.CARTAO);

        // Cada grupo tem o rótulo em cima e o campo embaixo
        JPanel campos = new JPanel(new GridLayout(0, 1, 0, 10));
        campos.setOpaque(false);
        campos.add(Estilo.criarGrupo(Estilo.criarRotulo("Data do empréstimo"), campoData));
        campos.add(Estilo.criarGrupo(Estilo.criarRotulo("Membro (quem pega o livro)"), comboMembro));
        campos.add(Estilo.criarGrupo(Estilo.criarRotulo("Funcionário (quem atende)"), comboFuncionario));

        // Lista de livros: permite selecionar mais de um
        listaLivros.setFont(Cores.FONTE_NORMAL);
        listaLivros.setForeground(Cores.TEXTO);
        listaLivros.setSelectionBackground(Cores.AZUL_CLARO);
        listaLivros.setSelectionForeground(Cores.TEXTO);
        listaLivros.setFixedCellHeight(26);

        JButton btnRealizar = Estilo.criarBotao("Realizar empréstimo", Cores.AZUL);
        btnRealizar.addActionListener(e -> realizarEmprestimo());

        JScrollPane rolagemLivros = new JScrollPane(listaLivros);
        rolagemLivros.setBorder(BorderFactory.createLineBorder(Cores.BORDA));

        JPanel cartao = Estilo.criarCartao();
        cartao.setPreferredSize(new Dimension(320, 0));
        cartao.add(campos, BorderLayout.NORTH);
        cartao.add(Estilo.criarGrupo(Estilo.criarRotulo("Livros (Ctrl + clique para vários)"), rolagemLivros),
                BorderLayout.CENTER);
        cartao.add(btnRealizar, BorderLayout.SOUTH);
        return cartao;
    }

    private JPanel criarAreaTabela() {
        String[] colunas = {"ID", "Data", "Membro", "Funcionário", "Livros"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        Estilo.estilizarTabela(tabela);
        tabela.getColumnModel().getColumn(0).setMaxWidth(50);

        JButton btnFinalizar = Estilo.criarBotao("Finalizar selecionado", Cores.VERDE);
        btnFinalizar.setPreferredSize(new Dimension(200, 38));
        btnFinalizar.addActionListener(e -> finalizarEmprestimo());

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rodape.setOpaque(false);
        rodape.add(btnFinalizar);

        JPanel cartao = Estilo.criarCartao();
        cartao.add(Estilo.criarRotulo("EMPRÉSTIMOS ATIVOS"), BorderLayout.NORTH);
        cartao.add(Estilo.criarRolagem(tabela), BorderLayout.CENTER);
        cartao.add(rodape, BorderLayout.SOUTH);
        return cartao;
    }

    // ---------------------------------------------------------------
    // Ações dos botões
    // ---------------------------------------------------------------

    private void realizarEmprestimo() {
        String data = campoData.getText().trim();
        int indiceMembro = comboMembro.getSelectedIndex();
        int indiceFuncionario = comboFuncionario.getSelectedIndex();
        int[] livrosEscolhidos = listaLivros.getSelectedIndices();

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

        atualizar();
        JOptionPane.showMessageDialog(this, "Empréstimo realizado com sucesso!");
    }

    private void finalizarEmprestimo() {
        int linha = tabela.getSelectedRow();
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
            atualizarTabela();
        }
    }

    // ---------------------------------------------------------------
    // Métodos de apoio
    // ---------------------------------------------------------------

    // Recarrega tudo: combos, lista de livros e tabela.
    // É chamado sempre que a tela abre, para mostrar o que foi cadastrado nas outras telas.
    public void atualizar() {
        // Data de hoje já preenchida (pode ser trocada)
        campoData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Separa as pessoas em membros e funcionários
        membros.clear();
        funcionarios.clear();
        comboMembro.removeAllItems();
        comboFuncionario.removeAllItems();

        for (Pessoa pessoa : dados.pessoas) {
            if (pessoa instanceof Membro) {
                membros.add((Membro) pessoa);
                comboMembro.addItem(pessoa.getControle() + " - " + pessoa.getNome());
            } else if (pessoa instanceof Funcionario) {
                funcionarios.add((Funcionario) pessoa);
                comboFuncionario.addItem(pessoa.getControle() + " - " + pessoa.getNome());
            }
        }

        // Lista de livros (na mesma ordem de dados.livros)
        modeloLista.clear();
        for (Livro livro : dados.livros) {
            modeloLista.addElement(livro.getControle() + " - " + livro.getTitulo());
        }

        atualizarTabela();
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);

        for (Emprestimo emprestimo : dados.emprestimos) {
            // Junta os títulos dos livros em um texto só: "Livro A, Livro B"
            String titulos = "";
            for (Livro livro : emprestimo.getLivros()) {
                if (!titulos.isEmpty()) {
                    titulos += ", ";
                }
                titulos += livro.getTitulo();
            }

            modeloTabela.addRow(new Object[]{
                emprestimo.getControle(), emprestimo.getDataEmprestimo(),
                emprestimo.getMembro(), emprestimo.getFuncionarioAtendeu(), titulos
            });
        }
    }
}
