/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.panels;

import dto.tutorias.CitaDTO;
import presentacion.styles.Label;
import presentacion.styles.Style;
import presentacion.tutorias.coordinadorAplicacion.CoordinadorAplicacionTutorias;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import presentacion.tutorias.jframePrincipal.Tutorias;


public class PanelCitasActivas extends PanelTutorias {
    
    private Label titulo;
    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;
    private List<CitaDTO> citasActivas;
    
    public PanelCitasActivas(Tutorias frame, CoordinadorAplicacionTutorias coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
    }
    
    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        
        titulo = new Label("Citas Activas");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));
        
        String[] columnas = {"Nombre Tutor", "Materia", "Tema", "Fecha", "Hora", ""};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };
        
        tablaCitas = new JTable(modeloTabla);
        tablaCitas.setFont(Style.LABEL_FONT);
        tablaCitas.setRowHeight(60);
        tablaCitas.getTableHeader().setFont(Style.SUBTITLE_FONT);
        tablaCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tablaCitas.getColumn("").setCellRenderer(new ButtonRenderer());
        tablaCitas.getColumn("").setCellEditor(new ButtonEditorCancelar(new JCheckBox()));
        
        JScrollPane scrollPane = new JScrollPane(tablaCitas);
        scrollPane.setMaximumSize(new Dimension(1400, 500));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(scrollPane);
        
        centralPanel.add(Box.createVerticalGlue());
        
        btnBack.addActionListener(e -> mainFrame.showPanel("menuTutorias"));
    }
    
    public void setCitas(List<CitaDTO> citas) {
        this.citasActivas = citas;
        modeloTabla.setRowCount(0);
        if (citas == null || citas.isEmpty()) {
            JOptionPane.showMessageDialog(
                mainFrame,
                "No tienes citas activas",
                "Sin citas",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
//        for (int i = 0; i < citas.size(); i++) {
//            CitaDTO cita = citas.get(i);
//            System.out.println("   [" + i + "] ID: " + cita.getId() + ", Tutor: " + cita.getNombreTutor());
//            
//            Object[] fila = {
//                cita.getNombreTutor(),
//                cita.getNombreMateria(),
//                cita.getTema(),
//                cita.getFecha(),
//                cita.getHora(),
//                "Cancelar"
//            };
//            modeloTabla.addRow(fila);
//        }
        
        for (CitaDTO cita : citas) {
            Object[] fila = {
                cita.getNombreTutor(),
                cita.getNombreMateria(),
                cita.getTema(),
                cita.getFecha(),
                cita.getHora(),
                "Cancelar"
            };
            modeloTabla.addRow(fila);
        }
    }
    

    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setText("Cancelar");
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

    
    class ButtonEditorCancelar extends DefaultCellEditor {
        private JButton button;
        private int row;
        
        public ButtonEditorCancelar(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Cancelar");
            button.setFont(Style.BUTTON_FONT);
            button.setBackground(Style.BUTTON_COLOR);
            button.setForeground(Style.BUTTON_TEXT_COLOR);
            button.addActionListener(e -> {
                stopCellEditing();
                procesarCancelacion();
            });
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            this.row = row;
            return button;
        }
        
        @Override
        public Object getCellEditorValue() {
            return "Cancelar";
        }
        
        private void procesarCancelacion() {
            if (citasActivas == null || row < 0 || row >= citasActivas.size()) {
                JOptionPane.showMessageDialog(
                    mainFrame,
                    "Error: Selección inválida",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            CitaDTO citaSeleccionada = citasActivas.get(row);
            
            if (citaSeleccionada.getId() == null) {
                JOptionPane.showMessageDialog(
                    mainFrame,
                    "Error: La cita no tiene ID asignado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            int confirmacion = JOptionPane.showConfirmDialog(
                mainFrame,
                "¿Seguro que deseas cancelar tu cita?\n\n" +
                "Tutor: " + citaSeleccionada.getNombreTutor() + "\n" +
                "Fecha: " + citaSeleccionada.getFecha() + "\n" +
                "Hora: " + citaSeleccionada.getHora(),
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                coordinadorAplicacion.cancelarCita(citaSeleccionada.getId());
            }
        }
    }
}
