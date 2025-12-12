package presentacion.login;
import dtoGobierno.EvaluadorLoginDTO;
import presentacion.coordinacion.MainFrame;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.styles.*;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.styles.Panel;
import presentacion.styles.TextField;
import javax.swing.*;
import java.awt.*;

/**
 * Panel de inicio de sesión
 *
 * @author Cortez, Manuel
 */
public class IniciarSesionPanel extends Panel {
    private Label titulo;
    private ImgPanel img;
    private Label lblUsuario;
    private TextField txtUsuario;
    private Label lblPassword;
    private PasswordField txtPassword;
    private Button btnIniciarSesion;
    private ICoordinadorAplicacion coordinadorAplicacion;

    /**
     * Instantiates a new Iniciar sesion panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public IniciarSesionPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
        southPanel.setVisible(false);

        JPanel mainContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(167, 139, 250, 20));
                g2.fillOval(-150, -150, 500, 500);

                g2.setColor(new Color(124, 58, 237, 15));
                g2.fillOval(getWidth() - 350, getHeight() - 350, 500, 500);
            }
        };
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setOpaque(false);
        mainContainer.setMaximumSize(new Dimension(1500, 900));

        mainContainer.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        mainContainer.add(Box.createVerticalStrut(20));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new BoxLayout(tituloPanel, BoxLayout.Y_AXIS));
        tituloPanel.setOpaque(false);
        tituloPanel.setMaximumSize(new Dimension(700, 100));

        tituloPanel.add(Box.createVerticalStrut(10));

        titulo = new Label("Iniciar Sesión Evaluador");
        titulo.setFont(Style.TITLE_FONT.deriveFont(Font.BOLD, 62f));
        titulo.setForeground(new Color(230, 230, 250));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        tituloPanel.add(titulo);

        mainContainer.add(tituloPanel);
        mainContainer.add(Box.createVerticalStrut(25));

        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(0, 0, 0, 40));
                g2.fillRoundRect(5, 5, getWidth() - 5, getHeight() - 5, 30, 30);

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(76, 29, 149),
                        0, getHeight(), new Color(124, 58, 237)
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 30, 30);

                g2.setColor(new Color(192, 132, 252, 120));
                g2.setStroke(new BasicStroke(2.5f));
                g2.drawRoundRect(2, 2, getWidth() - 9, getHeight() - 9, 30, 30);
            }
        };

        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setOpaque(false);
        loginPanel.setMaximumSize(new Dimension(550, 580));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(35, 45, 35, 45));

        JPanel imgContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int centerX = getWidth() / 2;
                int centerY = getHeight() / 3;

                for (int i = 3; i > 0; i--) {
                    int alpha = 15 * i;
                    g2.setColor(new Color(167, 139, 250, alpha));
                    int size = 200 + (i * 25);
                    g2.fillOval(centerX - size / 2, centerY - size / 2, size, size);
                }
            }
        };

        imgContainer.setLayout(new BorderLayout());
        imgContainer.setOpaque(false);
        imgContainer.setMaximumSize(new Dimension(200, 240));
        imgContainer.setAlignmentX(CENTER_ALIGNMENT);

        img = new ImgPanel("/assets/usuario.png");
        img.setMaximumSize(new Dimension(100, 200));
        img.setAlignmentY(CENTER_ALIGNMENT);
        imgContainer.add(img, BorderLayout.CENTER);

        loginPanel.add(imgContainer);
        loginPanel.add(Box.createVerticalStrut(30));

        JPanel usuarioPanel = crearCampo("Usuario");
        loginPanel.add(usuarioPanel);
        loginPanel.add(Box.createVerticalStrut(10));

        txtUsuario = new TextField(1);
        txtUsuario.setMaximumSize(new Dimension(450, 60));
        txtUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(txtUsuario);
        loginPanel.add(Box.createVerticalStrut(22));

        JPanel passwordPanel = crearCampo("Contraseña");
        loginPanel.add(passwordPanel);
        loginPanel.add(Box.createVerticalStrut(10));

        txtPassword = new PasswordField(1);
        txtPassword.setMaximumSize(new Dimension(450, 60));
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(txtPassword);
        loginPanel.add(Box.createVerticalStrut(35));

        btnIniciarSesion = new Button("Iniciar Sesión");
        btnIniciarSesion.setAlignmentX(CENTER_ALIGNMENT);
        loginPanel.add(btnIniciarSesion);

        mainContainer.add(loginPanel);
        centralPanel.add(mainContainer);

        Runnable toggle = () -> {
            boolean habilitado = !txtUsuario.getText().trim().isEmpty() &&
                    txtPassword.getPassword().length > 0;
            btnIniciarSesion.setEnabled(habilitado);
        };

        javax.swing.event.DocumentListener dl = new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { toggle.run(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { toggle.run(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { toggle.run(); }
        };

        txtUsuario.getDocument().addDocumentListener(dl);
        txtPassword.getDocument().addDocumentListener(dl);
        txtPassword.addActionListener(e -> btnIniciarSesion.doClick());
        toggle.run();

        btnIniciarSesion.addActionListener(e -> {
            try {
                Long matricula = Long.parseLong(txtUsuario.getText());
                String contrasenia = txtPassword.getText();
                EvaluadorLoginDTO dto = new EvaluadorLoginDTO();
                dto.setMatricula(matricula);
                dto.setContrasenia(contrasenia);
                coordinadorAplicacion.iniciarSesion(dto);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame,
                        "La matrícula debe ser un número entero",
                        "Inicio de Sesión",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JPanel crearCampo(String texto) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(450, 35));

        Label label = new Label(texto);
        label.setFont(Style.LABEL_FONT.deriveFont(Font.BOLD, 20f));
        label.setForeground(new Color(230, 230, 250));

        panel.add(label);
        return panel;
    }
}
