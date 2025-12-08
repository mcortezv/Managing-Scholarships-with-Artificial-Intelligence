package presentacion.apelarResultado;

import presentacion.CoordinadorAplicacion;
import presentacion.apelarResultado.coordinadorAplicacionApelarResultado.CoordinadorAplicacionApelarResultado;
import presentacion.apelarResultado.mainFrame.MainFrameApelarResultado;
import presentacion.apelarResultado.panels.DetalleSolicitud;
import presentacion.apelarResultado.panels.ListaSolicitudes;
import presentacion.login.panels.NorthPanel;
import presentacion.pagarAdeudo.panels.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ApelarResultado extends JFrame {
    private MainFrameApelarResultado mainFrame;
    private NorthPanel northPanel;
    private final JPanel centralPanel;
    private final Map<String, JPanel> panels;
    private final CoordinadorAplicacionApelarResultado coordinadorAplicacionApelarResultado;

    public ApelarResultado(CoordinadorAplicacion coordinadorAplicacion, CoordinadorAplicacionApelarResultado coordinadorAplicacionApelarResultado) {
        setTitle("Apelar Resultado");
        setResizable(false);
        setSize(1500, 900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.centralPanel = new JPanel();
        this.northPanel = new NorthPanel();
        this.coordinadorAplicacionApelarResultado = coordinadorAplicacionApelarResultado;
        panels = new HashMap<>();

        initializePanels();

        add(northPanel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
        northPanel.setVisible(false);

        coordinadorAplicacionApelarResultado.setApelarResultado(this);

        showPanel("listaSolicitudes");

    }

    public void initializePanels() {

        PanelApelarResultado panel;

        panel = new ListaSolicitudes(this, coordinadorAplicacionApelarResultado);
        panel.startComponents();
        panels.put("listaSolicitudes", panel);

        panel = new DetalleSolicitud(this, coordinadorAplicacionApelarResultado);
        panel.startComponents();
        panels.put("detalleSolicitud", panel);


    }

    public void setMainFrame(MainFrameApelarResultado mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void showPanel(String nuevoPanel) {
        centralPanel.removeAll();
        JPanel p =  panels.get(nuevoPanel);
        if (p != null) {
            centralPanel.add(p, BorderLayout.CENTER);
        }
        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public JPanel getPanel(String key) {
        return panels.get(key);
    }

}
