package presentacion.login.panels;
import presentacion.CoordinadorAplicacion;
import presentacion.login.MainFrame;
import presentacion.styles.ImgPanel;
import presentacion.styles.Label;
import presentacion.styles.Panel;
import presentacion.styles.Style;
import presentacion.styles.Button;
import javax.swing.Box;
import java.awt.*;

/**
 * The type Hub panel.
 *
 * @author Escalante, Sebastian.
 */
public class HubPanel extends Panel {
    private CoordinadorAplicacion coordinadorAplicacion;
    private Label titulo;
    private ImgPanel img;
    /**
     * The Btn log out.
     */
    protected Button btnLogOut;

    /**
     * Instantiates a new Hub panel.
     *
     * @param mainFrame             the main frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public HubPanel(MainFrame mainFrame, CoordinadorAplicacion coordinadorAplicacion) {
        super(mainFrame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
        btnBack.setVisible(false);
        btnLogOut = new Button("Cerrar Sesión");

        img = new ImgPanel("/assets/hero.png");
        img.setMaximumSize(new Dimension(1500, 700));
        img.setAlignmentY(CENTER_ALIGNMENT);
        centralPanel.add(img);
        southPanel.add(centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO)));
        southPanel.add(btnLogOut);

        btnLogOut.addActionListener(e -> {
            coordinadorAplicacion.cerrarSesion();
            mainFrame.showPanel("iniciarSesionPanel");
            mainFrame.getNorthPanel().setVisible(false);
        });
    }
}
