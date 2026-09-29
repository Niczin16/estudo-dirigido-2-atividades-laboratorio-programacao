package projeto.biblioteca.telas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import projeto.biblioteca.EBook;
import projeto.biblioteca.Livro;
import projeto.biblioteca.LivroFisico;

/**
 * Tela "Gerir Livros": incluir, editar, remover, listar e buscar livros.
 */
public class PainelLivros extends JPanel {

    private Dados dados;

    // Livro que foi clicado na tabela (null quando nenhum está selecionado)
    private Livro livroSelecionado = null;

    // Campos do formulário
    private JComboBox<String> comboTipo = new JComboBox<>(new String[]{"E-Book", "Livro Físico"});
    private JTextField campoTitulo = Estilo.criarCampo();
    private JTextField campoAutor = Estilo.criarCampo();
    private JTextField campoPaginas = Estilo.criarCampo();
    private JTextField campoExtra = Estilo.criarCampo(); // tamanho (MB) ou peso (kg)
    private JLabel rotuloExtra = Estilo.criarRotulo("Tamanho do arquivo (MB)");
    private JTextField campoBusca = Estilo.criarCampo();

    // Tabela
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public PainelLivros(Dados dados) {
        this.dados = dados;

        setLayout(new BorderLayout(20, 20));
        setBackground(Cores.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        add(Estilo.criarCabecalho("Livros",
                "Cadastre, edite e remova E-Books e livros físicos."), BorderLayout.NORTH);
        add(criarFormulario(), BorderLayout.WEST);
        add(criarAreaTabela(), BorderLayout.CENTER);
    }

    // ---------------------------------------------------------------
    // Montagem da tela
    // ---------------------------------------------------------------

    private JScrollPane criarFormulario() {
        comboTipo.setFont(Cores.FONTE_NORMAL);
        comboTipo.setBackground(Cores.CARTAO);
        // Ao trocar o tipo, muda o texto do último campo
        comboTipo.addActionListener(e -> atualizarRotuloExtra());

        // Cada grupo tem o rótulo em cima e o campo embaixo
        JPanel campos = new JPanel(new GridLayout(0, 1, 0, 10));
        campos.setOpaque(false);
        campos.add(Estilo.criarGrupo(Estilo.criarRotulo("Tipo"), comboTipo));
        campos.add(Estilo.criarGrupo(Estilo.criarRotulo("Título"), campoTitulo));
        campos.add(Estilo.criarGrupo(Estilo.criarRotulo("Autor"), campoAutor));
        campos.add(Estilo.criarGrupo(Estilo.criarRotulo("Número de páginas"), campoPaginas));
        campos.add(Estilo.criarGrupo(rotuloExtra, campoExtra));

        // Botões
        JButton btnCadastrar = Estilo.criarBotao("Cadastrar", Cores.AZUL);
        JButton btnSalvar = Estilo.criarBotao("Salvar alterações", Cores.VERDE);
        JButton btnRemover = Estilo.criarBotao("Remover selecionado", Cores.VERMELHO);
        JButton btnLimpar = Estilo.criarBotao("Limpar", Cores.CINZA);

        btnCadastrar.addActionListener(e -> cadastrar());
        btnSalvar.addActionListener(e -> salvarAlteracoes());
        btnRemover.addActionListener(e -> remover());
        btnLimpar.addActionListener(e -> limparCampos());

        JPanel botoes = new JPanel(new GridLayout(0, 1, 0, 8));
        botoes.setOpaque(false);
        botoes.add(btnCadastrar);
        botoes.add(btnSalvar);
        botoes.add(btnRemover);
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
        // Barra de busca por ID, em cima da tabela
        campoBusca.setPreferredSize(new Dimension(90, 36));
        JButton btnBuscar = Estilo.criarBotao("Buscar", Cores.AZUL);
        btnBuscar.setPreferredSize(new Dimension(100, 36));
        btnBuscar.addActionListener(e -> buscar());

        JPanel barraBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        barraBusca.setOpaque(false);
        barraBusca.add(Estilo.criarRotulo("Buscar por ID:"));
        barraBusca.add(campoBusca);
        barraBusca.add(btnBuscar);

        // Tabela
        String[] colunas = {"ID", "Tipo", "Título", "Autor", "Páginas", "Tamanho / Peso"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        Estilo.estilizarTabela(tabela);
        tabela.getColumnModel().getColumn(0).setMaxWidth(50);

        // Ao clicar em uma linha, o livro vai para o formulário
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherFormulario();
            }
        });

        JPanel cartao = Estilo.criarCartao();
        cartao.add(barraBusca, BorderLayout.NORTH);
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

        String titulo = campoTitulo.getText().trim();
        String autor = campoAutor.getText().trim();
        int paginas = Integer.parseInt(campoPaginas.getText().trim());
        double valorExtra = lerDecimal(campoExtra.getText());

        Livro livro;
        if (comboTipo.getSelectedIndex() == 0) {
            livro = new EBook(valorExtra, dados.proximoIdLivro, titulo, autor, paginas);
        } else {
            livro = new LivroFisico(valorExtra, dados.proximoIdLivro, titulo, autor, paginas);
        }
        dados.proximoIdLivro++;

        dados.biblioteca.incluirLivro(livro); // método original
        dados.livros.add(livro);              // cópia para a tabela

        atualizarTabela();
        limparCampos();
        JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
    }

    private void salvarAlteracoes() {
        if (livroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Clique em um livro da tabela para editar.");
            return;
        }
        if (!camposValidos()) {
            return;
        }

        String titulo = campoTitulo.getText().trim();
        String autor = campoAutor.getText().trim();
        int paginas = Integer.parseInt(campoPaginas.getText().trim());
        double valorExtra = lerDecimal(campoExtra.getText());
        Integer id = livroSelecionado.getControle();

        // Chama o método de edição certo para cada tipo de livro
        if (livroSelecionado instanceof EBook) {
            dados.biblioteca.editarLivroEbook(livroSelecionado, id, autor, valorExtra, titulo, paginas);
        } else {
            dados.biblioteca.editarLivroFisico(livroSelecionado, id, autor, valorExtra, titulo, paginas);
        }

        atualizarTabela();
        limparCampos();
        JOptionPane.showMessageDialog(this, "Livro editado com sucesso!");
    }

    private void remover() {
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
            atualizarTabela();
            limparCampos();
        }
    }

    private void buscar() {
        int id;
        try {
            id = Integer.parseInt(campoBusca.getText().trim());
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
            tabela.setRowSelectionInterval(linha, linha);
            tabela.scrollRectToVisible(tabela.getCellRect(linha, 0, true));
        }
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

            modeloTabela.addRow(new Object[]{
                livro.getControle(), tipo, livro.getTitulo(), livro.getAutor(),
                livro.getNumeroPaginas(), extra
            });
        }
    }

    // Copia os dados do livro clicado para os campos do formulário
    private void preencherFormulario() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            return; // nenhuma linha selecionada
        }

        livroSelecionado = dados.livros.get(linha);

        campoTitulo.setText(livroSelecionado.getTitulo());
        campoAutor.setText(livroSelecionado.getAutor());
        campoPaginas.setText(String.valueOf(livroSelecionado.getNumeroPaginas()));

        if (livroSelecionado instanceof EBook) {
            comboTipo.setSelectedIndex(0);
            campoExtra.setText(String.valueOf(((EBook) livroSelecionado).getTamanhoArquivo()));
        } else {
            comboTipo.setSelectedIndex(1);
            campoExtra.setText(String.valueOf(((LivroFisico) livroSelecionado).getPeso()));
        }

        comboTipo.setEnabled(false); // o tipo de um livro já cadastrado não muda
    }

    private void limparCampos() {
        livroSelecionado = null;
        tabela.clearSelection();
        comboTipo.setEnabled(true);
        campoTitulo.setText("");
        campoAutor.setText("");
        campoPaginas.setText("");
        campoExtra.setText("");
    }

    private void atualizarRotuloExtra() {
        if (comboTipo.getSelectedIndex() == 0) {
            rotuloExtra.setText("Tamanho do arquivo (MB)");
        } else {
            rotuloExtra.setText("Peso (kg)");
        }
    }

    // Mesmas regras do menu do terminal: nada vazio e números maiores que zero
    private boolean camposValidos() {
        if (campoTitulo.getText().trim().isEmpty() || campoAutor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O título e o autor não podem ficar em branco.");
            return false;
        }

        try {
            int paginas = Integer.parseInt(campoPaginas.getText().trim());
            double valorExtra = lerDecimal(campoExtra.getText());

            if (paginas <= 0 || valorExtra <= 0) {
                JOptionPane.showMessageDialog(this, "Páginas e " + rotuloExtra.getText()
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
}
