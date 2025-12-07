/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.panels;
import dto.tutorias.HorarioDTO;
import presentacion.styles.Label;
import presentacion.styles.Style;
import presentacion.tutorias.jframePrincipal.Tutorias;
import presentacion.tutorias.coordinadorAplicacion.CoordinadorAplicacionTutorias;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author katia
 */
public class PanelHorariosDisponibles extends PanelTutorias {
    
    private Label titulo;
    private Label lblNombreTutor;
    private JTable tablaHorarios;
    private DefaultTableModel modeloTabla;
    private List<HorarioDTO> horarios;
    
    public PanelHorariosDisponibles(Tutorias frame, CoordinadorAplicacionTutorias coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
    }
    
    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        
        titulo = new Label("Horarios Disponibles");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));
        
        lblNombreTutor = new Label("");
        lblNombreTutor.setFont(Style.SUBTITLE_FONT);
        lblNombreTutor.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(lblNombreTutor);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));
        
        String[] columnas = {"Hora", ""};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; 
            }
        };
        
        tablaHorarios = new JTable(modeloTabla);
        tablaHorarios.setFont(Style.LABEL_FONT);
        tablaHorarios.setRowHeight(60);
        tablaHorarios.getTableHeader().setFont(Style.SUBTITLE_FONT);
        
        tablaHorarios.getColumn("").setCellRenderer(new ButtonRenderer());
        tablaHorarios.getColumn("").setCellEditor(new ButtonEditorHorarios(new JCheckBox()));
        
        JScrollPane scrollPane = new JScrollPane(tablaHorarios);
        scrollPane.setMaximumSize(new Dimension(800, 400));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(scrollPane);
        
        centralPanel.add(Box.createVerticalGlue());
        
        btnBack.addActionListener(e -> mainFrame.showPanel("tutoresDisponibles"));
    }
    
    public void setNombreTutor(String nombreTutor) {
        lblNombreTutor.setText(nombreTutor);
    }
    
    public void setHorarios(List<HorarioDTO> horarios) {
        this.horarios = horarios;
        modeloTabla.setRowCount(0);
        
        if (horarios.isEmpty()) {
            JOptionPane.showMessageDialog(
                mainFrame,
                "No hay horarios disponibles para este tutor en la fecha seleccionada",
                "Sin horarios",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            for (HorarioDTO horario : horarios) {
                Object[] fila = {
                    horario.getHora().toString(),
                    "Seleccionar"
                };
                modeloTabla.addRow(fila);
            }
        }
    }
    
    
    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setText("Seleccionar");
            setFont(Style.BUTTON_FONT);
            setBackground(Style.BUTTON_COLOR);
            setForeground(Style.BUTTON_TEXT_COLOR);
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }
    
    
    class ButtonEditorHorarios extends DefaultCellEditor {
        private JButton button;
        private boolean clicked;
        private int row;
        
        public ButtonEditorHorarios(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Seleccionar");
            button.setFont(Style.BUTTON_FONT);
            button.setBackground(Style.BUTTON_COLOR);
            button.setForeground(Style.BUTTON_TEXT_COLOR);
            button.addActionListener(e -> fireEditingStopped());
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            this.row = row;
            clicked = true;
            return button;
        }
        
        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                HorarioDTO horarioSeleccionado = horarios.get(row);
                coordinadorAplicacion.seleccionarHorario(horarioSeleccionado);
                coordinadorAplicacion.mostrarConfirmacionCita();
            }
            clicked = false;
            return "Seleccionar";
        }
    }
}