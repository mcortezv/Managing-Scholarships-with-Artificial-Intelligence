/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.panels;

import dto.tutorias.CitaDTO;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.styles.Style;
import presentacion.tutorias.jframePrincipal.Tutorias;
import presentacion.tutorias.coordinadorAplicacion.CoordinadorAplicacionTutorias;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author katia
 */
public class PanelConfirmacionCita extends PanelTutorias {
    
    private Label titulo;
    private Label lblMateria;
    private Label lblFecha;
    private Label lblTema;
    private Label lblHora;
    private Label lblTutor;
    private Label lblModalidad;
    private Button btnConfirmarCita;
    
    public PanelConfirmacionCita(Tutorias frame, CoordinadorAplicacionTutorias coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
    }
    
    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        
        titulo = new Label("Confirmación");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));
        
        JPanel detallesPanel = new JPanel(new GridLayout(3, 4, 40, 20));
        detallesPanel.setOpaque(false);
        detallesPanel.setMaximumSize(new Dimension(900, 250));
        detallesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Label lblTituloMateria = new Label("Materia:");
        lblTituloMateria.setFont(Style.SUBTITLE_FONT);
        lblMateria = new Label("");
        lblMateria.setFont(Style.LABEL_FONT);
        
        Label lblTituloFecha = new Label("Fecha:");
        lblTituloFecha.setFont(Style.SUBTITLE_FONT);
        lblFecha = new Label("");
        lblFecha.setFont(Style.LABEL_FONT);
        
        Label lblTituloTema = new Label("Tema:");
        lblTituloTema.setFont(Style.SUBTITLE_FONT);
        lblTema = new Label("");
        lblTema.setFont(Style.LABEL_FONT);
        
        Label lblTituloHora = new Label("Hora:");
        lblTituloHora.setFont(Style.SUBTITLE_FONT);
        lblHora = new Label("");
        lblHora.setFont(Style.LABEL_FONT);
        
        Label lblTituloTutor = new Label("Tutor:");
        lblTituloTutor.setFont(Style.SUBTITLE_FONT);
        lblTutor = new Label("");
        lblTutor.setFont(Style.LABEL_FONT);
        
        Label lblTituloModalidad = new Label("Modalidad:");
        lblTituloModalidad.setFont(Style.SUBTITLE_FONT);
        lblModalidad = new Label("");
        lblModalidad.setFont(Style.LABEL_FONT);
        
        detallesPanel.add(lblTituloMateria);
        detallesPanel.add(lblMateria);
        detallesPanel.add(lblTituloTema);
        detallesPanel.add(lblTema);
        
        detallesPanel.add(lblTituloFecha);
        detallesPanel.add(lblFecha);
        detallesPanel.add(lblTituloHora);
        detallesPanel.add(lblHora);
        
        detallesPanel.add(lblTituloTutor);
        detallesPanel.add(lblTutor);
        detallesPanel.add(lblTituloModalidad);
        detallesPanel.add(lblModalidad);
        
        centralPanel.add(detallesPanel);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO * 2));
        
        btnConfirmarCita = new Button("Confirmar Cita");
        btnConfirmarCita.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(btnConfirmarCita);
        centralPanel.add(Box.createVerticalGlue());
        
        btnConfirmarCita.addActionListener(e -> {
            coordinadorAplicacion.confirmarYAgendarCita();
        });
        
        btnBack.addActionListener(e -> mainFrame.showPanel("horariosDisponibles"));
    }
    
    public void cargarDatosCita(CitaDTO cita) {
        lblMateria.setText(cita.getNombreMateria());
        lblFecha.setText(cita.getFecha().toString());
        lblTema.setText(cita.getTema());
        lblHora.setText(cita.getHora().toString());
        lblTutor.setText(cita.getNombreTutor());
        lblModalidad.setText(cita.getModalidad());
    }
}
