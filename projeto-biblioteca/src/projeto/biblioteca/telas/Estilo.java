package projeto.biblioteca.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Ajustes de aparência que a aba Design do NetBeans não consegue fazer.
 *
 * Cores, fontes, bordas e tamanhos dos componentes agora ficam nas
 * propriedades de cada componente (aba Design > janela Properties).
 * Aqui sobra só o que depende de código: efeito do mouse, cursor
 * e o cabeçalho/células da tabela.
 */
public class Estilo {

    // Mãozinha no cursor quando o mouse passa por cima dos botões
    public static void aplicarCursorMao(JButton... botoes) {
        for (JButton botao : botoes) {
            botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    // Botão escurece quando o mouse passa por cima (usa a cor definida na aba Design)
    public static void aplicarEfeitoHover(JButton... botoes) {
        aplicarCursorMao(botoes);
        for (JButton botao : botoes) {
            Color cor = botao.getBackground();
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
        }
    }

    // Centraliza o texto das células, pinta o cabeçalho de azul
    // e deixa branco o fundo da área de rolagem da tabela
    public static void estilizarTabela(JTable tabela, JScrollPane rolagem) {
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setDefaultRenderer(Object.class, centralizado);

        JTableHeader cabecalho = tabela.getTableHeader();
        cabecalho.setFont(Cores.FONTE_NEGRITO);
        cabecalho.setBackground(Cores.AZUL);
        cabecalho.setForeground(Color.WHITE);
        cabecalho.setPreferredSize(new Dimension(0, 36));

        rolagem.getViewport().setBackground(Color.WHITE);
    }
}
