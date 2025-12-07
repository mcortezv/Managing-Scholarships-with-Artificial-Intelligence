/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.styles;
import java.awt.*;

/**
 *
 * @author Cortez, Manuel;
 */
public class Style {
    public static boolean DARK_MODE = true;
    public static final Color PANEL_COLOR = DARK_MODE ? new Color( 23, 23, 23) : Color.WHITE;
    public static final Color BACKGROUND_COLOR = DARK_MODE ? new Color(60, 60, 60) : Color.WHITE;
    public static final Color BUTTON_COLOR = DARK_MODE ? new Color(0xF5F1E8) : new Color(33, 33, 33);
    public static final Color BUTTON_TEXT_COLOR = DARK_MODE ? new Color(33, 33, 33) : new Color(235, 235, 235);
    public static final Color BUTTON_COLOR_HOVER = DARK_MODE ? Color.LIGHT_GRAY : Color.DARK_GRAY;
    public static final Color TEXT_COLOR = DARK_MODE ? new Color(235, 235, 235) : new Color(33, 33, 33);
    public static final Color INPUT_COLOR = DARK_MODE ? new Color(0xF5F1E8) : new Color(255, 255, 255);
    public static final Color INPUT_TEXT_COLOR = DARK_MODE ? new Color(33, 33, 33) : new Color(33, 33, 33);

    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 70);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.BOLD, 25);
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 20);
    public static final Font INPUT_FONT = new Font("Segoe UI", Font.PLAIN, 18);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 18);

    public static final int TOP_ESPACIO = 40;
    public static final int TITULO_ESPACIO = 30;
    public static final int LBL_ESPACIO = 10;
    public static final int BLOQUE_ESPACIO = 20;
}