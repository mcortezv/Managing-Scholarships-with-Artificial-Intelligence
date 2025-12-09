/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.actividadesExtracurriculares.panels;

import itson.LoginDTOItson;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import presentacion.actividadesExtracurriculares.coordinador.CoordinadorAplicacionActividades;
import presentacion.bajaActividades.panels.DetallesActividadBaja;
import presentacion.bajaActividades.panels.DetallesExtraBaja;

import presentacion.login.panels.NorthPanel;
import presentacion.styles.Button;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ActividadesExtracurriculares extends JFrame {

    private JPanel northPanel;
    private JPanel centralPanel;
    private Map<String, JPanel> panels;
    private CoordinadorAplicacionActividades coordinadorAplicacionActividades;
    private LoginDTOItson loginDTO;

    public ActividadesExtracurriculares(CoordinadorAplicacionActividades coordinadorAplicacionActividades) {
        this.coordinadorAplicacionActividades = coordinadorAplicacionActividades;
        setTitle("Actividades Extracurriculares");
        setSize(1500, 900);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        northPanel = new JPanel();
        northPanel.setBackground(new Color(50, 100, 200));
        northPanel.setPreferredSize(new Dimension(1, 80));
        centralPanel = new JPanel();
        panels = new HashMap<>();
        initializePanels();
        add(northPanel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);

        showPanel("MenuOpciones");
        coordinadorAplicacionActividades.setActividades(this);

    }

    private void initializePanels() {

        PanelActividades panel;

        panel = new MenuOpciones(this, coordinadorAplicacionActividades);
        panel.startComponents();
        panels.put("MenuOpciones", panel);

        panel = new ListaActividades(this, coordinadorAplicacionActividades);
        panel.startComponents();
        panels.put("ListaActividades", panel);

        panel = new ResumenClases(this, coordinadorAplicacionActividades);
        panel.startComponents();
        panels.put("ResumenClases", panel);

        panel = new DetalleGrupo(this, coordinadorAplicacionActividades);
        panel.startComponents();
        panels.put("DetalleGrupo", panel);
        
        panel= new ListaInscripciones(this, coordinadorAplicacionActividades);
        panel.startComponents();
        panels.put("ListaInscripciones", panel);
        
        panel= new DetallesActividadBaja(this, coordinadorAplicacionActividades);
        panel.startComponents();
        panels.put("DetallesActividadBaja", panel);
        
        panel= new DetallesExtraBaja(this, coordinadorAplicacionActividades);
        panel.startComponents();
        panels.put("DetallesExtraBaja", panel);
        

    }

    public void showPanel(String pantalla) {
        centralPanel.removeAll();
        centralPanel.add(panels.get(pantalla), BorderLayout.CENTER);
        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public JPanel getPanel(String key) {
        return panels.get(key);
    }

    public void recuperarLogin(LoginDTOItson loginDTO) {
        this.loginDTO = loginDTO;
        coordinadorAplicacionActividades.recuperarLogin(loginDTO);
    }

}
