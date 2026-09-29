package projeto.biblioteca.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Métodos que criam componentes já com a aparência do sistema.
 * Assim cada tela não precisa repetir o mesmo código de cores e bordas.
 */
public class Estilo {

    // Título grande + frase explicativa, usado no topo de cada tela
    public static JPanel criarCabecalho(String titulo, String subtitulo) {
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(Cores.FONTE_TITULO);
        lblTitulo.setForeground(Cores.TEXTO);

        JLabel lblSubtitulo = new JLabel(subtitulo);
        lblSubtitulo.setFont(Cores.FONTE_SUBTITULO);
        lblSubtitulo.setForeground(Cores.TEXTO_CLARO);

        JPanel painel = new JPanel(new GridLayout(2, 1));
        painel.setOpaque(false); // deixa ver a cor de fundo da tela
        painel.add(lblTitulo);
        painel.add(lblSubtitulo);
        return painel;
    }

    // Caixa branca com borda fina, onde ficam o formulário e a tabela
    public static JPanel criarCartao() {
        JPanel cartao = new JPanel(new BorderLayout(10, 10));
        cartao.setBackground(Cores.CARTAO);
        cartao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)));
        return cartao;
    }

    // Texto pequeno em cima de cada campo
    public static JLabel criarRotulo(String texto) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setFont(Cores.FONTE_ROTULO);
        rotulo.setForeground(Cores.TEXTO_CLARO);
        return rotulo;
    }

    // Junta um rótulo e o seu campo em um bloco só (rótulo em cima, campo embaixo)
    public static JPanel criarGrupo(JLabel rotulo, JComponent campo) {
        JPanel grupo = new JPanel(new BorderLayout(0, 4));
        grupo.setOpaque(false);
        grupo.add(rotulo, BorderLayout.NORTH);
        grupo.add(campo, BorderLayout.CENTER);
        return grupo;
    }

    // Campo de texto com borda clara e espaço interno
    public static JTextField criarCampo() {
        JTextField campo = new JTextField();
        campo.setFont(Cores.FONTE_NORMAL);
        campo.setForeground(Cores.TEXTO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        return campo;
    }

    // Botão colorido com letra branca, que escurece quando o mouse passa por cima
    public static JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(Cores.FONTE_NEGRITO);
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setOpaque(true);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(160, 36));

        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(cor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(cor);
            }
        });
        return botao;
    }

    // Deixa a tabela com linhas altas, cabeçalho azul e sem edição direta nas células
    public static void estilizarTabela(JTable tabela) {
        tabela.setFont(Cores.FONTE_NORMAL);
        tabela.setForeground(Cores.TEXTO);
        tabela.setRowHeight(30);
        tabela.setShowVerticalLines(false);
        tabela.setGridColor(Cores.BORDA);
        tabela.setSelectionBackground(Cores.AZUL_CLARO);
        tabela.setSelectionForeground(Cores.TEXTO);
        tabela.setDefaultEditor(Object.class, null); // impede editar digitando na célula

        // Centraliza o texto de todas as células
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setDefaultRenderer(Object.class, centralizado);

        JTableHeader cabecalho = tabela.getTableHeader();
        cabecalho.setFont(Cores.FONTE_NEGRITO);
        cabecalho.setBackground(Cores.AZUL);
        cabecalho.setForeground(Color.WHITE);
        cabecalho.setPreferredSize(new Dimension(0, 36));
        cabecalho.setReorderingAllowed(false);
    }

    // Coloca o formulário dentro de uma barra de rolagem.
    // Em telas pequenas aparece a rolagem, em vez de os botões ficarem cortados.
    public static JScrollPane criarRolagemFormulario(JPanel cartao) {
        JScrollPane rolagem = new JScrollPane(cartao);
        rolagem.setBorder(null);
        rolagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rolagem.getVerticalScrollBar().setUnitIncrement(16); // rolagem mais rápida com a roda do mouse
        rolagem.setPreferredSize(new Dimension(300, 0));
        return rolagem;
    }

    // Coloca a tabela dentro de uma barra de rolagem com fundo branco
    public static JScrollPane criarRolagem(JTable tabela) {
        JScrollPane rolagem = new JScrollPane(tabela);
        rolagem.setBorder(BorderFactory.createLineBorder(Cores.BORDA));
        rolagem.getViewport().setBackground(Color.WHITE);
        return rolagem;
    }
}
