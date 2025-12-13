package presentacion.solicitarBeca.panels;
import presentacion.solicitarBeca.PanelSolicitarBeca;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.HistorialAcademicoDTO;
import presentacion.CoordinadorAplicacion;
import presentacion.solicitarBeca.SolicitarBeca;
import presentacion.styles.*;
import presentacion.styles.Button;
import presentacion.styles.Label;
import javax.swing.*;

/**
 * The type Historial academico panel.
 *
 * @author Cortez, Manuel;
 */
public class HistorialAcademicoPanel extends PanelSolicitarBeca {
    private Label titulo;
    private Label carrera;
    private ComboBox<String> comboCarrera;
    private Label cargaAcademica;
    private ComboBox<String> comboCargaAcademica;
    private Label semestre;
    private ComboBox<Integer> comboSemestre;
    private Button botonSiguiente;
    private CoordinadorAplicacion coordinadorAplicacion;

    /**
     * Instantiates a new Historial academico panel.
     *
     * @param mainFrame             the main frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public HistorialAcademicoPanel(SolicitarBeca mainFrame, CoordinadorAplicacion coordinadorAplicacion) {
        super(mainFrame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {

        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        titulo = new Label("Historial Academico");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));

        carrera = new Label("Que carrera estas cursando?");
        carrera.setFont(Style.LABEL_FONT);
        carrera.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(carrera);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        String[] carreras = new String[]{"INGENIERIA", "LICENCIATURA", "MAESTRIA", "DOCTORADO"};
        comboCarrera = new ComboBox<>(carreras);
        comboCarrera.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(comboCarrera);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        carrera = new Label("Carga Academica");
        carrera.setFont(Style.LABEL_FONT);
        carrera.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(carrera);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        String[] carga = new String[]{"25%", "50%", "75%", "100%"};
        comboCargaAcademica = new ComboBox<>(carga);
        comboCargaAcademica.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(comboCargaAcademica);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        semestre = new Label("Semestre");
        semestre.setFont(Style.LABEL_FONT);
        semestre.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(semestre);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        Integer[] semestres = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        comboSemestre = new ComboBox<>(semestres);
        comboSemestre.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(comboSemestre);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        botonSiguiente = new Button("Continuar");
        botonSiguiente.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(botonSiguiente);

        btnBack.addActionListener(e -> mainFrame.showPanel("datosDelSolicitantePanel"));

        botonSiguiente.addActionListener(e -> {
            try {
                String carreraSeleccionada = (String) comboCarrera.getSelectedItem();
                String cargaStr = (String) comboCargaAcademica.getSelectedItem();
                double cargaAcademicaSeleccionada = Double.parseDouble(cargaStr.replace("%", ""));
                int semestreSeleccionado = (int) comboSemestre.getSelectedItem();
                HistorialAcademicoDTO histAcademicoDTO = new HistorialAcademicoDTO();
                histAcademicoDTO.setMatriculaEstudiante(coordinadorAplicacion.getEstudianteLogueado().getMatricula());
                histAcademicoDTO.setCarrera(carreraSeleccionada);
                histAcademicoDTO.setCargaAcademica(cargaAcademicaSeleccionada);
                histAcademicoDTO.setSemestre(semestreSeleccionado);
                coordinadorAplicacion.procesarHistorialAcademico(histAcademicoDTO);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame,ex.getMessage(),"Error de recuperacion", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
