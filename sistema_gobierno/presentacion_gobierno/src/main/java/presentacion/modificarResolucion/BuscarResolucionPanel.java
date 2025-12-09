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
 * Panel corregido para búsqueda de resoluciones
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
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));

        // Panel principal con opciones de búsqueda
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBackground(Color.DARK_GRAY);
        searchPanel.setMaximumSize(new Dimension(600, 500));
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        // Tipo de búsqueda
        Label lblTipoBusqueda = new Label("Tipo de Búsqueda:");
        lblTipoBusqueda.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(lblTipoBusqueda);
        searchPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));

        comboTipoBusqueda = new ComboBox<>(new String[]{
                "Por Matrícula",
                "Por Nombre",
                "Por ID Solicitud"
        });
        comboTipoBusqueda.setMaximumSize(new Dimension(400, 50));
        comboTipoBusqueda.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(comboTipoBusqueda);
        searchPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        // Matrícula
        Label lblMatricula = new Label("Matrícula del Estudiante:");
        lblMatricula.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(lblMatricula);
        searchPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));

        txtMatricula = new TextField(20);
        txtMatricula.setMaximumSize(new Dimension(400, 50));
        txtMatricula.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(txtMatricula);
        searchPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        // Nombre
        Label lblNombre = new Label("Nombre del Estudiante:");
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(lblNombre);
        searchPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));

        txtNombre = new TextField(20);
        txtNombre.setMaximumSize(new Dimension(400, 50));
        txtNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(txtNombre);
        searchPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        // ID Solicitud
        Label lblIdSolicitud = new Label("ID de Solicitud:");
        lblIdSolicitud.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(lblIdSolicitud);
        searchPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));

        txtIdSolicitud = new TextField(20);
        txtIdSolicitud.setMaximumSize(new Dimension(400, 50));
        txtIdSolicitud.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(txtIdSolicitud);
        searchPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));

        // Botón buscar
        btnBuscar = new Button("Buscar Resolución");
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(btnBuscar);

        centralPanel.add(searchPanel);

        configurarEventos();
        configurarValidacionesDinamicas();
    }

    private void configurarEventos() {
        // Evento del botón buscar
        btnBuscar.addActionListener(e -> realizarBusqueda());

        // Permitir buscar con Enter en cualquier campo
        txtMatricula.addActionListener(e -> realizarBusqueda());
        txtNombre.addActionListener(e -> realizarBusqueda());
        txtIdSolicitud.addActionListener(e -> realizarBusqueda());

        // Cambiar campos activos según tipo de búsqueda
        comboTipoBusqueda.addActionListener(e -> actualizarCamposActivos());

        // Botón volver
        btnBack.addActionListener(e -> {
            coordinadorAplicacion.volverHub();
        });
    }

    private void configurarValidacionesDinamicas() {
        // Listener para matrícula (solo números)
        txtMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
            }
        });

        // Listener para ID solicitud (solo números)
        txtIdSolicitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
            }
        });

        // Inicializar campos activos
        actualizarCamposActivos();
    }

    private void actualizarCamposActivos() {
        String tipoBusqueda = (String) comboTipoBusqueda.getSelectedItem();

        // Deshabilitar todos
        txtMatricula.setEnabled(false);
        txtNombre.setEnabled(false);
        txtIdSolicitud.setEnabled(false);

        txtMatricula.setBackground(Color.LIGHT_GRAY);
        txtNombre.setBackground(Color.LIGHT_GRAY);
        txtIdSolicitud.setBackground(Color.LIGHT_GRAY);

        // Habilitar el correspondiente
        switch (tipoBusqueda) {
            case "Por Matrícula":
                txtMatricula.setEnabled(true);
                txtMatricula.setBackground(Style.INPUT_COLOR);
                txtMatricula.requestFocus();
                break;
            case "Por Nombre":
                txtNombre.setEnabled(true);
                txtNombre.setBackground(Style.INPUT_COLOR);
                txtNombre.requestFocus();
                break;
            case "Por ID Solicitud":
                txtIdSolicitud.setEnabled(true);
                txtIdSolicitud.setBackground(Style.INPUT_COLOR);
                txtIdSolicitud.requestFocus();
                break;
        }
    }

    private void realizarBusqueda() {
        String tipoBusqueda = (String) comboTipoBusqueda.getSelectedItem();
        String tipoFiltro = "";
        String filtro = "";

        // Determinar tipo de filtro y valor según selección
        switch (tipoBusqueda) {
            case "Por Matrícula":
                filtro = txtMatricula.getText().trim();
                if (filtro.isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Debe ingresar una matrícula para buscar",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                tipoFiltro = "MATRICULA";
                break;

            case "Por Nombre":
                filtro = txtNombre.getText().trim();
                if (filtro.isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Debe ingresar un nombre para buscar",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (filtro.length() < 3) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "El nombre debe tener al menos 3 caracteres",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                tipoFiltro = "NOMBRE";
                break;

            case "Por ID Solicitud":
                filtro = txtIdSolicitud.getText().trim();
                if (filtro.isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Debe ingresar un ID de solicitud para buscar",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                tipoFiltro = "ID_SOLICITUD";
                break;

            default:
                JOptionPane.showMessageDialog(mainFrame,
                        "Debe seleccionar un tipo de búsqueda",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        // Realizar búsqueda a través del coordinador
        try {
            coordinadorAplicacion.buscarResolucion(tipoFiltro, filtro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Error al buscar: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarBusqueda() {
        txtMatricula.setText("");
        txtNombre.setText("");
        txtIdSolicitud.setText("");
        comboTipoBusqueda.setSelectedIndex(0);
        actualizarCamposActivos();
    }
}