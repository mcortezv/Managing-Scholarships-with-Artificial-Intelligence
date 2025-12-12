package presentacion.login;
import presentacion.coordinacion.MainFrame;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.styles.ImgPanel;
import presentacion.styles.Panel;
import javax.swing.*;
import presentacion.styles.Button;
import presentacion.styles.Style;
import presentacion.styles.Label;
import java.awt.*;

/**
 * Panel Hub
 *
 * @author Cortez, Manuel;
 */
public class HubPanel extends Panel {
    private JPanel navbar;
    private ImgPanel imgPanel;
    private Button btnCerrarSesion;
    private Label titulo;
    private Button btnEvaluar;
    private Button btnModificar;

    private ICoordinadorAplicacion coordinadorAplicacion;

    /**
     * Instantiates a new Hub panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public HubPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
        southPanel.setVisible(false);
        navbar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(76, 29, 149),
                        0, getHeight(), new Color(124, 58, 237)
                );
                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(192, 132, 252, 150));
                g2.fillRect(0, getHeight() - 3, getWidth(), 3);
            }
        };
        navbar.setPreferredSize(new Dimension(1500, 85));
        navbar.setOpaque(false);
        navbar.setLayout(new BorderLayout());

        imgPanel = new ImgPanel("/assets/logo.png");
        imgPanel.setPreferredSize(new Dimension(140, 55));
        imgPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        btnCerrarSesion = new Button("Cerrar Sesión");
        btnCerrarSesion.setButtonSize(180, 55);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        leftPanel.setOpaque(false);
        leftPanel.add(imgPanel);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        rightPanel.setOpaque(false);
        rightPanel.add(btnCerrarSesion);

        navbar.add(leftPanel, BorderLayout.WEST);
        navbar.add(rightPanel, BorderLayout.EAST);
        add(navbar, BorderLayout.NORTH);


        JPanel contentContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(167, 139, 250, 25));
                g2.fillOval(-120, 120, 400, 400);
                g2.setColor(new Color(124, 58, 237, 18));
                g2.fillOval(getWidth() - 350, 180, 450, 450);
            }
        };
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
        contentContainer.setOpaque(false);
        contentContainer.add(Box.createVerticalStrut(35));


        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new BoxLayout(tituloPanel, BoxLayout.Y_AXIS));
        tituloPanel.setOpaque(false);
        tituloPanel.setMaximumSize(new Dimension(900, 130));

        JLabel iconoTitulo = new JLabel("📋");
        iconoTitulo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 64));
        iconoTitulo.setAlignmentX(CENTER_ALIGNMENT);
        tituloPanel.add(iconoTitulo);

        tituloPanel.add(Box.createVerticalStrut(15));

        titulo = new Label("Evaluación de Solicitudes");
        titulo.setFont(Style.TITLE_FONT.deriveFont(Font.BOLD, 50f));
        titulo.setForeground(new Color(233, 213, 255));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        tituloPanel.add(titulo);

        Label subtitulo = new Label("Seleccione una opción para comenzar");
        subtitulo.setFont(Style.LABEL_FONT.deriveFont(24f));
        subtitulo.setForeground(new Color(220, 200, 255));
        subtitulo.setAlignmentX(CENTER_ALIGNMENT);

        tituloPanel.add(Box.createVerticalStrut(10));
        tituloPanel.add(subtitulo);

        contentContainer.add(tituloPanel);
        contentContainer.add(Box.createVerticalStrut(40));

        JPanel main = new JPanel(new FlowLayout(FlowLayout.CENTER, 35, 0));
        main.setOpaque(false);


        JPanel cardEvaluar = crearCard("✓", "Evaluar",
                "Evaluar nuevas solicitudes",
                new Color(192, 132, 252));
        btnEvaluar = new Button("Evaluar Solicitudes");
        btnEvaluar.setPreferredSize(new Dimension(250, 55));
        cardEvaluar.add(Box.createVerticalStrut(15));
        cardEvaluar.setPreferredSize(new Dimension(450, 380));
        cardEvaluar.add(btnEvaluar);

        JPanel cardModificar = crearCard("✏️", "Modificar",
                "Modificar evaluaciones existentes",
                new Color(167, 139, 250));
        btnModificar = new Button("Modificar Resoluciones");
        btnModificar.setPreferredSize(new Dimension(250, 55));
        cardModificar.setPreferredSize(new Dimension(450, 380));
        cardModificar.add(Box.createVerticalStrut(15));
        cardModificar.add(btnModificar);

        main.add(cardEvaluar);
        main.add(cardModificar);

        contentContainer.add(main);
        contentContainer.add(Box.createVerticalStrut(35));


        JPanel heroContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint shadow = new GradientPaint(
                        0, 0, new Color(0, 0, 0, 40),
                        0, 20, new Color(0, 0, 0, 0)
                );
                g2.setPaint(shadow);
                g2.fillRect(0, 0, getWidth(), 20);
            }
        };
        heroContainer.setOpaque(false);
        heroContainer.setLayout(new BorderLayout());
        heroContainer.setMaximumSize(new Dimension(1500, 900));

        contentContainer.add(heroContainer);
        centralPanel.add(contentContainer);

        btnCerrarSesion.addActionListener(e -> coordinadorAplicacion.cerrarSesion());
        btnModificar.addActionListener(e -> coordinadorAplicacion.iniciarModificarConvocatoria());
        btnEvaluar.addActionListener(e -> coordinadorAplicacion.iniciarEvaluarConvocatoria());
    }


    private JPanel crearCard(String emoji, String titulo, String descripcion, Color accentColor) {

        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 45));
                g2.fillRoundRect(4, 4, getWidth() - 4, getHeight() - 4, 25, 25);
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(54, 22, 110),
                        0, getHeight(), new Color(76, 29, 149)
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, 25, 25);
                g2.setColor(accentColor);
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(2, 2, getWidth() - 8, getHeight() - 8, 25, 25);
                g2.fillRoundRect(0, 0, getWidth() - 4, 7, 25, 25);
            }
        };

        card.setOpaque(false);
        card.setPreferredSize(new Dimension(450, 470));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));

        JLabel iconoLabel = new JLabel(emoji);
        iconoLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        iconoLabel.setAlignmentX(CENTER_ALIGNMENT);
        card.add(iconoLabel);
        card.add(Box.createVerticalStrut(18));

        Label tituloLabel = new Label(titulo);
        tituloLabel.setFont(Style.SUBTITLE_FONT.deriveFont(Font.BOLD, 36f));
        tituloLabel.setForeground(accentColor);
        tituloLabel.setAlignmentX(CENTER_ALIGNMENT);
        card.add(tituloLabel);
        card.add(Box.createVerticalStrut(12));

        Label descLabel = new Label(descripcion);
        descLabel.setFont(Style.LABEL_FONT.deriveFont(18f));
        descLabel.setForeground(new Color(230, 230, 255));
        descLabel.setAlignmentX(CENTER_ALIGNMENT);
        card.add(descLabel);

        return card;
    }
}
