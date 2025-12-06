package views.panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import views.stylesPaypal.*;
import views.stylesPaypal.Button;
import views.stylesPaypal.Label;
import views.stylesPaypal.TextField;
import views.stylesPaypal.PasswordField;

public class PanelInicioSesionPaypal extends JPanel {

    private TextField campoCorreo;
    private PasswordField campoPassword;
    private Button btnIngresar;
    private JButton btnVolver;
    private final Color PAYPAL_BLUE_CARD = new Color(108, 160, 252);
    private final Color PAYPAL_BLUE_BTN  = new Color(28, 100, 238);
    private final Color WHITE = Color.WHITE;

    public PanelInicioSesionPaypal() {
        startComponents();
    }

    private void startComponents() {
        this.setLayout(new GridBagLayout());
        this.setBackground(WHITE);
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        btnVolver = new JButton("←");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnVolver.setForeground(new Color(50, 50, 50));
        btnVolver.setBorderPainted(false);
        btnVolver.setContentAreaFilled(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel lblLogo = new JLabel("PayPal");
        lblLogo.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 30));
        lblLogo.setForeground(new Color(0, 48, 135)); // Azul oscuro logo
        lblLogo.setBorder(new EmptyBorder(0, 0, 0, 20));

        headerPanel.add(btnVolver, BorderLayout.WEST);
        headerPanel.add(lblLogo, BorderLayout.EAST);

        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0; gbcHeader.gridy = 0;
        gbcHeader.weightx = 1.0;
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;
        gbcHeader.anchor = GridBagConstraints.NORTH;
        gbcHeader.insets = new Insets(10, 10, 0, 10);
        this.add(headerPanel, gbcHeader);


        RoundedPanel cardPanel = new RoundedPanel(20, PAYPAL_BLUE_CARD);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(new EmptyBorder(30, 40, 40, 40));

        Label lblTitulo = new Label("Inicio de Sesión PayPal");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardPanel.add(lblTitulo);
        cardPanel.add(Box.createVerticalStrut(40));

        campoCorreo = new TextField(20);
        estilizarCampoBlanco(campoCorreo); // Método auxiliar para forzar color blanco
        cardPanel.add(crearBloqueInput("Correo o teléfono asociado:", campoCorreo));
        cardPanel.add(Box.createVerticalStrut(20));

        campoPassword = new PasswordField(20);
        estilizarCampoBlanco(campoPassword);
        cardPanel.add(crearBloqueInput("Contraseña:", campoPassword));
        cardPanel.add(Box.createVerticalStrut(40));


        btnIngresar = new Button("Ingresar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(PAYPAL_BLUE_BTN);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Más redondeado
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btnIngresar.setForeground(WHITE);
        btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIngresar.setMaximumSize(new Dimension(200, 45));

        JPanel btnWrapper = new JPanel();
        btnWrapper.setOpaque(false);
        btnWrapper.add(btnIngresar);
        cardPanel.add(btnWrapper);

        GridBagConstraints gbcCard = new GridBagConstraints();
        gbcCard.gridx = 0; gbcCard.gridy = 1;
        gbcCard.weightx = 1.0; gbcCard.weighty = 1.0;
        gbcCard.anchor = GridBagConstraints.CENTER;
        this.add(cardPanel, gbcCard);
    }


    private JPanel crearBloqueInput(String texto, JComponent campo) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PAYPAL_BLUE_CARD);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Label lbl = new Label(texto);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        lbl.setForeground(WHITE); // Texto blanco sobre fondo azul
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        campo.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(lbl);
        panel.add(Box.createVerticalStrut(5));
        panel.add(campo);
        return panel;
    }


    private void estilizarCampoBlanco(JComponent campo) {
        campo.setBackground(Color.WHITE);
        campo.setForeground(Color.DARK_GRAY);
    }

    public void addIngresarListener(ActionListener listener) {
        btnIngresar.addActionListener(listener);
    }

    public void addVolverListener(ActionListener listener) {
        btnVolver.addActionListener(listener);
    }


    public String[] getDatosLogin() {
        String correo = campoCorreo.getText();
        String pass = new String(campoPassword.getPassword());

        if (correo.trim().isEmpty() || pass.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese correo y contraseña.",
                    "Datos requeridos",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return new String[]{correo, pass};
    }

    static class RoundedPanel extends JPanel {
        private final int cornerRadius;
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
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }
    }
}