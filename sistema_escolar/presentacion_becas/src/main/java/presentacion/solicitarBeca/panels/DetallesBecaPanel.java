package presentacion.solicitarBeca.panels;
import presentacion.solicitarBeca.PanelSolicitarBeca;
import solicitarBeca.BecaDTO;
import presentacion.CoordinadorAplicacion;
import java.awt.*;
import static java.awt.Font.BOLD;
import javax.swing.*;
import presentacion.solicitarBeca.SolicitarBeca;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.styles.Style;


/**
 * The type Detalles beca panel.
 */
public class DetallesBecaPanel extends PanelSolicitarBeca {
    private Label titulo;
    private Label lblPromedio, lblPeriodo, lblTipo;
    private JTextPane txtDescripcion;
    private Button botonSiguiente;
    private BecaDTO beca;
    private JPanel lista;
    private JScrollPane scroll;

    /**
     * Instantiates a new Detalles beca panel.
     *
     * @param mainFrame             the main frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public DetallesBecaPanel(SolicitarBeca mainFrame, CoordinadorAplicacion coordinadorAplicacion) {
        super(mainFrame, coordinadorAplicacion);
    }

    @Override
    public void startComponents() {
        centralPanel.setLayout(new BorderLayout());
        centralPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        lista = new JPanel();
        lista.setOpaque(false);
        lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
        lista.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo = new Label("Beca Seleccionada");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lista.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        lista.add(titulo);
        lista.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));


        lblTipo = new Label("");
        lista.add(crearCampoTexto("TIPO DE BECA", lblTipo));
        txtDescripcion = crearDescripcionPane();
        lista.add(crearCampoConComponente("DESCRIPCIÓN", txtDescripcion));
        lblPromedio = new Label("");
        lista.add(crearCampoTexto("PROMEDIO MÍNIMO", lblPromedio));
        lblPeriodo = new Label("");
        lista.add(crearCampoTexto("PERIODO", lblPeriodo));
        lista.add(Box.createVerticalStrut(40));


        scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        botonSiguiente = new Button("Solicitar");
        botonSiguiente.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(botonSiguiente);
        bottomPanel.add(Box.createVerticalStrut(20));


        centralPanel.add(scroll, BorderLayout.CENTER);
        centralPanel.add(bottomPanel, BorderLayout.SOUTH);


        botonSiguiente.addActionListener(e -> {
            coordinadorAplicacion.setBecaSeleccionadaDTO(beca);
            coordinadorAplicacion.iniciarSolicitud();
            coordinadorAplicacion.procesarDatosSolicitante();
        });

        btnBack.addActionListener(e -> mainFrame.showPanel("listadoBecasDisponiblesPanel"));
    }


    private JPanel crearCampoTexto(String titulo, Label lblValor) {
        JPanel campo = new JPanel();
        campo.setOpaque(false);
        campo.setLayout(new BoxLayout(campo, BoxLayout.Y_AXIS));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Style.LABEL_FONT.deriveFont(BOLD));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        campo.add(lblTitulo);
        campo.add(Box.createVerticalStrut(5));
        campo.add(lblValor);
        return campo;
    }

    private JPanel crearCampoConComponente(String titulo, Component comp) {
        JPanel campo = new JPanel();
        campo.setOpaque(false);
        campo.setLayout(new BoxLayout(campo, BoxLayout.Y_AXIS));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Style.LABEL_FONT.deriveFont(BOLD));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel cont = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cont.setOpaque(false);
        cont.add(comp);
        campo.add(lblTitulo);
        campo.add(Box.createVerticalStrut(10));
        campo.add(cont);
        return campo;
    }


    private JTextPane crearDescripcionPane() {
        JTextPane tp = new JTextPane();
        tp.setContentType("text/html");
        tp.setEditable(false);
        tp.setOpaque(false);
        tp.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        tp.setFont(Style.LABEL_FONT);
        tp.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        tp.setMinimumSize(new Dimension(0, 0));
        tp.setPreferredSize(null);
        tp.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        return tp;
    }


    /**
     * Cargar beca.
     *
     * @param becaDTO the beca dto
     */
    public void cargarBeca(BecaDTO becaDTO) {
        this.beca = becaDTO;
        titulo.setText(becaDTO.getNombre());
        txtDescripcion.setText(
                "<html><body style='"
                        + "width: 600px;"
                        + "text-align: center;"
                        + "font-family: \"Segoe UI Semilight\", \"Segoe UI Light\", \"Segoe UI\", sans-serif;"
                        + "font-size: 20px;"
                        + "line-height: 1.35;"
                        + "color: rgb("
                        + Style.TEXT_COLOR.getRed() + ","
                        + Style.TEXT_COLOR.getGreen() + ","
                        + Style.TEXT_COLOR.getBlue()
                        + ");"
                        + "'>"
                        + becaDTO.getDescripcion().replace("\n", "<br>")
                        + "</body></html>"
        );
        lblPromedio.setText(String.valueOf(becaDTO.getRequisitos().getPromedioMinimo()));
        lblPeriodo.setText(
                becaDTO.getFechaInicio().toString() + " → " + becaDTO.getFechaFin().toString()
        );
        lblTipo.setText(becaDTO.getTipo());
    }
}
