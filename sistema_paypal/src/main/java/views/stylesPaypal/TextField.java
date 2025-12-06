package views.stylesPaypal;
import javax.swing.*;
import java.awt.*;

public class TextField extends JTextField {

    public TextField(int columns) {
        super(columns);
        setFont(Style.INPUT_FONT);
        setBackground(Style.INPUT_COLOR);
        setForeground(Style.INPUT_TEXT_COLOR);
        setCaretColor(Color.BLACK);
        setMaximumSize(new Dimension(600, 60));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        g2d.setColor(new Color(0, 0, 0, 50));
        g2d.fillRoundRect(2, 2, width - 4, height - 4, 12, 12);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width - 4, height - 4, 12, 12);
        g2d.setColor(new Color(90, 90, 90)); // gris
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawRoundRect(0, 0, width - 4, height - 4, 12, 12);
        g2d.dispose();
        super.paintComponent(g);
    }
}
