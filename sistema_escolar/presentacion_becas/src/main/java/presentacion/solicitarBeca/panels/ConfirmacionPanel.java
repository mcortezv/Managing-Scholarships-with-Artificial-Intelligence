package presentacion.solicitarBeca.panels;
import presentacion.CoordinadorAplicacion;
import presentacion.solicitarBeca.PanelSolicitarBeca;
import presentacion.solicitarBeca.SolicitarBeca;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.styles.ImgPanel;
import presentacion.styles.Style;
import javax.swing.*;
import java.awt.*;

/**
 * The type Confirmacion panel.
 *
 * @author Cortez
 */
public class ConfirmacionPanel extends PanelSolicitarBeca {
    private Label titulo;
    private ImgPanel img;
    private Label subTitulo;
    private Button btnAceptar;

    /**
     * Instantiates a new Confirmacion panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public ConfirmacionPanel(SolicitarBeca frame, CoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
    }

    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(60));
        titulo = new Label("Solicitud Completada");
        titulo.setFont(Style.TITLE_FONT);  // Más grande
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(40));
        img = new ImgPanel("/assets/check.png");
        img.setPreferredSize(new Dimension(260, 260));
        img.setMaximumSize(new Dimension(260, 260));
        img.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(img);
        centralPanel.add(Box.createVerticalStrut(35));
        subTitulo = new Label("Tu solicitud ha sido registrada exitosamente.");
        subTitulo.setFont(new Font("SansSerif", Font.PLAIN, 28));
        subTitulo.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(subTitulo);
        centralPanel.add(Box.createVerticalStrut(50));
        btnAceptar = new Button("Aceptar");
        btnAceptar.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(btnAceptar);
        btnBack.setVisible(false);
        btnAceptar.addActionListener(e -> coordinadorAplicacion.main());
    }
}
