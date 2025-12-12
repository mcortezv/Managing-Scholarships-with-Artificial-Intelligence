/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.styles;
import javax.swing.*;

/**
 * The type Label.
 *
 * @author Cortez, Manuel;
 */
public class Label extends JLabel {

    /**
     * Instantiates a new Label.
     *
     * @param texto the texto
     */
    public Label(String texto) {
        super(texto);
        setFont(Style.LABEL_FONT);
        setForeground(Style.TEXT_COLOR);
    }
}