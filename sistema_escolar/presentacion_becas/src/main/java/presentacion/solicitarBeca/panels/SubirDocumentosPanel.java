package presentacion.solicitarBeca.panels;
import presentacion.CoordinadorAplicacion;
import presentacion.solicitarBeca.PanelSolicitarBeca;
import presentacion.solicitarBeca.SolicitarBeca;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.styles.Style;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The type Subir documentos panel.
 */
public class SubirDocumentosPanel extends PanelSolicitarBeca {

    private Label titulo;
    private Button btnContinuar;

    private final Map<String, File> documentosCargados = new HashMap<>();
    private final CoordinadorAplicacion coordinadorAplicacion;

    private final String[] DOCUMENTOS_REQUERIDOS = {
            "CURP", "INE", "KARDEX",
            "COMPROBANTE_INSCIRPCION",
            "COMPROBANTE_INGRESOS"
    };

    /**
     * Instantiates a new Subir documentos panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public SubirDocumentosPanel(SolicitarBeca frame, CoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {

        centralPanel.setBackground(Style.PANEL_COLOR);
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));

        centralPanel.add(Box.createVerticalStrut(60));

        titulo = new Label("Subir Documentos");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(40));

        JPanel panelBotones = new JPanel();
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        panelBotones.setBackground(new Color(245, 245, 245));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        panelBotones.setMaximumSize(new Dimension(900, 650));
        panelBotones.setOpaque(false);

        for (String docName : DOCUMENTOS_REQUERIDOS) {
            JPanel section = createUploadSection(docName);
            section.setAlignmentX(Component.CENTER_ALIGNMENT);

            panelBotones.add(section);
            panelBotones.add(Box.createVerticalStrut(20));
        }

        centralPanel.add(panelBotones);
        centralPanel.add(Box.createVerticalStrut(40));

        btnContinuar = new Button("Continuar");
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(btnContinuar);

        btnBack.addActionListener(e ->
                mainFrame.showPanel("informacionSocioeconomicaPanel")
        );

        btnContinuar.addActionListener(e -> {
            if (documentosCargados.size() != DOCUMENTOS_REQUERIDOS.length) {
                JOptionPane.showMessageDialog(
                        mainFrame,
                        "Debe subir los " + DOCUMENTOS_REQUERIDOS.length + " documentos requeridos para continuar.",
                        "Documentos faltantes",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            try {
                coordinadorAplicacion.procesarDocumentos(documentosCargados);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        mainFrame,
                        "Error al procesar la solicitud: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    private JPanel createUploadSection(String docName) {

        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.X_AXIS));

        sectionPanel.setBackground(Style.PANEL_COLOR);
        sectionPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Style.PANEL_COLOR),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                )
        );

        sectionPanel.setMaximumSize(new Dimension(800, 60));

        Button uploadButton = createUploadButton(docName);

        Button deleteButton = new Button("X");
        deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        deleteButton.setBackground(new Color(200, 60, 60));
        deleteButton.setPreferredSize(new Dimension(45, 40));
        deleteButton.setMaximumSize(new Dimension(45, 40));
        deleteButton.setVisible(false);

        deleteButton.addActionListener(e -> {
            documentosCargados.remove(docName);
            uploadButton.setText(docName);
            uploadButton.setEnabled(true);
            deleteButton.setVisible(false);
            uploadButton.setBackground(Color.WHITE);
        });

        sectionPanel.add(uploadButton);
        sectionPanel.add(Box.createHorizontalStrut(15));
        sectionPanel.add(deleteButton);

        uploadButton.addActionListener(e ->
                abrirFileChooser(uploadButton, deleteButton, docName)
        );

        return sectionPanel;
    }

    private Button createUploadButton(String docName) {
        ImageIcon uploadIcon = null;
        try {
            uploadIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/upload.png")));
            Image img = uploadIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            uploadIcon = new ImageIcon(img);
        } catch (Exception ignored) {}

        Button button = new Button(docName);
        button.setFont(new Font("Segoe UI", Font.BOLD, 17));
        button.setIcon(uploadIcon);
        button.setIconTextGap(15);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(Color.WHITE);
        button.setPreferredSize(new Dimension(700, 50));
        button.setMaximumSize(new Dimension(700, 50));

        return button;
    }

    private void abrirFileChooser(Button uploadButton, Button deleteButton, String docName) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona tu " + docName);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));

        int seleccion = fileChooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();

            documentosCargados.put(docName, archivoSeleccionado);

            uploadButton.setText(docName + " - " + archivoSeleccionado.getName() + " ✓");
            uploadButton.setBackground(new Color(225, 255, 225));
            uploadButton.setEnabled(false);

            deleteButton.setVisible(true);
        }
    }
}
