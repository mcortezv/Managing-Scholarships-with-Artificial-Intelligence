package presentacion.solicitarBeca.panels;
import presentacion.CoordinadorAplicacion;
import presentacion.solicitarBeca.PanelSolicitarBeca;
import presentacion.solicitarBeca.SolicitarBeca;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.styles.Style;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.SolicitudDTO;
import solicitarBeca.TutorDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The type Resumen final panel.
 */
public class ResumenFinalPanel extends PanelSolicitarBeca {

    private Label titulo;
    private Label lbl_beca_solicitada;
    private Label lbl_datos_solicitante;
    private Label lbl_datos_tutor;
    private Label lbl_info_socioeconomica;
    private Label lbl_documentos;

    private Button btnContinuar;

    private Label lbl_beca_response;
    private CoordinadorAplicacion coordinadorAplicacion;

    private SolicitudDTO solicitudDTO;
    private TutorDTO tutorDTO;
    private EstudianteDTO estudianteDTO;

    /**
     * The Lbl tutor 1.
     */
    Label lbl_tutor_1;
    /**
     * The Lbl tutor 2.
     */
    Label lbl_tutor_2;
    /**
     * The Lbl info 2.
     */
    Label lbl_info_2;
    /**
     * The Lbl info 3.
     */
    Label lbl_info_3;
    /**
     * The Lbl info 5.
     */
    Label lbl_info_5;
    /**
     * The Lbl dependencia.
     */
    Label lblDependencia;
    /**
     * The Lbl solicitantea nombre.
     */
    Label lblSolicitanteaNombre;

    /**
     * Instantiates a new Resumen final panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public ResumenFinalPanel(SolicitarBeca frame, CoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        titulo = new Label("Resumen Final");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));

        centralPanel.setBorder(new EmptyBorder(0, 80, 0, 80));

        Font sectionFont = new Font("Segoe UI", Font.BOLD, 28);
        Font dataFont = new Font("Segoe UI", Font.PLAIN, 20);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 2, 60, 0));
        contentPanel.setBackground(Style.PANEL_COLOR);
        contentPanel.setAlignmentX(CENTER_ALIGNMENT);

        JPanel leftColumn = new JPanel();
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        leftColumn.setBackground(Style.PANEL_COLOR);

        JPanel cardBeca = createCard();
        lbl_beca_solicitada = new Label("Beca Solicitada:");
        lbl_beca_solicitada.setFont(sectionFont);

        lbl_beca_response = new Label("...");
        lbl_beca_response.setFont(dataFont);

        cardBeca.add(lbl_beca_solicitada);
        cardBeca.add(Box.createVerticalStrut(8));
        cardBeca.add(lbl_beca_response);

        leftColumn.add(cardBeca);
        leftColumn.add(Box.createVerticalStrut(40));

        JPanel cardSolicitante = createCard();
        lbl_datos_solicitante = new Label("Datos del Solicitante:");
        lbl_datos_solicitante.setFont(sectionFont);

        lblSolicitanteaNombre = new Label("...");
        lblSolicitanteaNombre.setFont(dataFont);

        Label lbl_solicitante_2 = new Label("21 años");
        lbl_solicitante_2.setFont(dataFont);

        cardSolicitante.add(lbl_datos_solicitante);
        cardSolicitante.add(Box.createVerticalStrut(8));
        cardSolicitante.add(lblSolicitanteaNombre);
        cardSolicitante.add(lbl_solicitante_2);

        leftColumn.add(cardSolicitante);
        leftColumn.add(Box.createVerticalStrut(40));

        JPanel cardTutor = createCard();
        lbl_datos_tutor = new Label("Datos del Tutor:");
        lbl_datos_tutor.setFont(sectionFont);

        lbl_tutor_1 = new Label("...");
        lbl_tutor_1.setFont(dataFont);

        lbl_tutor_2 = new Label("...");
        lbl_tutor_2.setFont(dataFont);

        Label lbl_tutor_3 = new Label("");
        lbl_tutor_3.setFont(dataFont);

        cardTutor.add(lbl_datos_tutor);
        cardTutor.add(Box.createVerticalStrut(8));
        cardTutor.add(lbl_tutor_1);
        cardTutor.add(lbl_tutor_2);
        cardTutor.add(lbl_tutor_3);

        leftColumn.add(cardTutor);
        leftColumn.add(Box.createVerticalGlue());

        JPanel rightColumn = new JPanel();
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
        rightColumn.setBackground(Style.PANEL_COLOR);

        JPanel cardSocio = createCard();

        lbl_info_socioeconomica = new Label("Información Socioeconómica:");
        lbl_info_socioeconomica.setFont(sectionFont);

        Label lbl_info_1 = new Label("Deudas:");
        lbl_info_1.setFont(dataFont);

        lblDependencia = new Label("...");
        lblDependencia.setFont(dataFont);

        lbl_info_2 = new Label("Ingreso mensual familiar:");
        lbl_info_2.setFont(dataFont);

        lbl_info_3 = new Label("...");
        lbl_info_3.setFont(dataFont);

        Label lbl_info_4 = new Label("Generas ingreso:");
        lbl_info_4.setFont(dataFont);

        lbl_info_5 = new Label("...");
        lbl_info_5.setFont(dataFont);

        cardSocio.add(lbl_info_socioeconomica);
        cardSocio.add(Box.createVerticalStrut(8));
        cardSocio.add(lbl_info_1);
        cardSocio.add(lblDependencia);
        cardSocio.add(lbl_info_2);
        cardSocio.add(lbl_info_3);
        cardSocio.add(lbl_info_4);
        cardSocio.add(lbl_info_5);

        rightColumn.add(cardSocio);
        rightColumn.add(Box.createVerticalStrut(40));

        JPanel cardDocs = createCard();
        lbl_documentos = new Label("Documentos:");
        lbl_documentos.setFont(sectionFont);

        cardDocs.add(lbl_documentos);
        cardDocs.add(Box.createVerticalStrut(10));

        cardDocs.add(createDocumentLabel("CURP", dataFont));
        cardDocs.add(createDocumentLabel("IDENTIFICACIÓN OFICIAL", dataFont));
        cardDocs.add(createDocumentLabel("COMPROBANTE DE INSCRIPCIÓN", dataFont));
        cardDocs.add(createDocumentLabel("KÁRDEX", dataFont));
        cardDocs.add(createDocumentLabel("COMPROBANTE INGRESOS PADRE", dataFont));

        rightColumn.add(cardDocs);
        rightColumn.add(Box.createVerticalGlue());

        contentPanel.add(leftColumn);
        contentPanel.add(rightColumn);

        centralPanel.add(contentPanel);
        centralPanel.add(Box.createVerticalStrut(40));

        btnContinuar = new Button("Continuar");
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(btnContinuar);

        btnBack.addActionListener(e -> mainFrame.showPanel("subirDocumentosPanel"));
        btnContinuar.addActionListener(e -> {
            coordinadorAplicacion.enviarSolicitudAGobierno();
            mainFrame.showPanel("confirmacionPanel");
        });
    }

    private JPanel createCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Style.PANEL_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(20, 25, 20, 25)
        ));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        return card;
    }

    /**
     * Cargar resumen.
     *
     * @param solicitudDTO the solicitud dto
     */
    public void cargarResumen(SolicitudDTO solicitudDTO){
        this.solicitudDTO = solicitudDTO;
        this.tutorDTO = solicitudDTO.getEstudiante().getTutor();
        this.estudianteDTO = solicitudDTO.getEstudiante();

        lbl_beca_response.setText(solicitudDTO.getBeca().getNombre());
        lbl_info_3.setText(String.valueOf(solicitudDTO.getInformacionSocioeconomica().getIngresoTotalFamilarMensual()));
        lbl_tutor_1.setText(tutorDTO.getNombre());
        lbl_tutor_2.setText(String.valueOf(tutorDTO.getParentesco()));
        lblDependencia.setText(solicitudDTO.getInformacionSocioeconomica().isDeudas() ? "Sí" : "No");
        lbl_info_5.setText(solicitudDTO.getInformacionSocioeconomica().isTrabajo() ? "Sí" : "No");
        lblSolicitanteaNombre.setText(estudianteDTO.getNombre());
    }

    private Label createDocumentLabel(String text, Font font) {
        String checkMark = "\u2713  ";
        Label label = new Label(checkMark + text);
        label.setFont(font);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
}
