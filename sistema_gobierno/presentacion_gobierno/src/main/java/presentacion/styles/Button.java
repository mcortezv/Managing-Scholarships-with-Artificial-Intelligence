/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.styles;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Button.
 *
 * @author Cortez, Manuel;
 */
public class Button extends JButton {
    private static final int CORNER_RADIUS = 8;
    private boolean hovered = false;

    /**
     * Instantiates a new Button.
     *
     * @param text the text
     */
    public Button(String text) {
        super(text);
        setFont(Style.BUTTON_FONT);
        setForeground(Style.BUTTON_TEXT_COLOR);
        setBackground(Style.INPUT_COLOR);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 26, 10, 26));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { hovered = true;  repaint(); }
            @Override public void mouseExited (MouseEvent e) { hovered = false; repaint(); }
        });
        setUI(new BasicButtonUI());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(hovered ? Style.BUTTON_COLOR_HOVER : Style.BUTTON_COLOR);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                CORNER_RADIUS, CORNER_RADIUS);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    public void updateUI() {
        setUI(new BasicButtonUI());
    }

    /**
     * Sets button size.
     *
     * @param w the w
     * @param h the h
     */
    public void setButtonSize(int w, int h) {
        super.setPreferredSize(new Dimension(w, h));
        super.setMinimumSize(new Dimension(w, h));
        super.setMaximumSize(new Dimension(w, h));
    }

}