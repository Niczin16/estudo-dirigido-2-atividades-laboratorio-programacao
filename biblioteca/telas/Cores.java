package projeto.biblioteca.telas;

import java.awt.Color;
import java.awt.Font;

/**
 * Guarda todas as cores e fontes do sistema em um só lugar.
 * Para mudar a aparência do programa, basta mudar os valores aqui.
 */
public class Cores {

    // Menu lateral
    public static final Color MENU_FUNDO       = new Color(30, 41, 59);    // azul-marinho escuro
    public static final Color MENU_SELECIONADO = new Color(51, 65, 85);    // um pouco mais claro
    public static final Color MENU_TEXTO       = new Color(203, 213, 225); // cinza claro

    // Área das telas
    public static final Color FUNDO  = new Color(241, 245, 249); // cinza bem claro
    public static final Color CARTAO = Color.WHITE;
    public static final Color BORDA  = new Color(226, 232, 240);

    // Cores dos botões e destaques
    public static final Color AZUL        = new Color(37, 99, 235);
    public static final Color AZUL_CLARO  = new Color(219, 234, 254); // linha selecionada na tabela
    public static final Color VERDE       = new Color(22, 163, 74);
    public static final Color VERMELHO    = new Color(220, 38, 38);
    public static final Color AMARELO     = new Color(245, 158, 11);
    public static final Color CINZA       = new Color(100, 116, 139);

    // Textos
    public static final Color TEXTO       = new Color(15, 23, 42);
    public static final Color TEXTO_CLARO = new Color(100, 116, 139);

    // Fontes
    public static final Font FONTE_TITULO    = new Font("Segoe UI", Font.BOLD, 26);
    public static final Font FONTE_SUBTITULO = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONTE_NORMAL    = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONTE_NEGRITO   = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONTE_ROTULO    = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FONTE_NUMERO    = new Font("Segoe UI", Font.BOLD, 40);
}
