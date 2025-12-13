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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
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
        centralPanel.setBackground(new Color(240, 240, 240));
        centralPanel.add(Box.createVerticalStrut(20));
        JLabel titulo = new JLabel("ACTIVIDADES EXTRACURRICULARES");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(20));
        
        
        JPanel panelBusqueda= new JPanel();
        panelBusqueda.setLayout(new BoxLayout(panelBusqueda, BoxLayout.X_AXIS));
        panelBusqueda.setBackground(new Color(240, 240, 240));
        panelBusqueda.setMaximumSize(new Dimension(700, 40));
        JTextField textField= new JTextField("Buscar por Nombre");
        textField.setMaximumSize(new Dimension(400, 40));
        textField.setPreferredSize(new Dimension(400, 40));
    //    textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setForeground(Color.GRAY);
        
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("Buscar por Nombre")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("Buscar por Nombre");
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        
        
        Button botonBuscar = new Button("Buscar");
        botonBuscar.setBackground(Color.GRAY);
        botonBuscar.setForeground(Color.WHITE);
        botonBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonBuscar.setMaximumSize(new Dimension(200, 40));
        botonBuscar.setPreferredSize(new Dimension(200, 40));
        
        botonBuscar.addActionListener(e->{
            System.out.println(textField.getText());
            coordinadorAplicacionActividades.obtenerActividadPorNombre(textField.getText());
          
            
        });
        
        panelBusqueda.add(Box.createHorizontalGlue());
        panelBusqueda.add(textField);
        panelBusqueda.add(Box.createHorizontalStrut(10));
        panelBusqueda.add(botonBuscar);
        panelBusqueda.add(Box.createHorizontalGlue());
        
        centralPanel.add(panelBusqueda);
        centralPanel.add(Box.createVerticalStrut(30));
        
        panelContenido= new JPanel();
        panelContenido.setBackground(Color.white);

        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));      
        panelContenido.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JScrollPane scrollPane= new JScrollPane(panelContenido);
        scrollPane.setMaximumSize(new Dimension(600,500));
        scrollPane.setPreferredSize(new Dimension(700,500));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
        centralPanel.add(scrollPane);
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
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            boton.setMaximumSize(new Dimension(600, 50));
            boton.setPreferredSize(new Dimension(600, 50));
            
            boton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        boton.setBackground(new Color(100, 140, 240));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        boton.setBackground(new Color(60, 100, 220));
                    }
                });
            
            panelContenido.add(boton);
            panelContenido.add(Box.createVerticalStrut(5));
            
            boton.addActionListener(e->{
             coordinadorAplicacionActividades.procesarActividadSeleccionada(actividad);
               
            });
                
      
        }
        
        botonVolver.addActionListener(e->{
                coordinadorAplicacionActividades.mostrarMenu();
        });
                
        panelContenido.add(Box.createVerticalGlue());
        panelContenido.revalidate();
        panelContenido.repaint();
    }
}
    
    
    
