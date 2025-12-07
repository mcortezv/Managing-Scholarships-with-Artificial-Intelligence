package presentacion.styles;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

/**
 *
 * @author Cortez, Manuel;
 */
public class ComboBox<T> extends JComboBox<T> {

    public ComboBox(T[] items) {
        super(items);
        setFont(Style.LABEL_FONT);
        setMaximumSize(new Dimension(600, 60));
        setRenderer(new MyComboBoxRenderer<>());
        setUI(new ModernComboBoxUI());
        setBackground(Style.INPUT_COLOR);
        setForeground(Color.DARK_GRAY);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0, 0, 0, 50));
        g2d.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 12, 12);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, 12, 12);
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawRoundRect(0, 0, getWidth() - 4, getHeight() - 4, 12, 12);
        g2d.dispose();
        super.paintComponent(g);
    }

    public void add(T beca) {

    }

    private static class MyComboBoxRenderer<T> extends JLabel implements ListCellRenderer<T> {
        public MyComboBoxRenderer() {
            setOpaque(true);
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends T> list, T value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText(value == null ? "" : value.toString());
            if (isSelected) {
                setBackground(Color.LIGHT_GRAY);
                setForeground(Color.BLACK);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.DARK_GRAY);
            }
            list.setSelectionBackground(Color.WHITE);
            list.setSelectionForeground(Color.BLACK);
            return this;
        }
    }

    private static class ModernComboBoxUI extends BasicComboBoxUI {

        @Override
        protected JButton createArrowButton() {
            JButton arrow = new JButton("\u25BC");
            arrow.setBorder(BorderFactory.createEmptyBorder());
            arrow.setContentAreaFilled(false);
            arrow.setForeground(Color.DARK_GRAY);
            return arrow;
        }

        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup popup = new BasicComboPopup(comboBox) {
                @Override
                protected JScrollPane createScroller() {
                    JScrollPane scroll = new JScrollPane(list,
                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    scroll.getVerticalScrollBar().setUI(new ModernScrollBarUI());
                    scroll.setBorder(BorderFactory.createEmptyBorder());
                    return scroll;
                }
            };
            return popup;
        }
    }

    private static class ModernScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            thumbColor = Color.LIGHT_GRAY;
            trackColor = Color.WHITE;
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton jbutton = new JButton();
            jbutton.setPreferredSize(new Dimension(0, 0));
            jbutton.setMinimumSize(new Dimension(0, 0));
            jbutton.setMaximumSize(new Dimension(0, 0));
            return jbutton;
        }
    }
}
