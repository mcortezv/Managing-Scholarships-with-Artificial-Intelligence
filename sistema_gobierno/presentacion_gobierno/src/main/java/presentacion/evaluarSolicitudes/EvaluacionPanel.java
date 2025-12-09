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
 * Panel de evaluación con auto-avance y gestión de índice
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

    // AGREGADO: Variable de índice que faltaba
    private int indiceActual;

    private JTextArea solicitudTxtArea;
    private JTextArea evaluacionTxtArea;
    private ComboBox<String> comboManual;
    private ICoordinadorAplicacion coordinadorAplicacion;

    public EvaluacionPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
        this.indiceActual = 0; // Inicializar
    }

    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        titulo = new Label("Evaluación Beca");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));

        JPanel main = new JPanel(new FlowLayout(FlowLayout.CENTER));
        main.setMaximumSize(new Dimension(1500, 700));
        main.setOpaque(false);
        main.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setPreferredSize(new Dimension(700, 700));
        left.setBackground(Style.PANEL_COLOR);
        Label detallesSubtitulo = new Label("Detalles de Solicitud");
        detallesSubtitulo.setAlignmentX(CENTER_ALIGNMENT);
        detallesSubtitulo.setFont(Style.SUBTITLE_FONT);
        left.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        left.add(detallesSubtitulo);

        solicitudTxtArea = new JTextArea(7, 30);
        solicitudTxtArea.setFont(Style.INPUT_FONT);
        solicitudTxtArea.setLineWrap(true);
        solicitudTxtArea.setForeground(Color.WHITE);
        solicitudTxtArea.setWrapStyleWord(true);
        solicitudTxtArea.setOpaque(false);
        solicitudTxtArea.setEditable(false);
        solicitudTxtArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane solicitudScrollPane = new JScrollPane(solicitudTxtArea);
        solicitudScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        solicitudScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        solicitudScrollPane.setBorder(BorderFactory.createEmptyBorder());
        solicitudScrollPane.setBackground(Style.PANEL_COLOR);
        solicitudScrollPane.getViewport().setOpaque(false);

        solicitudScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.GRAY;
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });

        JPanel solicitudRoundedPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(240, 248, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            }
        };
        solicitudRoundedPanel.setLayout(new BorderLayout());
        solicitudRoundedPanel.setOpaque(false);
        solicitudRoundedPanel.add(solicitudScrollPane, BorderLayout.CENTER);
        solicitudRoundedPanel.setMaximumSize(new Dimension(500, 400));

        left.add(Box.createRigidArea(new Dimension(0, 20)));
        left.add(solicitudRoundedPanel);

        JPanel paginacion = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        next = new Button("Siguiente");
        previous = new Button("Anterior");
        paginacion.setMaximumSize(new Dimension(400, 90));
        paginacion.setOpaque(false);
        paginacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        paginacion.add(previous);
        paginacion.add(next);
        left.add(paginacion);

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setPreferredSize(new Dimension(700, 700));
        right.setBackground(Color.DARK_GRAY);
        Label evaluacionSubtitulo = new Label("Evaluación Automatica");
        evaluacionSubtitulo.setAlignmentX(CENTER_ALIGNMENT);
        evaluacionSubtitulo.setFont(Style.SUBTITLE_FONT);
        right.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));
        right.add(evaluacionSubtitulo);
        right.add(Box.createVerticalStrut(Style.LBL_ESPACIO));

        JPanel modelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        modelWrapper.setMaximumSize(new Dimension(400, 50));
        modelWrapper.setOpaque(false);
        Label modelWrapperSubtitulo = new Label("Modelo: ");
        ComboBox<String> comboModelo = new ComboBox<>(new String[]{"nvem v1.0"});
        modelWrapper.add(modelWrapperSubtitulo);
        modelWrapper.add(comboModelo);
        right.add(modelWrapper);

        btnGenerar = new Button("Generar");
        btnGenerar.setAlignmentX(CENTER_ALIGNMENT);
        right.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        right.add(btnGenerar);
        right.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        Label evaluacionManual = new Label("Evaluación Manual");
        evaluacionManual.setFont(Style.SUBTITLE_FONT);
        evaluacionManual.setAlignmentX(CENTER_ALIGNMENT);
        right.add(evaluacionManual);
        right.add(Box.createVerticalStrut(Style.LBL_ESPACIO));

        JPanel manualWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 0));
        manualWrapper.setMaximumSize(new Dimension(700, 50));
        manualWrapper.setOpaque(false);
        Label manualWrapperSubtitulo = new Label("Decisión: ");
        comboManual = new ComboBox<>(new String[]{"ACEPTADA", "RECHAZADA", "DEVUELTA"});
        manualWrapper.add(manualWrapperSubtitulo);
        manualWrapper.add(comboManual);
        right.add(manualWrapper);
        right.add(Box.createVerticalStrut(Style.LBL_ESPACIO));

        evaluacionTxtArea = new JTextArea(7, 30);
        evaluacionTxtArea.setFont(Style.INPUT_FONT);
        evaluacionTxtArea.setLineWrap(true);
        evaluacionTxtArea.setWrapStyleWord(true);
        evaluacionTxtArea.setOpaque(false);
        evaluacionTxtArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(evaluacionTxtArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setOpaque(false);

        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.GRAY;
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });

        JPanel roundedPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(240, 248, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            }
        };
        roundedPanel.setLayout(new BorderLayout());
        roundedPanel.setOpaque(false);
        roundedPanel.add(scrollPane, BorderLayout.CENTER);
        roundedPanel.setMaximumSize(new Dimension(500, 200));

        right.add(Box.createRigidArea(new Dimension(0, 20)));
        right.add(roundedPanel);

        btnResolver = new Button("Resolver Solicitud");
        btnResolver.setAlignmentX(CENTER_ALIGNMENT);
        right.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        right.add(btnResolver);

        main.add(left);
        main.add(right);
        centralPanel.add(main);

        configurarEventos();
    }

    private void configurarEventos() {
        // CORREGIDO: Usar índice en lugar de indexOf
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
            coordinadorAplicacion.volverHub();
        });
    }

    /**
     * CORREGIDO: Crear copia mutable de la lista
     */
    public void setSolicitudes(List<SolicitudDTO> solicitudes) {
        System.out.println("DEBUG Panel: Recibiendo " +
                (solicitudes != null ? solicitudes.size() : "null") + " solicitudes");

        // Crear copia mutable para poder modificar
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
    }

    public void setBecaActual(BecaDTO beca) {
        if (beca != null) {
            titulo.setText("Evaluación " + beca.getNombre());
        }
    }

    /**
     * MÉTODO CRÍTICO: Avanza a la siguiente solicitud después de evaluar
     */
    public void avanzarSiguienteSolicitud() {
        System.out.println("DEBUG Panel: Avanzando a siguiente solicitud");

        if (solicitudes == null || solicitudes.isEmpty() || solicitudActual == null) {
            System.out.println("DEBUG Panel: No hay solicitudes para avanzar");
            coordinadorAplicacion.evaluarOtraSolicitud();
            return;
        }

        // Eliminar la solicitud actual (la que acabamos de evaluar)
        solicitudes.remove(indiceActual);
        System.out.println("DEBUG Panel: Solicitudes restantes: " + solicitudes.size());

        // Verificar si quedan solicitudes
        if (solicitudes.isEmpty()) {
            System.out.println("DEBUG Panel: No quedan más solicitudes");
            coordinadorAplicacion.evaluarOtraSolicitud();
            return;
        }

        // Ajustar índice si es necesario
        if (indiceActual >= solicitudes.size()) {
            indiceActual = solicitudes.size() - 1;
        }

        // Actualizar solicitud actual
        solicitudActual = solicitudes.get(indiceActual);
        actualizarVisualizacionSolicitud();
        limpiarCamposEvaluacion();
        actualizarEstadoBotones();

        System.out.println("DEBUG Panel: Mostrando solicitud " + (indiceActual + 1) +
                " de " + solicitudes.size());
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

    /**
     * AGREGADO: Actualizar estado de botones de navegación
     */
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

    /**
     * AGREGADO: Deshabilitar todos los controles
     */
    private void deshabilitarControles() {
        previous.setEnabled(false);
        next.setEnabled(false);
        btnGenerar.setEnabled(false);
        btnResolver.setEnabled(false);
    }

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