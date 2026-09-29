package projeto.biblioteca.telas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import projeto.biblioteca.Funcionario;
import projeto.biblioteca.Membro;
import projeto.biblioteca.Pessoa;

/**
 * Tela "Gerir Membros": cadastrar, editar e listar membros e funcionários.
 * (Não tem botão Remover porque a classe GestaoPessoas não tem esse método.)
 */
public class PainelPessoas extends JPanel {

    private Dados dados;

    // Pessoa que foi clicada na tabela (null quando nenhuma está selecionada)
    private Pessoa pessoaSelecionada = null;

    // Campos do formulário
    private JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Membro", "Funcionário"});
    private JTextField campoNome = Estilo.criarCampo();
    private JTextField campoIdade = Estilo.criarCampo();
    private JTextField campoCpf = Estilo.criarCampo();
    private JTextField campoCep = Estilo.criarCampo();
    private JTextField campoExtra = Estilo.criarCampo(); // preferência ou nº de registro
    private JLabel rotuloExtra = Estilo.criarRotulo("Preferência");

    // Tabela
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public PainelPessoas(Dados dados) {
        this.dados = dados;

        setLayout(new BorderLayout(20, 20));
        setBackground(Cores.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        add(Estilo.criarCabecalho("Pessoas",
                "Cadastre e edite membros e funcionários da biblioteca."), BorderLayout.NORTH);
        add(criarFormulario(), BorderLayout.WEST);
        add(criarAreaTabela(), BorderLayout.CENTER);
    }

    // ---------------------------------------------------------------
    // Montagem da tela
    // ---------------------------------------------------------------

    private JScrollPane criarFormulario() {
        comboTipo.setFont(Cores.FONTE_NORMAL);
        comboTipo.setBackground(Cores.CARTAO);
        comboTipo.addActionListener(e -> atualizarRotuloExtra());

        // Cada grupo tem o rótulo em cima e o campo embaixo
        JPanel campos = new JPanel(new GridLayout(0, 1, 0, 10));
        campos.setOpaque(false);
        campos.add(Estilo.criarGrupo(Estilo.criarRotulo("Tipo"), comboTipo));
        campos.add(Estilo.criarGrupo(Estilo.criarRotulo("Nome"), campoNome));
        campos.add(Estilo.criarGrupo(Estilo.criarRotulo("Idade"), campoIdade));
        campos.add(Estilo.criarGrupo(Estilo.criarRotulo("CPF"), campoCpf));
        campos.add(Estilo.criarGrupo(Estilo.criarRotulo("CEP"), campoCep));
        campos.add(Estilo.criarGrupo(rotuloExtra, campoExtra));

        JButton btnCadastrar = Estilo.criarBotao("Cadastrar", Cores.AZUL);
        JButton btnSalvar = Estilo.criarBotao("Salvar alterações", Cores.VERDE);
        JButton btnLimpar = Estilo.criarBotao("Limpar", Cores.CINZA);

        btnCadastrar.addActionListener(e -> cadastrar());
        btnSalvar.addActionListener(e -> salvarAlteracoes());
        btnLimpar.addActionListener(e -> limparCampos());

        JPanel botoes = new JPanel(new GridLayout(0, 1, 0, 8));
        botoes.setOpaque(false);
        botoes.add(btnCadastrar);
        botoes.add(btnSalvar);
        botoes.add(btnLimpar);

        // Campos e botões empilhados: os botões ficam logo abaixo do último campo,
        // assim eles nunca ficam por cima dos campos em telas menores
        JPanel conteudo = new JPanel(new BorderLayout(0, 16));
        conteudo.setOpaque(false);
        conteudo.add(campos, BorderLayout.NORTH);
        conteudo.add(botoes, BorderLayout.CENTER);

        JPanel cartao = Estilo.criarCartao();
        cartao.add(conteudo, BorderLayout.NORTH);
        return Estilo.criarRolagemFormulario(cartao);
    }

    private JPanel criarAreaTabela() {
        String[] colunas = {"ID", "Tipo", "Nome", "Idade", "CPF", "CEP", "Pref. / Registro"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        Estilo.estilizarTabela(tabela);
        tabela.getColumnModel().getColumn(0).setMaxWidth(50);
        tabela.getColumnModel().getColumn(3).setMaxWidth(70);

        // Ao clicar em uma linha, a pessoa vai para o formulário
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherFormulario();
            }
        });

        JPanel cartao = Estilo.criarCartao();
        cartao.add(Estilo.criarRolagem(tabela), BorderLayout.CENTER);
        return cartao;
    }

    // ---------------------------------------------------------------
    // Ações dos botões
    // ---------------------------------------------------------------

    private void cadastrar() {
        if (!camposValidos()) {
            return;
        }

        String nome = campoNome.getText().trim();
        int idade = Integer.parseInt(campoIdade.getText().trim());
        String cpf = campoCpf.getText().trim();
        String cep = campoCep.getText().trim();
        String extra = campoExtra.getText().trim();

        Pessoa pessoa;
        if (comboTipo.getSelectedIndex() == 0) {
            pessoa = new Membro(extra, dados.proximoIdPessoa, nome, idade, cpf, cep);
        } else {
            pessoa = new Funcionario(extra, dados.proximoIdPessoa, nome, idade, cpf, cep);
        }
        dados.proximoIdPessoa++;

        dados.gestaoPessoas.cadastrarPessoa(pessoa); // método original
        dados.pessoas.add(pessoa);                   // cópia para a tabela

        atualizarTabela();
        limparCampos();
        JOptionPane.showMessageDialog(this, "Pessoa cadastrada com sucesso!");
    }

    private void salvarAlteracoes() {
        if (pessoaSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Clique em uma pessoa da tabela para editar.");
            return;
        }
        if (!camposValidos()) {
            return;
        }

        String nome = campoNome.getText().trim();
        int idade = Integer.parseInt(campoIdade.getText().trim());
        String cpf = campoCpf.getText().trim();
        String cep = campoCep.getText().trim();
        String extra = campoExtra.getText().trim();
        Integer id = pessoaSelecionada.getControle();

        // Chama o método de edição certo para cada tipo de pessoa
        if (pessoaSelecionada instanceof Membro) {
            dados.gestaoPessoas.editarCadastroMembro(pessoaSelecionada, id, nome, idade, cpf, cep, extra);
        } else {
            dados.gestaoPessoas.editarCadastroFuncionario(pessoaSelecionada, id, nome, idade, cpf, cep, extra);
        }

        atualizarTabela();
        limparCampos();
        JOptionPane.showMessageDialog(this, "Cadastro editado com sucesso!");
    }

    // ---------------------------------------------------------------
    // Métodos de apoio
    // ---------------------------------------------------------------

    // Chamado sempre que a tela abre: recarrega a tabela e limpa o formulário
    public void atualizar() {
        atualizarTabela();
        limparCampos();
    }

    // Apaga todas as linhas da tabela e preenche de novo com a lista atual
    private void atualizarTabela() {
        modeloTabela.setRowCount(0);

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

            modeloTabela.addRow(new Object[]{
                pessoa.getControle(), tipo, pessoa.getNome(), pessoa.getIdade(),
                pessoa.getCpf(), pessoa.getCep(), extra
            });
        }
    }

    private void preencherFormulario() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            return;
        }

        pessoaSelecionada = dados.pessoas.get(linha);

        campoNome.setText(pessoaSelecionada.getNome());
        campoIdade.setText(String.valueOf(pessoaSelecionada.getIdade()));
        campoCpf.setText(pessoaSelecionada.getCpf());
        campoCep.setText(pessoaSelecionada.getCep());

        if (pessoaSelecionada instanceof Membro) {
            comboTipo.setSelectedIndex(0);
            campoExtra.setText(((Membro) pessoaSelecionada).getPreferencia());
        } else {
            comboTipo.setSelectedIndex(1);
            campoExtra.setText(((Funcionario) pessoaSelecionada).getNumeroRegistro());
        }

        comboTipo.setEnabled(false); // o tipo de uma pessoa já cadastrada não muda
    }

    private void limparCampos() {
        pessoaSelecionada = null;
        tabela.clearSelection();
        comboTipo.setEnabled(true);
        campoNome.setText("");
        campoIdade.setText("");
        campoCpf.setText("");
        campoCep.setText("");
        campoExtra.setText("");
    }

    private void atualizarRotuloExtra() {
        if (comboTipo.getSelectedIndex() == 0) {
            rotuloExtra.setText("Preferência");
        } else {
            rotuloExtra.setText("Número de registro");
        }
    }

    private boolean camposValidos() {
        if (campoNome.getText().trim().isEmpty() || campoCpf.getText().trim().isEmpty()
                || campoCep.getText().trim().isEmpty() || campoExtra.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return false;
        }

        try {
            int idade = Integer.parseInt(campoIdade.getText().trim());
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
}
