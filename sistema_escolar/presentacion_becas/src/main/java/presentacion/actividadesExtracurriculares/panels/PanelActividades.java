/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.actividadesExtracurriculares.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import presentacion.actividadesExtracurriculares.coordinador.CoordinadorAplicacionActividades;
import presentacion.styles.Button;
import presentacion.styles.Style;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public abstract class PanelActividades extends JPanel {

    protected ActividadesExtracurriculares actividades;
    protected CoordinadorAplicacionActividades coordinadorAplicacionActividades;
    protected JPanel centralPanel;
    protected Button botonSiguiente;
    protected Button botonVolver;

    public PanelActividades(ActividadesExtracurriculares actividadesExtracurriculares, CoordinadorAplicacionActividades coordinadorAplicacionActividades) {
        actividades = actividadesExtracurriculares;
        this.coordinadorAplicacionActividades = coordinadorAplicacionActividades;
        this.setLayout(new BorderLayout());

        botonVolver = new Button("volver");
        botonVolver.setBackground(new Color(33, 150, 243));
        JPanel panelVolver = new JPanel();
        panelVolver.setBackground(new Color(240, 240, 240));
        panelVolver.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelVolver.setMinimumSize(new Dimension(1500, 50));
        panelVolver.setMaximumSize(new Dimension(1500, 50));
        panelVolver.setPreferredSize(new Dimension(1500, 50));
        panelVolver.add(botonVolver);
        botonSiguiente = new Button("siguiente");
        botonSiguiente.setBackground(new Color(52, 120, 246));
        centralPanel = new JPanel();
        centralPanel.setPreferredSize(new Dimension(1500, 750));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setBackground(Style.PANEL_COLOR);
        add(panelVolver, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);

    }

    public abstract void startComponents();

}
