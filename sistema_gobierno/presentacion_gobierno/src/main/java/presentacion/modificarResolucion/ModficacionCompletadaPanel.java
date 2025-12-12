package presentacion.modificarResolucion;
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
 * The type Modficacion completada panel.
 *
 * @author Cortez, Manuel;
 */
public class ModficacionCompletadaPanel extends Panel {
    private Label titulo;
    private ImgPanel imgPanel;
    private Label subTituloH;
    private Label subTituloL;
    private Button btnAceptar;
    private ICoordinadorAplicacion coordinadorAplicacion;

    /**
     * Instantiates a new Modficacion completada panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public ModficacionCompletadaPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
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
                g2.setColor(new Color(72, 219, 251, 20));
                g2.fillOval(getWidth() - 200, -50, 350, 350);
                g2.setColor(new Color(255, 159, 64, 20));
                g2.fillOval(-100, getHeight() - 200, 350, 350);
            }
        };
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);
        container.setMaximumSize(new Dimension(1500, 900));
        container.add(Box.createVerticalStrut(80));

        JPanel imgContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                for (int i = 4; i > 0; i--) {
                    int alpha = 15 * i;
                    g2.setColor(new Color(52, 152, 219, alpha));
                    int size = 300 + (i * 40);
                    g2.fillOval(centerX - size/2, centerY - size/2, size, size);
                }
            }
        };
        imgContainer.setLayout(new BorderLayout());
        imgContainer.setOpaque(false);
        imgContainer.setMaximumSize(new Dimension(380, 380));
        imgContainer.setAlignmentX(CENTER_ALIGNMENT);

        imgPanel = new ImgPanel("/assets/check.png");
        imgPanel.setMaximumSize(new Dimension(300, 300));
        imgPanel.setAlignmentY(CENTER_ALIGNMENT);
        imgContainer.add(imgPanel, BorderLayout.CENTER);

        container.add(imgContainer);
        container.add(Box.createVerticalStrut(35));

        titulo = new Label("Modificación Completada");
        titulo.setFont(Style.TITLE_FONT.deriveFont(Font.BOLD, 44f));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        container.add(titulo);
        container.add(Box.createVerticalStrut(25));

        JPanel infoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(45, 52, 54, 150),
                        0, getHeight(), new Color(55, 62, 64, 150)
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(new Color(52, 152, 219, 80));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 25, 25);
            }
        };
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setMaximumSize(new Dimension(580, 140));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));
        infoPanel.add(Box.createVerticalStrut(12));

        subTituloH = new Label("La resolución ha sido modificada correctamente");
        subTituloH.setFont(Style.LABEL_FONT.deriveFont(Font.PLAIN, 21f));
        subTituloH.setAlignmentX(CENTER_ALIGNMENT);

        subTituloL = new Label("y el estado de la solicitud ha sido actualizado con éxito");
        subTituloL.setFont(Style.LABEL_FONT.deriveFont(Font.PLAIN, 19f));
        subTituloL.setAlignmentX(CENTER_ALIGNMENT);

        infoPanel.add(subTituloH);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(subTituloL);

        container.add(infoPanel);
        container.add(Box.createVerticalStrut(45));

        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setOpaque(false);
        buttonWrapper.setMaximumSize(new Dimension(300, 70));

        btnAceptar = new Button("Continuar");
        btnAceptar.setAlignmentX(CENTER_ALIGNMENT);

        buttonWrapper.add(btnAceptar);
        container.add(buttonWrapper);
        container.add(Box.createVerticalStrut(40));
        centralPanel.add(container);

        btnAceptar.addActionListener(e -> {
            coordinadorAplicacion.modificarOtraResolucion();
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
            Color color = new Color(52, 152, 219);
            switch (decision) {
                case "ACEPTADA":
                    color = new Color(46, 204, 113);
                    break;
                case "RECHAZADA":
                    color = new Color(231, 76, 60);
                    break;
                case "DEVUELTA":
                    color = new Color(241, 196, 15);
                    break;
            }
            subTituloH.setText("La resolución ha sido modificada a: " + decision);
            subTituloH.setForeground(color);
            subTituloL.setText("✉El estudiante será notificado del cambio");
            titulo.setForeground(color);
        }
    }
}
