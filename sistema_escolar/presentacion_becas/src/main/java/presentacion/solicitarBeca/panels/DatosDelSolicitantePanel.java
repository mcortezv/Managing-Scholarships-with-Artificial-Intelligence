package presentacion.solicitarBeca.panels;
import presentacion.solicitarBeca.PanelSolicitarBeca;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.CoordinadorAplicacion;
import presentacion.login.exceptions.IDInvalidoException;
import presentacion.solicitarBeca.SolicitarBeca;
import presentacion.solicitarBeca.exceptions.*;
import presentacion.styles.*;
import solicitarBeca.EstudianteDTO;
import javax.swing.*;
import java.awt.*;


/**
 * The type Datos del solicitante panel.
 */
public class DatosDelSolicitantePanel extends PanelSolicitarBeca {
    private Label matricula;
    private Label nombre;
    private Label carrera;
    private Label telefono;
    private Label direccion;
    private Label correo;

    private EstudianteDTO estudiante;
    private final CoordinadorAplicacion coordinadorAplicacion;

    /**
     * Instantiates a new Datos del solicitante panel.
     *
     * @param mainFrame             the main frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public DatosDelSolicitantePanel(SolicitarBeca mainFrame, CoordinadorAplicacion coordinadorAplicacion) {
        super(mainFrame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    private JPanel crearBloque(String labelText, Label valueLabel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Style.PANEL_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        Label tituloCampo = new Label(labelText);
        tituloCampo.setFont(new Font("SansSerif", Font.BOLD, 20));
        tituloCampo.setAlignmentX(Component.CENTER_ALIGNMENT);

        valueLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(tituloCampo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(valueLabel);

        return panel;
    }

    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(40));
        Label titulo = new Label("Datos del Solicitante");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(50));

        matricula = new Label("");
        nombre = new Label("");
        carrera = new Label("");
        telefono = new Label("");
        direccion = new Label("");
        correo = new Label("");

        centralPanel.add(crearFila(
                crearBloque("Matrícula", matricula),
                crearBloque("Nombre", nombre)
        ));

        centralPanel.add(Box.createVerticalStrut(25));

        centralPanel.add(crearFila(
                crearBloque("Carrera", carrera),
                crearBloque("Teléfono", telefono)
        ));

        centralPanel.add(Box.createVerticalStrut(25));

        centralPanel.add(crearFila(
                crearBloque("Dirección", direccion),
                crearBloque("Correo", correo)
        ));

        centralPanel.add(Box.createVerticalStrut(60));

        Button btn_next = new Button("Aceptar");
        btn_next.setAlignmentX(CENTER_ALIGNMENT);

        centralPanel.add(btn_next);
        centralPanel.add(Box.createVerticalStrut(60));

        btnBack.addActionListener(e -> mainFrame.showPanel("detalleBecaPanel"));

        btn_next.addActionListener(e -> {
            try {
                coordinadorAplicacion.mostrarPanelHitorialAcademico();
            } catch (NombresInvalidosException | ApellidoInvalidoException | DireccionInvalidaException |
                     TelefonoInvalidoException | IDInvalidoException ex) {
                JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Solo se aceptan números", "Error de validación", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame, "No se pudo recuperar al estudiante", "Error de datos", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JPanel crearFila(JPanel col1, JPanel col2) {
        JPanel fila = new JPanel();
        fila.setLayout(new GridLayout(1, 2, 50, 0));
        fila.setMaximumSize(new Dimension(1000, 180));
        fila.setBackground(Style.PANEL_COLOR);
        fila.add(col1);
        fila.add(col2);
        return fila;
    }

    /**
     * Sets estudiante.
     *
     * @param estudiante the estudiante
     */
    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
        matricula.setText(String.valueOf(estudiante.getMatricula()));
        nombre.setText(estudiante.getNombre());
        carrera.setText(estudiante.getCarrera());
        telefono.setText(estudiante.getTelefono());
        direccion.setText(estudiante.getDireccion());
        correo.setText(estudiante.getCorreo());
    }
}
