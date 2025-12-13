/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.panels;

import com.toedter.calendar.JDateChooser;
import dto.tutorias.CitaDTO;
import dto.tutorias.MateriaDTO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import presentacion.styles.Button;
import presentacion.styles.ComboBox;
import presentacion.styles.Label;
import presentacion.styles.Style;
import presentacion.tutorias.coordinadorAplicacion.CoordinadorAplicacionTutorias;
import presentacion.tutorias.jframePrincipal.Tutorias;

/**
 *
 * @author katia
 */
public class PanelHistorial extends PanelTutorias {
    
    private Label titulo;
    private JDateChooser dateChooser;
    private ComboBox<MateriaDTO> cmbMaterias;
    private Button btnFiltrarFecha;
    private Button btnFiltrarMateria;
    private Button btnMostrarTodo;
    private Button btnFiltrarAmbos;
    private JTable tablaHistorial;
    private DefaultTableModel modeloTabla;
    
    public PanelHistorial(Tutorias frame, CoordinadorAplicacionTutorias coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
    }
    
    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        
        titulo = new Label("Historial");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));
        
        JPanel filtrosPanel = new JPanel();
        filtrosPanel.setOpaque(false);
        filtrosPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        filtrosPanel.setMaximumSize(new Dimension(1400, 100));
        
        Label lblFecha = new Label("Fecha:");
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setPreferredSize(new Dimension(150, 40));
        dateChooser.setFont(Style.INPUT_FONT);
        btnFiltrarFecha = new Button("Filtrar");
        
        filtrosPanel.add(lblFecha);
        filtrosPanel.add(dateChooser);
        filtrosPanel.add(btnFiltrarFecha);
        
        filtrosPanel.add(Box.createHorizontalStrut(30));
        
        Label lblMateria = new Label("Materia:");
        cmbMaterias = new ComboBox<>(new MateriaDTO[]{});
        cmbMaterias.setPreferredSize(new Dimension(200, 40));
        btnFiltrarMateria = new Button("Filtrar");
        
        filtrosPanel.add(lblMateria);
        filtrosPanel.add(cmbMaterias);
        filtrosPanel.add(btnFiltrarMateria);
        
        filtrosPanel.add(Box.createHorizontalStrut(30));
        
        btnMostrarTodo = new Button("Mostrar Todo");
        filtrosPanel.add(btnMostrarTodo);
        
        btnFiltrarAmbos = new Button("Filtrar Ambos");
        filtrosPanel.add(btnFiltrarAmbos);
        
        centralPanel.add(filtrosPanel);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));
        
        String[] columnas = {"Nombre Tutor", "Materia", "Tema", "Fecha", "Hora", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaHistorial = new JTable(modeloTabla);
        tablaHistorial.setFont(Style.LABEL_FONT);
        tablaHistorial.setRowHeight(50);
        tablaHistorial.getTableHeader().setFont(Style.SUBTITLE_FONT);
        
        JScrollPane scrollPane = new JScrollPane(tablaHistorial);
        scrollPane.setPreferredSize(new Dimension(1400, 400));
        scrollPane.setMaximumSize(new Dimension(1400, 400));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(scrollPane);
        
        centralPanel.add(Box.createVerticalGlue());
        
        btnFiltrarFecha.addActionListener(e -> filtrarPorFecha());
        btnFiltrarMateria.addActionListener(e -> filtrarPorMateria());
        btnMostrarTodo.addActionListener(e -> {
            dateChooser.setDate(null);
            cmbMaterias.setSelectedIndex(-1);
            coordinadorAplicacion.mostrarHistorial();
        });
        btnFiltrarAmbos.addActionListener(e -> filtrarPorFechaYMateria());
        
        btnBack.addActionListener(e -> mainFrame.showPanel("menuTutorias"));
    }
    
    private void filtrarPorFecha() {
        Date fechaSeleccionada = dateChooser.getDate();
        
        if (fechaSeleccionada == null) {
            JOptionPane.showMessageDialog(
                mainFrame,
                "Debe seleccionar una fecha",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        LocalDate fecha = fechaSeleccionada.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
        
        coordinadorAplicacion.filtrarHistorialPorFecha(fecha);
    }
    
    private void filtrarPorMateria() {
        MateriaDTO materiaSeleccionada = (MateriaDTO) cmbMaterias.getSelectedItem();
        
        if (materiaSeleccionada == null) {
            JOptionPane.showMessageDialog(
                mainFrame,
                "Debe seleccionar una materia",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        coordinadorAplicacion.filtrarHistorialPorMateria(materiaSeleccionada.getId());
    }
    
    private void filtrarPorFechaYMateria(){
        Date fechaSeleccionada = dateChooser.getDate();
        MateriaDTO materiaSeleccionada = (MateriaDTO) cmbMaterias.getSelectedItem();

        if (fechaSeleccionada == null || materiaSeleccionada == null) {
            JOptionPane.showMessageDialog(
                mainFrame,
                "Debe seleccionar fecha y materia",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        LocalDate fecha = fechaSeleccionada.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

        coordinadorAplicacion.filtrarHistorialPorFechaYMateria(fecha, materiaSeleccionada.getId());
    }
    
    public void setHistorial(List<CitaDTO> citas) {
        modeloTabla.setRowCount(0);
        
        if (citas == null || citas.isEmpty()) {
            JOptionPane.showMessageDialog(
                mainFrame,
                "No se encontraron citas en el historial",
                "Sin resultados",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        
        for (CitaDTO cita : citas) {
            Object[] fila = {
                cita.getNombreTutor(),
                cita.getNombreMateria(),
                cita.getTema(),
                cita.getFecha(),
                cita.getHora(),
                cita.getEstado()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    
    public void setMaterias(List<MateriaDTO> materias) {
        cmbMaterias.removeAllItems();
        for (MateriaDTO materia : materias) {
            cmbMaterias.addItem(materia);
        }
        cmbMaterias.setSelectedIndex(-1);
    }
    
}
