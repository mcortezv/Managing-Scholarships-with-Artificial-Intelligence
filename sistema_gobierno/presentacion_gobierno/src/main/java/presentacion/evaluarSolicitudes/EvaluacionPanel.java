package presentacion.evaluarSolicitudes;
import dtoGobierno.BecaDTO;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import presentacion.coordinacion.MainFrame;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.styles.Button;
import presentacion.styles.ComboBox;
import presentacion.styles.Label;
import presentacion.styles.Panel;
import presentacion.styles.Style;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Evaluacion panel.
 *
 * @author Cortez, Manuel
 */
public class EvaluacionPanel extends Panel {
    private Label titulo;
    private Button next;
    private Button previous;
    private Button btnGenerar;
    private Button btnResolver;
    private List<SolicitudDTO> solicitudes;
    private SolicitudDTO solicitudActual;
    private int indiceActual;
    private JTextArea solicitudTxtArea;
    private JTextArea evaluacionTxtArea;
    private ComboBox<String> comboManual;
    private ICoordinadorAplicacion coordinadorAplicacion;
    private Label contadorLabel;

    /**
     * Instantiates a new Evaluacion panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public EvaluacionPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
        this.indiceActual = 0;
    }

    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new BoxLayout(tituloPanel, BoxLayout.X_AXIS));
        tituloPanel.setOpaque(false);
        tituloPanel.setAlignmentX(CENTER_ALIGNMENT);
        tituloPanel.setMaximumSize(new Dimension(800, 60));

        titulo = new Label("Evaluación de Becas");
        titulo.setFont(Style.TITLE_FONT);
        tituloPanel.add(Box.createHorizontalGlue());
        tituloPanel.add(titulo);
        tituloPanel.add(Box.createHorizontalGlue());

        centralPanel.add(tituloPanel);
        centralPanel.add(Box.createVerticalStrut(10));

        contadorLabel = new Label("Solicitud 0 de 0");
        contadorLabel.setFont(Style.LABEL_FONT.deriveFont(18f));
        contadorLabel.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(contadorLabel);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));

        JPanel main = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        main.setMaximumSize(new Dimension(1450, 650));
        main.setOpaque(false);
        main.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel left = crearPanelIzquierdo();
        JPanel right = crearPanelDerecho();

        main.add(left);
        main.add(right);
        centralPanel.add(main);

        configurarEventos();
    }

    private JPanel crearPanelIzquierdo() {
        JPanel left = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 25, 25);
            }
        };
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setPreferredSize(new Dimension(650, 560));
        left.setOpaque(false);
        left.add(Box.createVerticalStrut(25));

        Label detallesSubtitulo = new Label("Detalles de Solicitud");
        detallesSubtitulo.setAlignmentX(CENTER_ALIGNMENT);
        detallesSubtitulo.setFont(Style.SUBTITLE_FONT);
        left.add(detallesSubtitulo);
        left.add(Box.createVerticalStrut(20));

        solicitudTxtArea = new JTextArea(17, 36);
        solicitudTxtArea.setFont(Style.INPUT_FONT);
        solicitudTxtArea.setLineWrap(true);
        solicitudTxtArea.setForeground(new Color(240, 240, 240));
        solicitudTxtArea.setWrapStyleWord(true);
        solicitudTxtArea.setOpaque(false);
        solicitudTxtArea.setEditable(false);
        solicitudTxtArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane solicitudScrollPane = new JScrollPane(solicitudTxtArea);
        solicitudScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        solicitudScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        solicitudScrollPane.setBorder(BorderFactory.createEmptyBorder());
        solicitudScrollPane.setBackground(Color.DARK_GRAY);
        solicitudScrollPane.getViewport().setOpaque(false);
        solicitudScrollPane.getVerticalScrollBar().setUI(crearScrollBarUI());

        JPanel solicitudRoundedPanel = crearPanelContenedor();
        solicitudRoundedPanel.add(solicitudScrollPane, BorderLayout.CENTER);
        solicitudRoundedPanel.setMaximumSize(new Dimension(600, 420));

        left.add(solicitudRoundedPanel);
        left.add(Box.createVerticalStrut(20));

        JPanel paginacion = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        paginacion.setMaximumSize(new Dimension(500, 70));
        paginacion.setOpaque(false);
        paginacion.setAlignmentX(Component.CENTER_ALIGNMENT);

        previous = new Button("Anterior");
        next = new Button("Siguiente");

        paginacion.add(previous);
        paginacion.add(next);
        left.add(paginacion);
        left.add(Box.createVerticalStrut(20));

        return left;
    }

    private JPanel crearPanelDerecho() {
        JPanel right = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(35, 42, 44),
                        0, getHeight(), new Color(45, 52, 54)
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(new Color(70, 70, 70));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 25, 25);
            }
        };
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setPreferredSize(new Dimension(650, 560));
        right.setOpaque(false);
        right.add(Box.createVerticalStrut(20));

        Label evaluacionSubtitulo = new Label("Evaluación Automática");
        evaluacionSubtitulo.setAlignmentX(CENTER_ALIGNMENT);
        evaluacionSubtitulo.setFont(Style.SUBTITLE_FONT);
        right.add(evaluacionSubtitulo);
        right.add(Box.createVerticalStrut(15));

        JPanel modelWrapper = crearPanelModelo();
        right.add(modelWrapper);
        right.add(Box.createVerticalStrut(15));

        btnGenerar = new Button("Generar Evaluación");
        btnGenerar.setAlignmentX(CENTER_ALIGNMENT);
        right.add(btnGenerar);
        right.add(Box.createVerticalStrut(20));

        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(600, 2));
        separator.setForeground(new Color(70, 70, 70));
        right.add(separator);
        right.add(Box.createVerticalStrut(15));

        Label evaluacionManual = new Label("Evaluación Manual");
        evaluacionManual.setFont(Style.SUBTITLE_FONT);
        evaluacionManual.setAlignmentX(CENTER_ALIGNMENT);
        right.add(evaluacionManual);
        right.add(Box.createVerticalStrut(15));

        JPanel manualWrapper = crearPanelDecision();
        right.add(manualWrapper);
        right.add(Box.createVerticalStrut(15));

        evaluacionTxtArea = new JTextArea(7, 30);
        evaluacionTxtArea.setFont(Style.INPUT_FONT);
        evaluacionTxtArea.setLineWrap(true);
        evaluacionTxtArea.setWrapStyleWord(true);
        evaluacionTxtArea.setOpaque(false);
        evaluacionTxtArea.setForeground(new Color(50, 50, 50));
        evaluacionTxtArea.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JScrollPane scrollPane = new JScrollPane(evaluacionTxtArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUI(crearScrollBarUI());

        JPanel roundedPanel = crearPanelContenedor();
        roundedPanel.add(scrollPane, BorderLayout.CENTER);
        roundedPanel.setMaximumSize(new Dimension(500, 160));
        right.add(roundedPanel);
        right.add(Box.createVerticalStrut(15));

        btnResolver = new Button("Resolver Solicitud");
        btnResolver.setAlignmentX(CENTER_ALIGNMENT);
        right.add(btnResolver);
        right.add(Box.createVerticalStrut(15));

        return right;
    }

    private JPanel crearPanelModelo() {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        wrapper.setMaximumSize(new Dimension(500, 50));
        wrapper.setOpaque(false);

        Label label = new Label("Modelo:");
        ComboBox<String> comboModelo = new ComboBox<>(new String[]{"nvem v1.0"});

        wrapper.add(label);
        wrapper.add(comboModelo);
        return wrapper;
    }

    private JPanel crearPanelDecision() {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        wrapper.setMaximumSize(new Dimension(500, 50));
        wrapper.setOpaque(false);

        Label label = new Label("Decisión:");
        comboManual = new ComboBox<>(new String[]{"ACEPTADA", "RECHAZADA", "DEVUELTA"});

        wrapper.add(label);
        wrapper.add(comboManual);
        return wrapper;
    }

    private JPanel crearPanelContenedor() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 30));
                g2.fillRoundRect(3, 3, getWidth()-3, getHeight()-3, 20, 20);
                g2.setColor(new Color(248, 249, 250));
                g2.fillRoundRect(0, 0, getWidth()-3, getHeight()-3, 20, 20);
            }
        };
    }

    private BasicScrollBarUI crearScrollBarUI() {
        return new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(120, 120, 120);
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return crearBotonInvisible();
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return crearBotonInvisible();
            }
            private JButton crearBotonInvisible() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        };
    }

    private void configurarEventos() {
        next.addActionListener(e -> {
            if (solicitudes != null && !solicitudes.isEmpty() &&
                    indiceActual < solicitudes.size() - 1) {
                indiceActual++;
                solicitudActual = solicitudes.get(indiceActual);
                actualizarVisualizacionSolicitud();
                limpiarCamposEvaluacion();
                actualizarEstadoBotones();
            }
        });

        previous.addActionListener(e -> {
            if (solicitudes != null && !solicitudes.isEmpty() && indiceActual > 0) {
                indiceActual--;
                solicitudActual = solicitudes.get(indiceActual);
                actualizarVisualizacionSolicitud();
                limpiarCamposEvaluacion();
                actualizarEstadoBotones();
            }
        });

        btnGenerar.addActionListener(e -> {
            if (solicitudActual == null) {
                JOptionPane.showMessageDialog(mainFrame,
                        "No hay solicitud seleccionada",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            coordinadorAplicacion.evaluarAutomatica(solicitudActual);
        });

        btnResolver.addActionListener(e -> {
            if (solicitudActual == null) {
                JOptionPane.showMessageDialog(mainFrame,
                        "No hay solicitud seleccionada",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String decision = (String) comboManual.getSelectedItem();
            String motivo = evaluacionTxtArea.getText().trim();

            if (motivo.isEmpty() || motivo.length() < 10) {
                JOptionPane.showMessageDialog(mainFrame,
                        "El motivo debe tener al menos 10 caracteres",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ResolucionDTO resolucion = new ResolucionDTO();
            resolucion.setSolicitud(solicitudActual);
            resolucion.setDecision(decision);
            resolucion.setMotivo(motivo);
            resolucion.setFechaEvaluacion(LocalDate.now());
            coordinadorAplicacion.evaluarManual(resolucion);
        });

        btnBack.addActionListener(e -> {
            coordinadorAplicacion.iniciarEvaluarConvocatoria();
        });
    }

    /**
     * Sets solicitudes.
     *
     * @param solicitudes the solicitudes
     */
    public void setSolicitudes(List<SolicitudDTO> solicitudes) {
        this.solicitudes = solicitudes != null ? new ArrayList<>(solicitudes) : new ArrayList<>();

        if (!this.solicitudes.isEmpty()) {
            indiceActual = 0;
            solicitudActual = this.solicitudes.get(0);
            actualizarVisualizacionSolicitud();
            limpiarCamposEvaluacion();
            actualizarEstadoBotones();
        } else {
            solicitudActual = null;
            indiceActual = 0;
            solicitudTxtArea.setText("No hay solicitudes activas para evaluar");
            limpiarCamposEvaluacion();
            deshabilitarControles();
        }
        actualizarContador();
    }

    /**
     * Sets beca actual.
     *
     * @param beca the beca
     */
    public void setBecaActual(BecaDTO beca) {
        if (beca != null) {
            titulo.setText("Evaluación " + beca.getNombre());
        }
    }

    /**
     * Avanzar siguiente solicitud.
     */
    public void avanzarSiguienteSolicitud() {
        if (solicitudes == null || solicitudes.isEmpty() || solicitudActual == null) {
            coordinadorAplicacion.evaluarOtraSolicitud();
            return;
        }
        solicitudes.remove(indiceActual);
        if (solicitudes.isEmpty()) {
            coordinadorAplicacion.finalizarEvaluacion();
            return;
        }
        if (indiceActual >= solicitudes.size()) {
            indiceActual = solicitudes.size() - 1;
        }
        solicitudActual = solicitudes.get(indiceActual);
        actualizarVisualizacionSolicitud();
        limpiarCamposEvaluacion();
        actualizarEstadoBotones();
        actualizarContador();
    }

    private void actualizarVisualizacionSolicitud() {
        if (solicitudActual != null) {
            solicitudTxtArea.setText(solicitudActual.toString());
            solicitudTxtArea.setCaretPosition(0);
        }
    }

    private void limpiarCamposEvaluacion() {
        evaluacionTxtArea.setText("");
        comboManual.setSelectedIndex(0);
    }

    private void actualizarEstadoBotones() {
        if (solicitudes != null && !solicitudes.isEmpty()) {
            previous.setEnabled(indiceActual > 0);
            next.setEnabled(indiceActual < solicitudes.size() - 1);
            btnGenerar.setEnabled(true);
            btnResolver.setEnabled(true);
        } else {
            deshabilitarControles();
        }
    }

    private void actualizarContador() {
        if (solicitudes != null && !solicitudes.isEmpty()) {
            contadorLabel.setText(String.format("Solicitud %d de %d",
                    indiceActual + 1, solicitudes.size()));
        } else {
            contadorLabel.setText("Solicitud 0 de 0");
        }
    }

    private void deshabilitarControles() {
        previous.setEnabled(false);
        next.setEnabled(false);
        btnGenerar.setEnabled(false);
        btnResolver.setEnabled(false);
    }

    /**
     * Mostrar formulario evaluacion manual.
     *
     * @param solicitud the solicitud
     */
    public void mostrarFormularioEvaluacionManual(SolicitudDTO solicitud) {
        this.solicitudActual = solicitud;
        actualizarVisualizacionSolicitud();

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this),
                "Evaluación Manual", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Label lblDecision = new Label("Decisión:");
        lblDecision.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblDecision);
        panel.add(Box.createVerticalStrut(10));

        ComboBox<String> comboDecision = new ComboBox<>(
                new String[]{"ACEPTADA", "RECHAZADA", "DEVUELTA"});
        comboDecision.setMaximumSize(new Dimension(400, 50));
        comboDecision.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(comboDecision);
        panel.add(Box.createVerticalStrut(20));

        Label lblMotivo = new Label("Motivo (mínimo 10 caracteres):");
        lblMotivo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblMotivo);
        panel.add(Box.createVerticalStrut(10));

        JTextArea txtMotivo = new JTextArea(5, 30);
        txtMotivo.setLineWrap(true);
        txtMotivo.setWrapStyleWord(true);
        txtMotivo.setFont(Style.INPUT_FONT);
        JScrollPane scrollMotivo = new JScrollPane(txtMotivo);
        scrollMotivo.setMaximumSize(new Dimension(400, 150));
        scrollMotivo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(scrollMotivo);
        panel.add(Box.createVerticalStrut(20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        Button btnCancelar = new Button("Cancelar");
        Button btnGuardar = new Button("Guardar");

        btnCancelar.addActionListener(e -> dialog.dispose());

        btnGuardar.addActionListener(e -> {
            String decision = (String) comboDecision.getSelectedItem();
            String motivo = txtMotivo.getText().trim();

            if (motivo.length() < 10) {
                JOptionPane.showMessageDialog(dialog,
                        "El motivo debe tener al menos 10 caracteres",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ResolucionDTO resolucion = new ResolucionDTO();
            resolucion.setSolicitud(solicitud);
            resolucion.setDecision(decision);
            resolucion.setMotivo(motivo);
            resolucion.setFechaEvaluacion(LocalDate.now());

            dialog.dispose();
            coordinadorAplicacion.evaluarManual(resolucion);
        });

        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnGuardar);
        panel.add(buttonPanel);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}
