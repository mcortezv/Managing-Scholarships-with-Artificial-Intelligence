package presentacion.apelarResultado.mainFrame;

import presentacion.CoordinadorAplicacion;
import presentacion.apelarResultado.ApelarResultado;
import presentacion.apelarResultado.coordinadorAplicacionApelarResultado.CoordinadorAplicacionApelarResultado;
import presentacion.apelarResultado.panels.DetalleSolicitud;
import presentacion.apelarResultado.panels.ListaSolicitudes;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrameApelarResultado extends JFrame {

    private final JPanel centralPanel;
    private final CoordinadorAplicacion coordinadorAplicacion;
    private final CoordinadorAplicacionApelarResultado coordinadorAplicacionApelarResultado;
    private final Map<String, JPanel> panels;
    private final ApelarResultado apelarResultado;

    public MainFrameApelarResultado(CoordinadorAplicacion coordinadorAplicacion, CoordinadorAplicacionApelarResultado coordinadorAplicacionApelarResultado) {
        setResizable(false);
        setSize(1500, 900);
        setLocationRelativeTo(null);
        setTitle("Sistema de Apelación de Resultados");
        setLayout(new BorderLayout());
        this.coordinadorAplicacion = coordinadorAplicacion;
        this.coordinadorAplicacionApelarResultado = coordinadorAplicacionApelarResultado;
        centralPanel = new JPanel();
        centralPanel.setLayout(new BorderLayout());
        centralPanel.setBackground(new Color(30, 30, 30));
        add(centralPanel, BorderLayout.CENTER);
        panels = new HashMap<>();
        apelarResultado = new ApelarResultado(coordinadorAplicacion, coordinadorAplicacionApelarResultado);
        ListaSolicitudes panelLista = new ListaSolicitudes(apelarResultado, coordinadorAplicacionApelarResultado);
        panels.put("listaSolicitudes", panelLista);
        DetalleSolicitud panelDetalle = new DetalleSolicitud(apelarResultado, coordinadorAplicacionApelarResultado);
        panels.put("detalleSolicitud", panelDetalle);
        showPanel("listaSolicitudes");
        apelarResultado.setMainFrame(this);
    }

    public void showPanel(String nombrePanel) {
        if (panels.containsKey(nombrePanel)) {
            centralPanel.removeAll();
            JPanel panelSeleccionado = panels.get(nombrePanel);
            if (panelSeleccionado instanceof presentacion.apelarResultado.PanelApelarResultado) {
                ((presentacion.apelarResultado.PanelApelarResultado) panelSeleccionado).startComponents();
            }
            centralPanel.add(panelSeleccionado, BorderLayout.CENTER);
            centralPanel.revalidate();
            centralPanel.repaint();
        } else {
            System.err.println("Error: El panel '" + nombrePanel + "' no está registrado en MainFrameApelarResultado.");
        }
    }

    public JPanel getPanel(String nombrePanel) {
        return panels.get(nombrePanel);
    }
}