/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.actividadesExtracurriculares.panels;

import dto.actividades.ActividadDTO;
import dto.actividades.ActividadesDTO;
import dto.actividades.GrupoDTO;
import dto.actividades.GruposResponseDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import presentacion.actividadesExtracurriculares.coordinador.CoordinadorAplicacionActividades;
import presentacion.styles.Button;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ListaActividades extends PanelActividades {
    JPanel panelContenido;
    
    
    public ListaActividades(ActividadesExtracurriculares actividadesExtracurriculares, CoordinadorAplicacionActividades coordinadorAplicacionActividades) {
        super(actividadesExtracurriculares, coordinadorAplicacionActividades);
        startComponents();
    }

    @Override
    public void startComponents() {
        centralPanel.removeAll();
        southPanel.removeAll();
        centralPanel.setBackground(new Color(240, 240, 240));
        centralPanel.add(Box.createVerticalStrut(20));
        JLabel titulo = new JLabel("ACTIVIDADES EXTRACURRICULARES");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(20));
        
        JTextField textField= new JTextField("Buscar por Nombre");
        textField.setMaximumSize(new Dimension(400, 40));
        textField.setPreferredSize(new Dimension(400, 40));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setForeground(Color.GRAY);
        
        centralPanel.add(textField);
        centralPanel.add(Box.createVerticalStrut(30));
        
        panelContenido= new JPanel();
        panelContenido.setBackground(Color.white);
        panelContenido.setMaximumSize(new Dimension(600,500));
        panelContenido.setPreferredSize(new Dimension(700,500));
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        
        panelContenido.setBorder(new EmptyBorder(20, 20, 20, 20));
        centralPanel.add(panelContenido);
        cargarElementos();
       
       
    }
    
    public void cargarElementos(){
        ActividadesDTO actividadesDTO= coordinadorAplicacionActividades.obtenerActividades();
        panelContenido.removeAll();
        
        
        for(ActividadDTO actividad:actividadesDTO.getActividades()){
            Button boton= new Button(actividad.getNombre());
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            boton.setBackground(new Color(60, 100, 220));
            boton.setOpaque(true);
            boton.setForeground(Color.WHITE);
          
            boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            boton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
            
            
            panelContenido.add(boton);
            panelContenido.add(Box.createVerticalStrut(2));
            
            boton.addActionListener(e->{
                System.out.println("hola");
                 System.out.println("el id es"+actividad.getId());
             coordinadorAplicacionActividades.procesarActividadSeleccionada(actividad);
               
            });
                
                
           
        }
        panelContenido.add(Box.createVerticalGlue());
       
    }
}
    
    
    
