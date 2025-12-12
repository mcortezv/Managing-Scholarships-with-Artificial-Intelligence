/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.styles;
import java.awt.*;

/**
 * The type Style.
 *
 * @author Cortez, Manuel;
 */
public class Style {
    /**
     * The constant DARK_MODE.
     */
    public static boolean DARK_MODE = true;
    /**
     * The constant PANEL_COLOR.
     */
    public static final Color PANEL_COLOR = DARK_MODE ? new Color( 23, 23, 23) : Color.WHITE;
    /**
     * The constant BACKGROUND_COLOR.
     */
    public static final Color BACKGROUND_COLOR = DARK_MODE ? new Color(60, 60, 60) : Color.WHITE;
    /**
     * The constant BUTTON_COLOR.
     */
    public static final Color BUTTON_COLOR = DARK_MODE ? new Color(0xF5F1E8) : new Color(33, 33, 33);
    /**
     * The constant BUTTON_TEXT_COLOR.
     */
    public static final Color BUTTON_TEXT_COLOR = DARK_MODE ? new Color(33, 33, 33) : new Color(235, 235, 235);
    /**
     * The constant BUTTON_COLOR_HOVER.
     */
    public static final Color BUTTON_COLOR_HOVER = DARK_MODE ? Color.LIGHT_GRAY : Color.DARK_GRAY;
    /**
     * The constant TEXT_COLOR.
     */
    public static final Color TEXT_COLOR = DARK_MODE ? new Color(235, 235, 235) : new Color(33, 33, 33);
    /**
     * The constant INPUT_COLOR.
     */
    public static final Color INPUT_COLOR = DARK_MODE ? new Color(0xF5F1E8) : new Color(255, 255, 255);
    /**
     * The constant INPUT_TEXT_COLOR.
     */
    public static final Color INPUT_TEXT_COLOR = DARK_MODE ? new Color(33, 33, 33) : new Color(33, 33, 33);

    /**
     * The constant TITLE_FONT.
     */
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 70);
    /**
     * The constant SUBTITLE_FONT.
     */
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.BOLD, 25);
    /**
     * The constant LABEL_FONT.
     */
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 20);
    /**
     * The constant INPUT_FONT.
     */
    public static final Font INPUT_FONT = new Font("Segoe UI", Font.PLAIN, 18);
    /**
     * The constant BUTTON_FONT.
     */
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 18);

    /**
     * The constant TOP_ESPACIO.
     */
    public static final int TOP_ESPACIO = 40;
    /**
     * The constant TITULO_ESPACIO.
     */
    public static final int TITULO_ESPACIO = 30;
    /**
     * The constant LBL_ESPACIO.
     */
    public static final int LBL_ESPACIO = 10;
    /**
     * The constant BLOQUE_ESPACIO.
     */
    public static final int BLOQUE_ESPACIO = 20;
}
