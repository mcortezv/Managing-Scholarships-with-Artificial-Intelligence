package presentacion.apelarResultado.panels;

import presentacion.styles.Button;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class RedaccionApelacion extends JDialog {

    private JTextArea txtAreaMotivo;
    private boolean confirmado = false;
    private final Color BACKGROUND_COLOR = new Color(30, 30, 30);
    private final Color CARD_COLOR = Color.WHITE;

    public RedaccionApelacion(Window parent) {
        super(parent, "Redacción de Apelación", ModalityType.APPLICATION_MODAL);
        startComponents();
        setSize(900, 600);
        setLocationRelativeTo(parent);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 900, 600, 20, 20));
    }

    private void startComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel btnBack = new JLabel("←", SwingConstants.CENTER);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 24));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(60, 60, 60));
        btnBack.setOpaque(true);
        btnBack.setPreferredSize(new Dimension(50, 50));
        btnBack.setBorder(BorderFactory.createLineBorder(new Color(60,60,60), 1));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                confirmado = false;
                dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 10, 0, 0);
        mainPanel.add(btnBack, gbc);

        JPanel cardPanel = new RoundedPanel(20, CARD_COLOR);
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setPreferredSize(new Dimension(600, 400));
        cardPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        JLabel lblTitulo = new JLabel("Realice su apelación:");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setForeground(Color.BLACK);

        GridBagConstraints gbcCard = new GridBagConstraints();
        gbcCard.gridx = 0; gbcCard.gridy = 0;
        gbcCard.weightx = 1.0;
        gbcCard.anchor = GridBagConstraints.CENTER;
        gbcCard.insets = new Insets(0, 0, 15, 0);
        cardPanel.add(lblTitulo, gbcCard);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(200, 200, 200));

        gbcCard.gridy = 1;
        gbcCard.fill = GridBagConstraints.HORIZONTAL;
        gbcCard.insets = new Insets(0, 20, 20, 20);
        cardPanel.add(separator, gbcCard);

        txtAreaMotivo = new JTextArea();
        txtAreaMotivo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtAreaMotivo.setForeground(new Color(80, 80, 80));
        txtAreaMotivo.setLineWrap(true);
        txtAreaMotivo.setWrapStyleWord(true);
        txtAreaMotivo.setBorder(null);

        JScrollPane scrollPane = new JScrollPane(txtAreaMotivo);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        gbcCard.gridy = 2;
        gbcCard.weighty = 1.0;
        gbcCard.fill = GridBagConstraints.BOTH;
        gbcCard.insets = new Insets(0, 10, 20, 10);
        cardPanel.add(scrollPane, gbcCard);

        Button btnEnviar = new Button("Enviar Petición");
        btnEnviar.setBackground(new Color(40, 40, 40));
        btnEnviar.setForeground(Color.WHITE);
        btnEnviar.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnEnviar.setPreferredSize(new Dimension(180, 40));
        btnEnviar.addActionListener(e -> {
            confirmado = true;
            dispose();
        });

        gbcCard.gridy = 3;
        gbcCard.weighty = 0;
        gbcCard.fill = GridBagConstraints.NONE;
        gbcCard.anchor = GridBagConstraints.CENTER;
        gbcCard.insets = new Insets(10, 0, 0, 0);
        cardPanel.add(btnEnviar, gbcCard);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 0, 60);
        mainPanel.add(cardPanel, gbc);

        this.setContentPane(mainPanel);
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public String getMotivo() {
        return txtAreaMotivo.getText();
    }

    static class RoundedPanel extends JPanel {
        private int cornerRadius = 15;
        private final Color backgroundColor;

        public RoundedPanel(int radius, Color bgColor) {
            super();
            this.cornerRadius = radius;
            this.backgroundColor = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            graphics.setColor(backgroundColor);
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        }
    }
}