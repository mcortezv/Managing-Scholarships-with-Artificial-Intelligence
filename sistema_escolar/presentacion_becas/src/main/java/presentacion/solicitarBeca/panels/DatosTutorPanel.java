package presentacion.solicitarBeca.panels;
import presentacion.solicitarBeca.PanelSolicitarBeca;
import solicitarBeca.TutorDTO;
import presentacion.CoordinadorAplicacion;
import presentacion.solicitarBeca.SolicitarBeca;
import presentacion.styles.*;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.styles.TextField;
import presentacion.login.exceptions.IDInvalidoException;
import presentacion.solicitarBeca.exceptions.ApellidoInvalidoException;
import presentacion.solicitarBeca.exceptions.NombresInvalidosException;
import presentacion.solicitarBeca.exceptions.TelefonoInvalidoException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The type Datos tutor panel.
 */
public class DatosTutorPanel extends PanelSolicitarBeca {
    private Label lbl_responsable;
    private TextField field_nombre;
    private TextField field_telefono;
    private TextField field_email;
    private Button btnContinuar;
    private TextField field_apellido_materno;
    private TextField field_apellido_paterno;
    private TextField field_direccion;
    private ComboBox<String> comboParentesco;
    private CoordinadorAplicacion coordinadorAplicacion;

    /**
     * Instantiates a new Datos tutor panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public DatosTutorPanel(SolicitarBeca frame, CoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    private JPanel crearDosColumnas(String labelText, TextField field) {
        JPanel panel = new JPanel();
        panel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Style.PANEL_COLOR);
        Label label = new Label(labelText);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setColumns(20);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height + 10));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        panel.add(field);
        panel.setMaximumSize(new Dimension(800, 80));
        return panel;
    }

    @Override
    public void startComponents() {
        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBackground(Style.PANEL_COLOR);
        contenido.setBorder(new EmptyBorder(50, 50, 50, 50));
        Label titulo = new Label("Datos del Tutor");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(titulo);
        contenido.add(Box.createVerticalStrut(30));

        lbl_responsable = new Label("¿Quién es el responsable de pagar tus estudios?");
        lbl_responsable.setFont(Style.LABEL_FONT);
        lbl_responsable.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lbl_responsable);
        contenido.add(Box.createVerticalStrut(10));

        String[] parentescoOpciones = new String[]{"PADRE", "MADRE", "ABUELO", "ABUELA", "HERMANO", "HERMANA"
                , "TIO", "TIA", "PADRASTRO", "MADRE", "TUTOR_OFICIAL"};
        comboParentesco = new ComboBox<>(parentescoOpciones);
        comboParentesco.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboParentesco.setMaximumSize(new Dimension(300, 30));
        contenido.add(comboParentesco);
        contenido.add(Box.createVerticalStrut(30));

        field_nombre = new TextField(20);
        field_apellido_paterno = new TextField(20);
        field_apellido_materno = new TextField(20);
        field_telefono = new TextField(20);
        field_email = new TextField(20);
        field_direccion = new TextField(20);

        JPanel fila1 = new JPanel(new GridLayout(1, 2, 40, 0));
        fila1.setBackground(Style.PANEL_COLOR);
        fila1.add(crearDosColumnas("Nombres:", field_nombre));
        fila1.add(crearDosColumnas("Apellido Paterno:", field_apellido_paterno));
        fila1.setMaximumSize(new Dimension(800, 80));
        contenido.add(fila1);
        contenido.add(Box.createVerticalStrut(20));



        JPanel fila2 = new JPanel(new GridLayout(1, 2, 40, 0));
        fila2.setBackground(Style.PANEL_COLOR);
        fila2.add(crearDosColumnas("Apellido Materno:", field_apellido_materno));
        fila2.add(crearDosColumnas("Teléfono:", field_telefono));
        fila2.setMaximumSize(new Dimension(800, 80));
        contenido.add(fila2);
        contenido.add(Box.createVerticalStrut(20));



        JPanel fila3 = new JPanel(new GridLayout(1, 2, 40, 0));
        fila3.setBackground(Style.PANEL_COLOR);
        fila3.add(crearDosColumnas("Direccion:", field_direccion));
        fila3.add(crearDosColumnas("Email:", field_email));
        fila3.setMaximumSize(new Dimension(800, 80));
        contenido.add(fila3);
        contenido.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        btnContinuar = new Button("Continuar");
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(btnContinuar);
        contenido.add(Box.createVerticalGlue());

        centralPanel.setLayout(new BorderLayout());
        centralPanel.removeAll();
        centralPanel.add(contenido, BorderLayout.NORTH);

        btnBack.addActionListener(e -> mainFrame.showPanel("historialAcademicoPanel"));
        btnContinuar.addActionListener(e -> {
            try {
                String parentesco = (String) comboParentesco.getSelectedItem();
                String nombre = field_nombre.getText();
                String apPat = field_apellido_paterno.getText();
                String apMat = field_apellido_materno.getText();
                String telefono = field_telefono.getText();
                String direccion = field_direccion.getText();
                String correo = field_email.getText();
                TutorDTO tutorDTO = new TutorDTO();
                tutorDTO.setNombre(nombre);
                tutorDTO.setParentesco(parentesco);
                tutorDTO.setApellidoMaterno(apMat);
                tutorDTO.setApellidoPaterno(apPat);
                tutorDTO.setTelefono(telefono);
                tutorDTO.setDireccion(direccion);
                tutorDTO.setCorreo(correo);
                coordinadorAplicacion.procesarDatosTutor(tutorDTO);
            } catch (NombresInvalidosException | ApellidoInvalidoException | TelefonoInvalidoException | IDInvalidoException ex) {
                JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Solo se aceptan números", "Error de validación", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame,"No se pudo crear el Tutor","Error de datos", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}