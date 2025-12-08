package presentacion.apelarResultado.panels;

import dto.apelacionResultado.ApelacionDTO;
import presentacion.apelarResultado.ApelarResultado;
import presentacion.apelarResultado.PanelApelarResultado;
import presentacion.apelarResultado.coordinadorAplicacionApelarResultado.CoordinadorAplicacionApelarResultado;
import presentacion.styles.Button;
import solicitarBeca.SolicitudDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class DetalleSolicitud extends PanelApelarResultado {
    private SolicitudDTO solicitudDTO;
    private JLabel lblTituloPrincipal;
    private JLabel lblEstado;
    private JLabel txtIdSolicitud;
    private JLabel txtNombreBeca;
    private JLabel txtFechaSolicitud;
    private JLabel txtEstudiante;
    private JLabel txtPromedio;
    private JLabel txtIngresos;
    private Button btnApelar;

    public DetalleSolicitud(ApelarResultado mainFrame, CoordinadorAplicacionApelarResultado coordinadorAplicacion) {
        super(mainFrame, coordinadorAplicacion);
    }

    @Override
    public void startComponents() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(245, 245, 245));
        JPanel pnlTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTop.setOpaque(false);
        pnlTop.setBorder(new EmptyBorder(20, 20, 0, 0));
        Button btnVolver = new Button("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBackground(new Color(50, 50, 50));
        btnVolver.setPreferredSize(new Dimension(150, 50));
        btnVolver.addActionListener(e -> {
            mainFrame.showPanel("listaSolicitudes");
        });
        pnlTop.add(btnVolver);
        this.add(pnlTop, BorderLayout.NORTH);
        JPanel pnlCentro = new JPanel(new GridBagLayout());
        pnlCentro.setOpaque(false);
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1),
                new EmptyBorder(30, 40, 30, 40)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        lblTituloPrincipal = new JLabel("DETALLE SOLICITUD");
        lblTituloPrincipal.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTituloPrincipal.setForeground(Color.BLACK);
        lblEstado = new JLabel(" - ", SwingConstants.CENTER);
        lblEstado.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblEstado.setOpaque(true);
        lblEstado.setBackground(Color.GRAY);
        lblEstado.setForeground(Color.WHITE);
        lblEstado.setBorder(new EmptyBorder(5, 10, 5, 10));
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
        card.add(lblTituloPrincipal, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0; gbc.anchor = GridBagConstraints.EAST;
        card.add(lblEstado, gbc);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; gbc.weightx = 1.0;
        gbc.insets = new Insets(15, 0, 20, 0);
        card.add(new JSeparator(), gbc);
        gbc.insets = new Insets(8, 0, 8, 20);
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        int row = 2;
        card.add(crearLabel("ID Solicitud:"), configLabelGBC(0, row));
        txtIdSolicitud = crearValor("-");
        card.add(txtIdSolicitud, configValorGBC(1, row));
        row++;
        card.add(crearLabel("Beca Solicitada:"), configLabelGBC(0, row));
        txtNombreBeca = crearValor("-");
        card.add(txtNombreBeca, configValorGBC(1, row));
        row++;
        card.add(crearLabel("Fecha Solicitud:"), configLabelGBC(0, row));
        txtFechaSolicitud = crearValor("-");
        card.add(txtFechaSolicitud, configValorGBC(1, row));
        row++;
        card.add(crearLabel("Estudiante:"), configLabelGBC(0, row));
        txtEstudiante = crearValor("-");
        card.add(txtEstudiante, configValorGBC(1, row));
        row++;
        card.add(crearLabel("Promedio General:"), configLabelGBC(0, row));
        txtPromedio = crearValor("-");
        card.add(txtPromedio, configValorGBC(1, row));
        row++;
        card.add(crearLabel("Ingresos Mensuales:"), configLabelGBC(0, row));
        txtIngresos = crearValor("-");
        card.add(txtIngresos, configValorGBC(1, row));
        row++;
        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2; gbc.insets = new Insets(20, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        btnApelar = new Button("Apelar Resultado");
        btnApelar.setBackground(new Color(220, 53, 69));
        btnApelar.setForeground(Color.WHITE);
        btnApelar.setPreferredSize(new Dimension(200, 40));
        btnApelar.setVisible(false);
        btnApelar.addActionListener(e -> abrirDialogoApelacion());
        card.add(btnApelar, gbc);
        pnlCentro.add(card);
        this.add(pnlCentro, BorderLayout.CENTER);
    }

    private void abrirDialogoApelacion() {
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        RedaccionApelacion dialog = new RedaccionApelacion(parentWindow);
        dialog.setVisible(true);
        if (dialog.isConfirmado()) {
            String motivo = dialog.getMotivo();
            if (motivo != null && !motivo.trim().isEmpty()) {
                ApelacionDTO nuevaApelacion = new ApelacionDTO();
                nuevaApelacion.setSolicitud(this.solicitudDTO);
                nuevaApelacion.setMotivo(motivo);
                coordinadorAplicacionApelarResultado.procesarApelacion(nuevaApelacion);
            } else {
                JOptionPane.showMessageDialog(this, "Debe redactar un motivo para proceder.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private GridBagConstraints configLabelGBC(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x; gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 0, 8, 30);
        return gbc;
    }

    private GridBagConstraints configValorGBC(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x; gbc.gridy = y;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 0, 8, 0);
        return gbc;
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        lbl.setForeground(new Color(30, 30, 30));
        return lbl;
    }

    private JLabel crearValor(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl.setForeground(new Color(100, 100, 100));
        return lbl;
    }

    public void setSolicitud(SolicitudDTO solicitud) {
        this.solicitudDTO = solicitud;
        if (solicitud != null) {
            txtIdSolicitud.setText(String.valueOf(solicitud.getId()));
            txtNombreBeca.setText(solicitud.getBeca() != null ? solicitud.getBeca().getNombre() : "N/A");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            txtFechaSolicitud.setText(solicitud.getFecha() != null ? solicitud.getFecha().format(formatter) : "-");

            txtEstudiante.setText(solicitud.getEstudiante() != null ? solicitud.getEstudiante().getNombre() : "-");

            if (solicitud.getHistorialAcademico() != null) {
                txtPromedio.setText(String.format("%.2f", solicitud.getHistorialAcademico().getPromedio()));
            } else {
                txtPromedio.setText("-");
            }

            if (solicitud.getInformacionSocioeconomica() != null) {
                txtIngresos.setText(String.format("$ %.2f MXN", solicitud.getInformacionSocioeconomica().getIngresoTotalFamilarMensual()));
            } else {
                txtIngresos.setText("-");
            }

            String estado = solicitud.getEstado();
            lblEstado.setText(" " + estado + " ");

            if ("Aprobada".equalsIgnoreCase(estado) || "Aceptada".equalsIgnoreCase(estado)) {
                lblEstado.setBackground(new Color(40, 167, 69));
                btnApelar.setVisible(false);
            } else if ("Rechazada".equalsIgnoreCase(estado)) {
                lblEstado.setBackground(new Color(220, 53, 69));
                btnApelar.setVisible(true);
            } else {
                lblEstado.setBackground(new Color(255, 193, 7));
                btnApelar.setVisible(false);
            }
        }
    }
}