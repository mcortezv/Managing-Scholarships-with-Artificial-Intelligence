/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.panels;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.styles.Style;
import presentacion.tutorias.coordinadorAplicacion.CoordinadorAplicacionTutorias;
import presentacion.tutorias.jframePrincipal.Tutorias;

/**
 *
 * @author katia
 */
public class PanelMenuTutorias extends PanelTutorias {
    
    private Label titulo;
    private Button btnAgendarTutoria;
    private Button btnCancelarTutoria;
    private Button btnHistorial;
    
    public PanelMenuTutorias(Tutorias frame, CoordinadorAplicacionTutorias coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
    }
    
    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        
        titulo = new Label("Tutorías Académicas");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO * 2));
        
        JPanel botonesPanel = new JPanel();
        botonesPanel.setOpaque(false);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));
        botonesPanel.setMaximumSize(new Dimension(400, 400));
        botonesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnAgendarTutoria = new Button("Agendar Tutoría");
        btnAgendarTutoria.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgendarTutoria.setMaximumSize(new Dimension(300, 60));
        botonesPanel.add(btnAgendarTutoria);
        botonesPanel.add(Box.createVerticalStrut(30));
        
        btnCancelarTutoria = new Button("Cancelar Tutoría");
        btnCancelarTutoria.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancelarTutoria.setMaximumSize(new Dimension(300, 60));
        botonesPanel.add(btnCancelarTutoria);
        botonesPanel.add(Box.createVerticalStrut(30));
        
        btnHistorial = new Button("Historial Tutorías Solicitadas");
        btnHistorial.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnHistorial.setMaximumSize(new Dimension(300, 60));
        botonesPanel.add(btnHistorial);
        
        centralPanel.add(botonesPanel);
        centralPanel.add(Box.createVerticalGlue());
        
        btnAgendarTutoria.addActionListener(e -> {
            coordinadorAplicacion.intentarMostrarDetallesSolicitud();
        });
        
        btnCancelarTutoria.addActionListener(e -> {
            coordinadorAplicacion.mostrarCitasActivas();
        });
        
        btnHistorial.addActionListener(e -> {
            coordinadorAplicacion.mostrarHistorial();
        });
        
        btnBack.addActionListener(e -> {
            coordinadorAplicacion.regresarAlMenuPrincipal();
        });
    }
}