package presentacion.modificarResolucion;
import presentacion.coordinacion.MainFrame;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.styles.*;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.styles.Panel;
import presentacion.styles.TextField;
import javax.swing.*;
import java.awt.*;

/**
 * The type Buscar resolucion panel.
 *
 * @author Cortez, Manuel
 */
public class BuscarResolucionPanel extends Panel {
    private Label titulo;
    private TextField txtMatricula;
    private TextField txtNombre;
    private TextField txtIdSolicitud;
    private ComboBox<String> comboTipoBusqueda;
    private Button btnBuscar;
    private ICoordinadorAplicacion coordinadorAplicacion;

    /**
     * Instantiates a new Buscar resolucion panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public BuscarResolucionPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new BoxLayout(tituloPanel, BoxLayout.X_AXIS));
        tituloPanel.setOpaque(false);
        tituloPanel.setMaximumSize(new Dimension(800, 60));

        titulo = new Label("Buscar Resolución");
        titulo.setFont(Style.TITLE_FONT);

        tituloPanel.add(Box.createHorizontalGlue());
        tituloPanel.add(titulo);
        tituloPanel.add(Box.createHorizontalGlue());
        centralPanel.add(tituloPanel);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));

        JPanel searchPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(0, 0, 0, 30));
                g2.fillRoundRect(4, 4, getWidth()-4, getHeight()-4, 25, 25);

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(45, 52, 54),
                        0, getHeight(), new Color(55, 62, 64)
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth()-4, getHeight()-4, 25, 25);
                g2.setColor(new Color(100, 149, 237, 100));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, getWidth()-6, getHeight()-6, 25, 25);
            }
        };
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setOpaque(false);
        searchPanel.setMaximumSize(new Dimension(650, 600));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(35, 40, 35, 40));
        searchPanel.add(Box.createVerticalStrut(20));

        Label lblTipoBusqueda = new Label("Tipo de Búsqueda");
        lblTipoBusqueda.setFont(Style.LABEL_FONT.deriveFont(Font.BOLD, 20f));
        lblTipoBusqueda.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(lblTipoBusqueda);
        searchPanel.add(Box.createVerticalStrut(12));

        comboTipoBusqueda = new ComboBox<>(new String[]{
                "Por Matrícula",
                "Por Nombre",
                "Por ID Solicitud"
        });
        comboTipoBusqueda.setMaximumSize(new Dimension(450, 50));
        comboTipoBusqueda.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(comboTipoBusqueda);
        searchPanel.add(Box.createVerticalStrut(25));

        JSeparator separator1 = crearSeparador();
        searchPanel.add(separator1);
        searchPanel.add(Box.createVerticalStrut(25));

        JPanel matriculaPanel = crearCampo("Matrícula del Estudiante");
        searchPanel.add(matriculaPanel);
        searchPanel.add(Box.createVerticalStrut(10));

        txtMatricula = new TextField(20);
        txtMatricula.setMaximumSize(new Dimension(450, 50));
        txtMatricula.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(txtMatricula);
        searchPanel.add(Box.createVerticalStrut(20));

        JPanel nombrePanel = crearCampo("Nombre del Estudiante");
        searchPanel.add(nombrePanel);
        searchPanel.add(Box.createVerticalStrut(10));

        txtNombre = new TextField(20);
        txtNombre.setMaximumSize(new Dimension(450, 50));
        txtNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(txtNombre);
        searchPanel.add(Box.createVerticalStrut(20));

        JPanel idPanel = crearCampo("ID de Solicitud");
        searchPanel.add(idPanel);
        searchPanel.add(Box.createVerticalStrut(10));

        txtIdSolicitud = new TextField(20);
        txtIdSolicitud.setMaximumSize(new Dimension(450, 50));
        txtIdSolicitud.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(txtIdSolicitud);
        searchPanel.add(Box.createVerticalStrut(30));

        JSeparator separator2 = crearSeparador();
        searchPanel.add(separator2);
        searchPanel.add(Box.createVerticalStrut(25));

        btnBuscar = new Button("Buscar Resolución");
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(btnBuscar);
        centralPanel.add(searchPanel);

        configurarEventos();
        configurarValidacionesDinamicas();
    }

    private JPanel crearCampo(String texto) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(450, 30));

        Label label = new Label(texto);
        label.setFont(Style.LABEL_FONT.deriveFont(16f));
        label.setForeground(Color.WHITE);

        panel.add(label);
        return panel;
    }

    private JSeparator crearSeparador() {
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(500, 2));
        separator.setForeground(new Color(70, 70, 70));
        separator.setBackground(new Color(70, 70, 70));
        return separator;
    }

    private void configurarEventos() {
        btnBuscar.addActionListener(e -> realizarBusqueda());
        txtMatricula.addActionListener(e -> realizarBusqueda());
        txtNombre.addActionListener(e -> realizarBusqueda());
        txtIdSolicitud.addActionListener(e -> realizarBusqueda());
        comboTipoBusqueda.addActionListener(e -> actualizarCamposActivos());
        btnBack.addActionListener(e -> coordinadorAplicacion.volverHub());
    }

    private void configurarValidacionesDinamicas() {
        txtMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
            }
        });
        txtIdSolicitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
            }
        });
        actualizarCamposActivos();
    }

    private void actualizarCamposActivos() {
        String tipoBusqueda = (String) comboTipoBusqueda.getSelectedItem();

        txtMatricula.setEnabled(false);
        txtNombre.setEnabled(false);
        txtIdSolicitud.setEnabled(false);

        Color colorInactivo = new Color(100, 100, 100);
        txtMatricula.setBackground(colorInactivo);
        txtNombre.setBackground(colorInactivo);
        txtIdSolicitud.setBackground(colorInactivo);

        if (tipoBusqueda.contains("Matrícula")) {
            txtMatricula.setEnabled(true);
            txtMatricula.setBackground(Style.INPUT_COLOR);
            txtMatricula.requestFocus();
        } else if (tipoBusqueda.contains("Nombre")) {
            txtNombre.setEnabled(true);
            txtNombre.setBackground(Style.INPUT_COLOR);
            txtNombre.requestFocus();
        } else if (tipoBusqueda.contains("ID Solicitud")) {
            txtIdSolicitud.setEnabled(true);
            txtIdSolicitud.setBackground(Style.INPUT_COLOR);
            txtIdSolicitud.requestFocus();
        }
    }

    private void realizarBusqueda() {
        String tipoBusqueda = (String) comboTipoBusqueda.getSelectedItem();
        String tipoFiltro = "";
        String filtro = "";

        if (tipoBusqueda.contains("Matrícula")) {
            filtro = txtMatricula.getText().trim();
            if (filtro.isEmpty()) {
                mostrarError("Debe ingresar una matrícula para buscar");
                return;
            }
            tipoFiltro = "MATRICULA";
        } else if (tipoBusqueda.contains("Nombre")) {
            filtro = txtNombre.getText().trim();
            if (filtro.isEmpty()) {
                mostrarError("Debe ingresar un nombre para buscar");
                return;
            }
            if (filtro.length() < 3) {
                mostrarError("El nombre debe tener al menos 3 caracteres");
                return;
            }
            tipoFiltro = "NOMBRE";
        } else if (tipoBusqueda.contains("ID Solicitud")) {
            filtro = txtIdSolicitud.getText().trim();
            if (filtro.isEmpty()) {
                mostrarError("Debe ingresar un ID de solicitud para buscar");
                return;
            }
            tipoFiltro = "ID_SOLICITUD";
        } else {
            mostrarError("Debe seleccionar un tipo de búsqueda");
            return;
        }
        try {
            coordinadorAplicacion.buscarResolucion(tipoFiltro, filtro);
        } catch (Exception ex) {
            mostrarError("Error al buscar: " + ex.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(mainFrame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Limpiar busqueda.
     */
    public void limpiarBusqueda() {
        txtMatricula.setText("");
        txtNombre.setText("");
        txtIdSolicitud.setText("");
        comboTipoBusqueda.setSelectedIndex(0);
        actualizarCamposActivos();
    }
}