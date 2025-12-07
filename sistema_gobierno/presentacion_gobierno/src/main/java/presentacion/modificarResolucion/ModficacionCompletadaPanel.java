package presentacion.modificarResolucion;
import presentacion.coordinacion.MainFrame;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.styles.Button;
import presentacion.styles.ImgPanel;
import presentacion.styles.Label;
import presentacion.styles.Panel;
import presentacion.styles.Style;
import javax.swing.*;
import java.awt.*;

public class ModficacionCompletadaPanel extends Panel {
    private presentacion.styles.Label titulo;
    private ImgPanel imgPanel;
    private presentacion.styles.Label subTituloH;
    private Label subTituloL;
    private Button btnAceptar;

    public ModficacionCompletadaPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
    }

    @Override
    public void startComponents() {
        btnBack.setVisible(false);
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        imgPanel = new ImgPanel("/assets/check.png");
        imgPanel.setMaximumSize(new Dimension(270, 270));
        imgPanel.setAlignmentY(CENTER_ALIGNMENT);
        centralPanel.add(imgPanel);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));

        titulo = new presentacion.styles.Label("Modificación Completada");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));

        subTituloH = new presentacion.styles.Label("La resolución ha sido modificada correctamente y el estado");
        subTituloH.setFont(Style.LABEL_FONT);
        subTituloH.setAlignmentX(CENTER_ALIGNMENT);
        subTituloL = new presentacion.styles.Label("de la solicitud ha sido actualizado con exito.");
        subTituloL.setFont(Style.LABEL_FONT);
        subTituloL.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(subTituloH);
        centralPanel.add(subTituloL);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        btnAceptar = new presentacion.styles.Button("Aceptar");
        btnAceptar.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(btnAceptar);

        btnAceptar.addActionListener(e -> {
            //coordinadorAplicacion.aceptar();
        });
    }
}
