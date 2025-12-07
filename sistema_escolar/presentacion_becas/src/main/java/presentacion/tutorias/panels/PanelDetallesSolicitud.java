/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.panels;

import com.toedter.calendar.JDateChooser;
import dto.tutorias.MateriaDTO;
import java.awt.Component;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.Box;
import javax.swing.JOptionPane;
import presentacion.styles.Button;
import presentacion.styles.ComboBox;
import presentacion.styles.Label;
import presentacion.styles.Style;
import presentacion.styles.TextField;
import presentacion.tutorias.coordinadorAplicacion.CoordinadorAplicacionTutorias;
import presentacion.tutorias.jframePrincipal.Tutorias;

/**
 *
 * @author katia
 */
public class PanelDetallesSolicitud extends PanelTutorias {
    
    private Label titulo;
    private ComboBox<MateriaDTO> cmbMateria;
    private TextField txtTema;
    private ComboBox<String> cmbModalidad;
    private JDateChooser dateChooser;
    private Button btnVerDisponibilidad;
    
    public PanelDetallesSolicitud(Tutorias frame, CoordinadorAplicacionTutorias coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
    }
    
    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        
        titulo = new Label("Detalles Solicitud");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));
        
        Label lblMateria = new Label("Materia en la que se requiere:");
        lblMateria.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(lblMateria);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        
        cmbMateria = new ComboBox<>(new MateriaDTO[]{});
        cmbMateria.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(cmbMateria);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));
        
        Label lblTema = new Label("Tema a tratar/Descripción:");
        lblTema.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(lblTema);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        
        txtTema = new TextField(1);
        txtTema.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(txtTema);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));
        
        Label lblModalidad = new Label("Modalidad:");
        lblModalidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(lblModalidad);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        
        String[] modalidades = {"Virtual", "Presencial"};
        cmbModalidad = new ComboBox<>(modalidades);
        cmbModalidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(cmbModalidad);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));
        
        Label lblFecha = new Label("Fecha:");
        lblFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(lblFecha);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setMinSelectableDate(new Date());
        dateChooser.setFont(Style.INPUT_FONT);
        dateChooser.setMaximumSize(new Dimension(600, 60));
        dateChooser.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(dateChooser);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));
        
        btnVerDisponibilidad = new Button("Ver Disponibilidad");
        btnVerDisponibilidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(btnVerDisponibilidad);
        centralPanel.add(Box.createVerticalGlue());
        
        btnVerDisponibilidad.addActionListener(e -> procesarVerDisponibilidad());
        btnBack.addActionListener(e -> mainFrame.showPanel("menuTutorias"));
    }
    
    private void procesarVerDisponibilidad() {
        try {
            MateriaDTO materiaSeleccionada = (MateriaDTO) cmbMateria.getSelectedItem();
            String tema = txtTema.getText().trim();
            String modalidad = (String) cmbModalidad.getSelectedItem();
            Date fechaSeleccionada = dateChooser.getDate();
            
            if (materiaSeleccionada == null || tema.isEmpty() || 
                modalidad == null || fechaSeleccionada == null) {
                JOptionPane.showMessageDialog(
                    mainFrame,
                    "Por favor complete todos los campos",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            LocalDate fecha = fechaSeleccionada.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
            
            coordinadorAplicacion.guardarDetallesSolicitud(
                materiaSeleccionada.getId(),
                tema,
                modalidad,
                fecha
            );
            
            coordinadorAplicacion.mostrarTutoresDisponibles();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                mainFrame,
                "Error: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void setMaterias(List<MateriaDTO> materias) {
        cmbMateria.removeAllItems();
        for (MateriaDTO materia : materias) {
            cmbMateria.addItem(materia);
        }
    }
}
