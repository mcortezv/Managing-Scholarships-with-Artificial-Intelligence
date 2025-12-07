/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import presentacion.styles.Button;
import presentacion.styles.Style;
import presentacion.tutorias.coordinadorAplicacion.CoordinadorAplicacionTutorias;
import presentacion.tutorias.jframePrincipal.Tutorias;

/**
 *
 * @author katia
 */
public abstract class PanelTutorias extends JPanel{
    protected Tutorias mainFrame;
    protected JPanel centralPanel;
    protected JPanel southPanel;
    protected Button btnBack;
    protected CoordinadorAplicacionTutorias coordinadorAplicacion;
    
    public PanelTutorias(Tutorias frame, CoordinadorAplicacionTutorias coordinadorAplicacion) {
        this.mainFrame = frame;
        this.coordinadorAplicacion = coordinadorAplicacion;
        this.btnBack = new Button("Volver");
        
        setLayout(new BorderLayout());
        
        centralPanel = new JPanel();
        southPanel = new JPanel();
        
        centralPanel.setPreferredSize(new Dimension(1500, 770));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        
        southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        southPanel.setPreferredSize(new Dimension(1500, 90));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 0, 0));
        
        centralPanel.setBackground(Style.PANEL_COLOR);
        southPanel.setBackground(Style.PANEL_COLOR);
        
        add(centralPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        
        southPanel.add(btnBack);
        
        btnBack.addActionListener(e -> {
        });
    }
    public abstract void startComponents();
}
