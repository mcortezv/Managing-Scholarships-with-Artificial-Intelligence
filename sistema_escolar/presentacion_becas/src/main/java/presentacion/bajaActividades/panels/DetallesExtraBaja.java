/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.bajaActividades.panels;

import dto.actividades.GrupoDTO;
import dto.actividades.InscripcionDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import presentacion.actividadesExtracurriculares.coordinador.CoordinadorAplicacionActividades;
import presentacion.actividadesExtracurriculares.panels.ActividadesExtracurriculares;
import presentacion.actividadesExtracurriculares.panels.PanelActividades;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class DetallesExtraBaja extends PanelActividades{
    
    JPanel panelContenido;
    private JTextArea fieldMotivo;

    public DetallesExtraBaja(ActividadesExtracurriculares actividadesExtracurriculares, CoordinadorAplicacionActividades coordinadorAplicacionActividades) {
        super(actividadesExtracurriculares, coordinadorAplicacionActividades);
    }
    
    
    @Override
    public void startComponents() {
        centralPanel.removeAll();

        JPanel panelGrupo = new JPanel();
        centralPanel.setBackground(new Color(240, 240, 240));
        centralPanel.add(Box.createVerticalStrut(20));
        JLabel titulo = new JLabel("DETALLES DE LA ACTIVIDAD");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(20));

        panelContenido = new JPanel();
        panelContenido.setBackground(Color.white);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        panelContenido.setMaximumSize(new Dimension(500, 450));
        panelContenido.setPreferredSize(new Dimension(500, 450));
        panelContenido.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelContenido.setBorder(new EmptyBorder(30, 40, 30, 40));
        centralPanel.add(panelContenido);
        
        centralPanel.add(Box.createVerticalStrut(30));
        botonSiguiente.setText("confirmar");
        botonSiguiente.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSiguiente.setFont(new Font("Sanserif", Font.BOLD, 16));
       // botonSiguiente.setForeground(Color.WHITE);
        botonSiguiente.setBackground(new Color(52, 120, 246));
        Dimension tamanioBoton = new Dimension(200, 50);
        centralPanel.add(botonSiguiente);
        centralPanel.add(Box.createVerticalStrut(30));

        botonSiguiente.addActionListener(e -> {
            String motivo= fieldMotivo.getText();
            System.out.println(motivo);
            
           coordinadorAplicacionActividades.darBajaActividad(motivo);
        });

        botonVolver.addActionListener(e -> {
            coordinadorAplicacionActividades.mostarListaInscripciones();
        });

    }

    public void cargarInscripcion(InscripcionDTO inscripcion, GrupoDTO grupoInscripcionDTO) {
        
        panelContenido.removeAll();
        panelContenido.add(Box.createVerticalGlue());
        System.out.println(inscripcion.getId());
        panelContenido.add(crearCampo("Fecha de Inscripcion",  inscripcion.getFechaInscripcion().toString()));
        panelContenido.add(Box.createVerticalStrut(20));
        LocalDate fechaInicio = grupoInscripcionDTO.getFechaInicio();
        long diasPasados = ChronoUnit.DAYS.between(fechaInicio, LocalDate.now());
        if(diasPasados<0){
            panelContenido.add(crearCampo("Dias pasados", "Aun no inicia"));
        } else{
             panelContenido.add(crearCampo("Dias pasados", String.valueOf(diasPasados)));;
        }
        LocalDate fechaFin = grupoInscripcionDTO.getFechaFin();
        long diasFaltantes = fechaFin.toEpochDay() - LocalDate.now().toEpochDay();
        panelContenido.add(crearCampo("Dias faltantes ", String.valueOf(diasFaltantes)));
        panelContenido.add(Box.createVerticalStrut(20));
        
        
        JLabel labelMotivo= new JLabel("Motivo de la Baja");
        labelMotivo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        labelMotivo.setForeground(Color.GRAY);
        labelMotivo.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldMotivo = new JTextArea(3, 20);
        fieldMotivo.setLineWrap(true);
        fieldMotivo.setWrapStyleWord(true);
        fieldMotivo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        panelContenido.add(labelMotivo);
        panelContenido.add(fieldMotivo);
        
        panelContenido.add(Box.createVerticalGlue());       
        panelContenido.revalidate();
        panelContenido.repaint();
        

    }

    private JPanel crearCampo(String titulo, String contenido) {
        JPanel panelCampo = new JPanel();
        panelCampo.setLayout(new BoxLayout(panelCampo, BoxLayout.Y_AXIS));
        panelCampo.setBackground(Color.WHITE);
        panelCampo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblTitulo.setForeground(Color.GRAY);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblValor = new JLabel(contenido, SwingConstants.CENTER);
        lblValor.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblValor.setForeground(Color.BLACK);
        lblValor.setOpaque(true);
        lblValor.setBackground(Color.WHITE);
        lblValor.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(5, 10, 5, 10)
        ));

        lblValor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        lblValor.setPreferredSize(new Dimension(400, 40));
        lblValor.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelCampo.add(lblTitulo);
        panelCampo.add(Box.createVerticalStrut(5));
        panelCampo.add(lblValor);

        return panelCampo;

    }
    
    
    
    
    

    
    
    
}
    
