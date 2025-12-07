/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.jframePrincipal;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import presentacion.tutorias.coordinadorAplicacion.CoordinadorAplicacionTutorias;
import presentacion.tutorias.panels.PanelCitasActivas;
import presentacion.tutorias.panels.PanelConfirmacionCita;
import presentacion.tutorias.panels.PanelDetallesSolicitud;
import presentacion.tutorias.panels.PanelHistorial;
import presentacion.tutorias.panels.PanelHorariosDisponibles;
import presentacion.tutorias.panels.PanelMenuTutorias;
import presentacion.tutorias.panels.PanelTutoresDisponibles;
import presentacion.tutorias.panels.PanelTutorias;

/**
 *
 * @author katia
 */
public class Tutorias extends JFrame{    
    private JPanel centralPanel;
    private Map<String, JPanel> panels;
    private final CoordinadorAplicacionTutorias coordinadorAplicacion;
    
    public Tutorias(CoordinadorAplicacionTutorias coordinadorAplicacion) {
        this.coordinadorAplicacion = coordinadorAplicacion;
        
        setTitle("Tutorías Académicas");
        setResizable(false);
        setSize(1500, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        centralPanel = new JPanel(new BorderLayout());
        panels = new HashMap<>();
        
        initializePanels();
        
        add(centralPanel, BorderLayout.CENTER);
        
        coordinadorAplicacion.setTutorias(this);
        showPanel("menuTutorias");
    }
    
    private void initializePanels() {
    PanelTutorias panel;
    
    panel = new PanelMenuTutorias(this, coordinadorAplicacion);
    panel.startComponents();
    panels.put("menuTutorias", panel);
    
    panel = new PanelDetallesSolicitud(this, coordinadorAplicacion);
    panel.startComponents();
    panels.put("detallesSolicitud", panel);
    
    panel = new PanelTutoresDisponibles(this, coordinadorAplicacion);
    panel.startComponents();
    panels.put("tutoresDisponibles", panel);
    
    panel = new PanelHorariosDisponibles(this, coordinadorAplicacion);
    panel.startComponents();
    panels.put("horariosDisponibles", panel);
    
    panel = new PanelConfirmacionCita(this, coordinadorAplicacion);
    panel.startComponents();
    panels.put("confirmacionCita", panel);
    
    panel = new PanelCitasActivas(this, coordinadorAplicacion);
    panel.startComponents();
    panels.put("citasActivas", panel);
    
    panel = new PanelHistorial(this, coordinadorAplicacion);
    panel.startComponents();
    panels.put("historial", panel);
}
    
    public void showPanel(String nombrePanel) {
        centralPanel.removeAll();
        
        JPanel panel = panels.get(nombrePanel);
        if (panel != null) {
            centralPanel.add(panel, BorderLayout.CENTER);
        } else {
            System.err.println("Tutorias.showPanel: panel '" + nombrePanel + "' no encontrado");
        }
        
        centralPanel.revalidate();
        centralPanel.repaint();
    }
    
    public JPanel getPanel(String key) {
        return panels.get(key);
    }
}
