/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.panels;
import dto.tutorias.TutorDTO;
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
public class PanelTutoresDisponibles extends PanelTutorias {
    
    private Label titulo;
    private JTable tablaTutores;
    private DefaultTableModel modeloTabla;
    private List<TutorDTO> tutores;
    
    public PanelTutoresDisponibles(Tutorias frame, CoordinadorAplicacionTutorias coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
    }
    
    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        
        titulo = new Label("Tutores Disponibles");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));
        
        String[] columnas = {"Nombre Tutor", "Carrera", "Cubículo", ""};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; 
            }
        };
        
        tablaTutores = new JTable(modeloTabla);
        tablaTutores.setFont(Style.LABEL_FONT);
        tablaTutores.setRowHeight(60);
        tablaTutores.getTableHeader().setFont(Style.SUBTITLE_FONT);
        
        tablaTutores.getColumn("").setCellRenderer(new ButtonRenderer());
        tablaTutores.getColumn("").setCellEditor(new ButtonEditorTutores(new JCheckBox()));
        
        JScrollPane scrollPane = new JScrollPane(tablaTutores);
        scrollPane.setMaximumSize(new Dimension(1400, 500));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(scrollPane);
        
        centralPanel.add(Box.createVerticalGlue());
        
        btnBack.addActionListener(e -> mainFrame.showPanel("detallesSolicitud"));
    }
    
    public void setTutores(List<TutorDTO> tutores) {
        this.tutores = tutores;
        modeloTabla.setRowCount(0);
        
        for (TutorDTO tutor : tutores) {
            Object[] fila = {
                tutor.getNombre(),
                tutor.getCarrera(),
                tutor.getCubiculo(),
                "Seleccionar"
            };
            modeloTabla.addRow(fila);
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
    
    
    class ButtonEditorTutores extends DefaultCellEditor {
        private JButton button;
        private boolean clicked;
        private int row;
        
        public ButtonEditorTutores(JCheckBox checkBox) {
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
                TutorDTO tutorSeleccionado = tutores.get(row);
                coordinadorAplicacion.seleccionarTutor(tutorSeleccionado);
                coordinadorAplicacion.mostrarHorariosDisponibles();
            }
            clicked = false;
            return "Seleccionar";
        }
    }
    
}
