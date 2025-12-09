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

public class ModificarResolucionPanel extends Panel {
    private Label titulo;
    private Label estado;
    private ResolucionDTO resolucionActual;
    private SolicitudDTO solicitudActual;
    private JTextArea solicitudTxtArea;
    private JTextArea evaluacionTxtArea;
    private ComboBox<String> comboManual;
    private ICoordinadorAplicacion coordinadorAplicacion;

    public ModificarResolucionPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        titulo = new Label("Modificar Resolución");
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



        estado = new Label("Estado: ");
        estado.setAlignmentX(Component.CENTER_ALIGNMENT);
        left.add(estado);





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

        presentacion.styles.Button btnGenerar = new presentacion.styles.Button("Generar");
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
        Label manualWrapperSubtitulo = new Label("Motivo: ");
        comboManual = new ComboBox<>(new String[]{"Aprobar", "Rechazar", "Devolver"});
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

        presentacion.styles.Button btnResolver = new Button("Resolver Solicitud");
        btnResolver.setAlignmentX(CENTER_ALIGNMENT);
        right.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        right.add(btnResolver);

        main.add(left);
        main.add(right);
        centralPanel.add(main);

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

            ResolucionDTO nuevaResolucion = new ResolucionDTO();
            nuevaResolucion.setSolicitud(solicitudActual);
            nuevaResolucion.setDecision(decision);
            nuevaResolucion.setMotivo(motivo);
            nuevaResolucion.setFechaEvaluacion(LocalDate.now());

            coordinadorAplicacion.modificarResolucion(nuevaResolucion);
        });

    }

    public void setResolucion(ResolucionDTO resolucion) {
        this.resolucionActual = resolucion;
        this.solicitudActual = resolucion.getSolicitud();

        // Mostrar la información de la solicitud
        solicitudTxtArea.setText(solicitudActual.toString());

        // Mostrar el estado actual
        estado.setText("Estado Actual: " + solicitudActual.getEstado());

        // Mostrar la resolución actual
        evaluacionTxtArea.setText(resolucion.getMotivo());

        // Seleccionar la decisión actual
        comboManual.setSelectedItem(resolucion.getDecision());
    }
}
