package presentacion.modificarResolucion;
import presentacion.coordinacion.MainFrame;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.styles.Button;
import presentacion.styles.ComboBox;
import presentacion.styles.Label;
import presentacion.styles.Panel;
import presentacion.styles.Style;
import presentacion.styles.TextField;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;


public class BuscarResolucionPanel extends Panel {
    private Label titulo;
    private Button btnModificar;
    private TextField textFieldNombre;
    private ICoordinadorAplicacion coordinadorAplicacion;

    public BuscarResolucionPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        titulo = new Label("Buscar Resolución");
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
        left.setBackground(Color.DARK_GRAY);
        Label evaluacionSubtitulo = new Label("Nombre del Estudiante");
        evaluacionSubtitulo.setAlignmentX(CENTER_ALIGNMENT);
        evaluacionSubtitulo.setFont(Style.SUBTITLE_FONT);
        left.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        left.add(evaluacionSubtitulo);
        left.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        textFieldNombre = new TextField(10);
        textFieldNombre.setAlignmentX(CENTER_ALIGNMENT);
        textFieldNombre.setMaximumSize(new Dimension(450, 50));
        left.add(textFieldNombre);
        left.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        Label filtrosLabel = new Label("Filtros y Restricciones");
        JPanel categoriaWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 0));
        categoriaWrapper.setMaximumSize(new Dimension(400, 50));
        categoriaWrapper.setOpaque(false);
        Label categoriaBeca = new Label("Categoría Beca");
        Label carreraLabel = new Label("Carrera");
        categoriaWrapper.add(categoriaBeca);
        categoriaWrapper.add(carreraLabel);
        left.add(categoriaWrapper);


        JPanel comboWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        comboWrapper.setMaximumSize(new Dimension(400, 50));
        comboWrapper.setOpaque(false);
        ComboBox<String> beca = new ComboBox<>(new String[]{"Excelencia", "Recursos", "Constancia", "Trabajo"});
        beca.setFont(Style.LABEL_FONT);
        ComboBox<String> carrera = new ComboBox<>(new String[]{"Ingeniería", "Licenciatura", "Maestría", "Doctorado"});
        carrera.setMaximumSize(new Dimension(100, 50));
        comboWrapper.add(beca);
        comboWrapper.add(carrera);
        left.add(comboWrapper);
        left.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));


        presentacion.styles.Button btnGenerar = new presentacion.styles.Button("Buscar");
        btnGenerar.setAlignmentX(CENTER_ALIGNMENT);
        left.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        left.add(btnGenerar);
        left.add(Box.createVerticalStrut(Style.TOP_ESPACIO));



        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setPreferredSize(new Dimension(700, 700));
        right.setBackground(Style.PANEL_COLOR);

        JTextArea solicitudTxtArea = new JTextArea(7, 30);
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
        solicitudRoundedPanel.setMaximumSize(new Dimension(500, 500));

        right.add(Box.createRigidArea(new Dimension(0, 20)));
        right.add(solicitudRoundedPanel);

        btnModificar = new Button("Modificar");
        btnModificar.setAlignmentX(CENTER_ALIGNMENT);
        right.add(btnModificar);


        main.add(left);
        main.add(right);
        centralPanel.add(main);

        btnModificar.addActionListener(e -> {
            String nombre = textFieldNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Debe ingresar un nombre para buscar",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            coordinadorAplicacion.buscarResolucion("NOMBRE", nombre);
        });

        // Modificar el ActionListener del btnBack:
        btnBack.addActionListener(e -> {
            coordinadorAplicacion.volverHub();
        });
    }

    public void limpiarBusqueda() {
        textFieldNombre.setText("");
    }
}
