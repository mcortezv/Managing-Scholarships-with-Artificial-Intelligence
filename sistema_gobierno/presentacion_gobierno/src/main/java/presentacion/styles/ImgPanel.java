/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.styles;
import javax.swing.*;
import java.awt.*;

/**
 * The type Img panel.
 *
 * @author Cortez, Manuel;
 */
public class ImgPanel extends JPanel {
    private final Image imagen;

    /**
     * Instantiates a new Img panel.
     *
     * @param path the path
     */
    public ImgPanel(String path) {
        this.imagen = new ImageIcon(
                getClass().getResource(path)
        ).getImage();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        g2d.dispose();
    }
}
