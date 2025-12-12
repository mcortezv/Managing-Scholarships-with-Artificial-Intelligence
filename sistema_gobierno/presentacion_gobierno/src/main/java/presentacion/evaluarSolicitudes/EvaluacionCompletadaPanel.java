package presentacion.evaluarSolicitudes;
import dtoGobierno.ResolucionDTO;
import presentacion.coordinacion.MainFrame;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.styles.Button;
import presentacion.styles.ImgPanel;
import presentacion.styles.Label;
import presentacion.styles.Panel;
import presentacion.styles.Style;
import javax.swing.*;
import java.awt.*;


/**
 * The type Evaluacion completada panel.
 *
 * @autor Cortez, Manuel;
 */
public class EvaluacionCompletadaPanel extends Panel {
    private Label titulo;
    private ImgPanel imgPanel;
    private Label subTituloH;
    private Label subTituloL;
    private Button btnAceptar;
    private Button btnModificar;
    private ICoordinadorAplicacion coordinadorAplicacion;

    /**
     * Instantiates a new Evaluacion completada panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public EvaluacionCompletadaPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
        southPanel.setVisible(false);
        JPanel container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(100, 149, 237, 30));
                g2.fillOval(-100, -100, 400, 400);
                g2.fillOval(getWidth() - 300, getHeight() - 300, 400, 400);
            }
        };
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);
        container.setMaximumSize(new Dimension(1500, 900));
        container.add(Box.createVerticalStrut(60));

        JPanel imgContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                for (int i = 3; i > 0; i--) {
                    int alpha = 20 * i;
                    g2.setColor(new Color(46, 204, 113, alpha));
                    int size = 280 + (i * 30);
                    g2.fillOval(centerX - size/2, centerY - size/2, size, size);
                }
            }
        };
        imgContainer.setLayout(new BorderLayout());
        imgContainer.setOpaque(false);
        imgContainer.setMaximumSize(new Dimension(350, 350));
        imgContainer.setAlignmentX(CENTER_ALIGNMENT);

        imgPanel = new ImgPanel("/assets/check.png");
        imgPanel.setMaximumSize(new Dimension(280, 280));
        imgPanel.setAlignmentY(CENTER_ALIGNMENT);
        imgContainer.add(imgPanel, BorderLayout.CENTER);

        container.add(imgContainer);
        container.add(Box.createVerticalStrut(30));

        titulo = new Label("Evaluación Completada");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        container.add(titulo);
        container.add(Box.createVerticalStrut(20));

        JPanel subtitulosPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 20));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        subtitulosPanel.setLayout(new BoxLayout(subtitulosPanel, BoxLayout.Y_AXIS));
        subtitulosPanel.setOpaque(false);
        subtitulosPanel.setMaximumSize(new Dimension(700, 120));
        subtitulosPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        subTituloH = new Label("Todas las solicitudes de esta convocatoria han sido evaluadas");
        subTituloH.setFont(Style.LABEL_FONT.deriveFont(20f));
        subTituloH.setForeground(new Color(220, 220, 220));
        subTituloH.setAlignmentX(CENTER_ALIGNMENT);

        subTituloL = new Label("o el límite de becas aceptadas ha sido alcanzado.");
        subTituloL.setFont(Style.LABEL_FONT.deriveFont(20f));
        subTituloL.setForeground(new Color(180, 180, 180));
        subTituloL.setAlignmentX(CENTER_ALIGNMENT);

        subtitulosPanel.add(subTituloH);
        subtitulosPanel.add(Box.createVerticalStrut(8));
        subtitulosPanel.add(subTituloL);

        container.add(subtitulosPanel);
        container.add(Box.createVerticalStrut(40));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        buttonPanel.setMaximumSize(new Dimension(500, 90));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnAceptar = new Button("Aceptar");
        btnAceptar.setAlignmentX(CENTER_ALIGNMENT);

        btnModificar = new Button("Modificar");
        btnModificar.setAlignmentX(CENTER_ALIGNMENT);

        buttonPanel.add(btnAceptar);
        buttonPanel.add(btnModificar);
        container.add(buttonPanel);
        centralPanel.add(container);

        btnAceptar.addActionListener(e -> {
            coordinadorAplicacion.volverHub();
        });

        btnModificar.addActionListener(e -> {
            coordinadorAplicacion.iniciarModificarConvocatoria();
        });
    }

    /**
     * Sets resolucion.
     *
     * @param resolucion the resolucion
     */
    public void setResolucion(ResolucionDTO resolucion) {
        if (resolucion != null) {
            String decision = resolucion.getDecision();
            if (decision.equals("ACEPTADA")) {
                subTituloH.setText("La solicitud ha sido ACEPTADA exitosamente");
                subTituloH.setForeground(new Color(46, 204, 113));
            } else if (decision.equals("RECHAZADA")) {
                subTituloH.setText("La solicitud ha sido RECHAZADA");
                subTituloH.setForeground(new Color(231, 76, 60));
            } else {
                subTituloH.setText("↩La solicitud ha sido DEVUELTA");
                subTituloH.setForeground(new Color(241, 196, 15));
            }

            subTituloL.setText("El estudiante ha sido notificado del resultado");
        }
    }
}
