/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.actividadesExtracurriculares.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import presentacion.actividadesExtracurriculares.coordinador.CoordinadorAplicacionActividades;
import presentacion.styles.Button;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class MenuOpciones extends PanelActividades{

    
    public MenuOpciones(ActividadesExtracurriculares actividades, CoordinadorAplicacionActividades coordinadorAplicacionActividades) {
        super(actividades, coordinadorAplicacionActividades);
        startComponents();
    }

    @Override
    public void startComponents() {
         centralPanel.removeAll();
         centralPanel.setAlignmentY(CENTER_ALIGNMENT);
         Dimension tamanoBoton = new Dimension(300, 60);
         Button botonInscribir= new Button("inscribir");
         botonInscribir.setBackground(new Color(33, 150, 243));
         botonInscribir.setForeground(Color.GRAY);
         botonInscribir.setFont(new Font("SansSerif", Font.BOLD, 18));
         Button botonBaja= new Button("baja");
         botonBaja.setBackground(new Color(255, 230, 180));
         botonBaja.setForeground(Color.GRAY);
         botonBaja.setFont(new Font("SansSerif", Font.BOLD, 18));
         botonInscribir.setPreferredSize(tamanoBoton);
         botonInscribir.setMaximumSize(tamanoBoton);
         botonInscribir.setAlignmentX(Button.CENTER_ALIGNMENT);
         centralPanel.setBackground(new Color(173, 216, 230));
         botonBaja.setPreferredSize(tamanoBoton);
         botonBaja.setMaximumSize(tamanoBoton);
         botonBaja.setAlignmentX(Button.CENTER_ALIGNMENT);
         
         centralPanel.add(Box.createVerticalGlue());
         
         centralPanel.add(botonInscribir);
         centralPanel.add(Box.createVerticalStrut(25));
         centralPanel.add(botonBaja);
         
         centralPanel.add(Box.createVerticalGlue());
         
         botonInscribir.addActionListener(e->{
             coordinadorAplicacionActividades.inscribirActividad();
         });
         
         botonBaja.addActionListener(e->{
             
             coordinadorAplicacionActividades.mostarListaInscripciones();
         });
         
         botonVolver.addActionListener(e->{
             coordinadorAplicacionActividades.regresarAlMenuPrincipal();
         });
         
      
      
      
    }
    
}
