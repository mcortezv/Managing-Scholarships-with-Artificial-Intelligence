package presentacion.evaluarSolicitudes;
import presentacion.coordinacion.MainFrame;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.styles.Button;
import presentacion.styles.ImgPanel;
import presentacion.styles.Label;
import presentacion.styles.Panel;
import presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;

public class EvaluacionCompletadaPanel extends Panel {
    private Label titulo;
    private ImgPanel imgPanel;
    private Label subTituloH;
    private Label subTituloL;
    private Button btnAceptar;
    private Button btnModificar;
    private ICoordinadorAplicacion coordinadorAplicacion;

    public EvaluacionCompletadaPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
        btnBack.setVisible(false);
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        imgPanel = new ImgPanel("/assets/check.png");
        imgPanel.setMaximumSize(new Dimension(270, 270));
        imgPanel.setAlignmentY(CENTER_ALIGNMENT);
        centralPanel.add(imgPanel);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));

        titulo = new Label("Evaluación Completada");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));

        subTituloH = new Label("Todas las solicitudes de esta convocatoria han sido evaluadas");
        subTituloH.setFont(Style.LABEL_FONT);
        subTituloH.setAlignmentX(CENTER_ALIGNMENT);
        subTituloL = new Label("o el limite de becas aceptadas ha sido alcanzado.");
        subTituloL.setFont(Style.LABEL_FONT);
        subTituloL.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(subTituloH);
        centralPanel.add(subTituloL);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 30));
        buttonPanel.setMaximumSize(new Dimension(400, 90));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAceptar = new Button("Aceptar");
        btnAceptar.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.add(btnAceptar);

        btnModificar = new Button("Modificar");
        btnModificar.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.add(btnModificar);

        centralPanel.add(buttonPanel);

        btnAceptar.addActionListener(e -> {
            //coordinadorAplicacion.aceptar();
        });

        btnModificar.addActionListener(e -> {
            //coordinadorAplicacion.modificar();
        });
    }
}
