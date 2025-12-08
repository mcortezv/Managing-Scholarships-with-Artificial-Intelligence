package presentacion.apelarResultado;

import presentacion.apelarResultado.coordinadorAplicacionApelarResultado.CoordinadorAplicacionApelarResultado;
import presentacion.styles.Button;
import presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;

public abstract class PanelApelarResultado extends JPanel {
    protected ApelarResultado mainFrame;
    protected JPanel centralPanel;
    protected JPanel southPanel;
    protected Button btnBack;
    protected CoordinadorAplicacionApelarResultado coordinadorAplicacionApelarResultado;

    public PanelApelarResultado(ApelarResultado mainFrame, CoordinadorAplicacionApelarResultado coordinadorAplicacionApelarResultado){
        this.mainFrame = mainFrame;
        btnBack = new Button("Volver");
        this.coordinadorAplicacionApelarResultado = coordinadorAplicacionApelarResultado;
        setLayout(new BorderLayout());
        centralPanel = new JPanel();
        southPanel = new JPanel();
        centralPanel.setPreferredSize(new Dimension(1500, 750));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        southPanel.setPreferredSize(new Dimension(1500, 100));
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        centralPanel.setBackground(Style.PANEL_COLOR);
        southPanel.setBackground(Style.PANEL_COLOR);
        add(centralPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        southPanel.add(btnBack);
    }

    public abstract void startComponents();

}
