package presentacion.modificarResolucion;
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


/**
 * The type Modificar resolucion panel.
 *
 * @author Cortez, Manuel;
 */
public class ModificarResolucionPanel extends Panel {
    private Label titulo;
    private Label estado;
    private ResolucionDTO resolucionActual;
    private SolicitudDTO solicitudActual;
    private JTextArea solicitudTxtArea;
    private JTextArea evaluacionTxtArea;
    private ComboBox<String> comboManual;
    private ICoordinadorAplicacion coordinadorAplicacion;

    /**
     * Instantiates a new Modificar resolucion panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public ModificarResolucionPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new BoxLayout(tituloPanel, BoxLayout.X_AXIS));
        tituloPanel.setOpaque(false);
        tituloPanel.setAlignmentX(CENTER_ALIGNMENT);
        tituloPanel.setMaximumSize(new Dimension(800, 60));

        titulo = new Label("Modificar Resolución");
        titulo.setFont(Style.TITLE_FONT);
        tituloPanel.add(Box.createHorizontalGlue());
        tituloPanel.add(titulo);
        tituloPanel.add(Box.createHorizontalGlue());

        centralPanel.add(tituloPanel);
        centralPanel.add(Box.createVerticalStrut(40));

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
        left.add(Box.createVerticalStrut(20));

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

        estado = new Label("Estado: ");
        estado.setFont(Style.LABEL_FONT.deriveFont(Font.BOLD, 22f));
        estado.setAlignmentX(Component.CENTER_ALIGNMENT);
        left.add(estado);
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

        JPanel modelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        modelWrapper.setMaximumSize(new Dimension(500, 50));
        modelWrapper.setOpaque(false);
        Label modelWrapperSubtitulo = new Label("Modelo:");
        modelWrapperSubtitulo.setForeground(Color.WHITE);
        ComboBox<String> comboModelo = new ComboBox<>(new String[]{"nvem v1.0"});
        modelWrapper.add(modelWrapperSubtitulo);
        modelWrapper.add(comboModelo);
        right.add(modelWrapper);
        right.add(Box.createVerticalStrut(15));

        Button btnGenerar = new Button("Regenerar Evaluación");
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

        JPanel manualWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        manualWrapper.setMaximumSize(new Dimension(500, 50));
        manualWrapper.setOpaque(false);
        Label manualWrapperSubtitulo = new Label("Decisión:");
        manualWrapperSubtitulo.setForeground(Color.WHITE);
        comboManual = new ComboBox<>(new String[]{"ACEPTADA", "RECHAZADA", "DEVUELTA"});
        manualWrapper.add(manualWrapperSubtitulo);
        manualWrapper.add(comboManual);
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

        Button btnResolver = new Button("Guardar Cambios");
        btnResolver.setAlignmentX(CENTER_ALIGNMENT);
        right.add(btnResolver);
        right.add(Box.createVerticalStrut(15));

        btnGenerar.addActionListener(e -> {
            if (solicitudActual != null) {
                coordinadorAplicacion.reevaluarAutomatica(solicitudActual);
            } else {
                JOptionPane.showMessageDialog(mainFrame,
                        "No hay solicitud cargada para re-evaluar",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnResolver.addActionListener(e -> {
            if (resolucionActual == null) {
                JOptionPane.showMessageDialog(mainFrame,
                        "No hay resolución cargada para modificar",
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

            resolucionActual.setDecision(decision);
            resolucionActual.setMotivo(motivo);
            resolucionActual.setFechaEvaluacion(LocalDate.now());
            coordinadorAplicacion.modificarResolucion(resolucionActual);
        });

        return right;
    }

    private void configurarEventos() {
        btnBack.addActionListener(e -> {
            coordinadorAplicacion.iniciarBusquedaResolucion();
        });
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
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        };
    }

    /**
     * Sets resolucion.
     *
     * @param resolucion the resolucion
     */
    public void setResolucion(ResolucionDTO resolucion) {
        this.resolucionActual = resolucion;
        this.solicitudActual = resolucion.getSolicitud();

        solicitudTxtArea.setText(solicitudActual.toString());
        solicitudTxtArea.setCaretPosition(0);

        String estadoTexto = solicitudActual.getEstado();
        estado.setText("Estado: " + estadoTexto);

        if (estadoTexto.contains("ACEPTADA")) {
            estado.setForeground(new Color(46, 204, 113));
        } else if (estadoTexto.contains("RECHAZADA")) {
            estado.setForeground(new Color(231, 76, 60));
        } else {
            estado.setForeground(new Color(241, 196, 15));
        }
        evaluacionTxtArea.setText(resolucion.getMotivo());
        comboManual.setSelectedItem(resolucion.getDecision());
    }
}
