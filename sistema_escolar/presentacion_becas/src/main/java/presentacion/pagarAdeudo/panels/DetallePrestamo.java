package presentacion.pagarAdeudo.panels;

import dto.pagarAdeudo.PrestamoDTO;
import presentacion.pagarAdeudo.PagarAdeudo;
import presentacion.pagarAdeudo.PanelPagarAdeudo;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DetallePrestamo extends PanelPagarAdeudo {

    private JLabel lblTituloPrincipal;
    private JLabel lblEstado;
    private JLabel txtFechaPrestamo;
    private JLabel txtFechaDevolucion;
    private JLabel txtIsbn;
    private JLabel txtTituloLibro;
    private JLabel txtCampus;
    private JLabel txtDetalles;
    private JLabel txtCosto;

    public DetallePrestamo(PagarAdeudo frame, CoordinadorAplicacionPagarAdeudo coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
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
            mainFrame.showPanel("listaPrestamosBiblioteca");
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

        lblTituloPrincipal = new JLabel("DETALLE PRÉSTAMO");
        lblTituloPrincipal.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTituloPrincipal.setForeground(Color.BLACK);

        lblEstado = new JLabel(" Pendiente ", SwingConstants.CENTER);
        lblEstado.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblEstado.setOpaque(true);
        lblEstado.setBackground(new Color(255, 193, 7));
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

        card.add(crearLabel("Fecha de préstamo"), configLabelGBC(0, row));
        txtFechaPrestamo = crearValor("-");
        card.add(txtFechaPrestamo, configValorGBC(1, row));
        row++;

        card.add(crearLabel("Fecha de devolución programada:"), configLabelGBC(0, row));
        txtFechaDevolucion = crearValor("-");
        card.add(txtFechaDevolucion, configValorGBC(1, row));
        row++;

        card.add(crearLabel("ISBN:"), configLabelGBC(0, row));
        txtIsbn = crearValor("-");
        card.add(txtIsbn, configValorGBC(1, row));
        row++;

        card.add(crearLabel("Título:"), configLabelGBC(0, row));
        txtTituloLibro = crearValor("-");
        card.add(txtTituloLibro, configValorGBC(1, row));
        row++;

        card.add(crearLabel("Campus:"), configLabelGBC(0, row));
        txtCampus = crearValor("-");
        card.add(txtCampus, configValorGBC(1, row));
        row++;

        card.add(crearLabel("Costo:"), configLabelGBC(0, row));
        txtCosto = crearValor("-");
        card.add(txtCosto, configValorGBC(1, row));
        row++;

        card.add(crearLabel("Detalles:"), configLabelGBC(0, row));
        txtDetalles = crearValor("-");
        card.add(txtDetalles, configValorGBC(1, row));

        pnlCentro.add(card);
        this.add(pnlCentro, BorderLayout.CENTER);
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
        lbl.setForeground(new Color(100, 100, 100)); // Gris más suave
        return lbl;
    }

    public void setPrestamo(PrestamoDTO prestamo){
        if(prestamo != null) {
            lblTituloPrincipal.setText("PRÉSTAMO");
            txtFechaPrestamo.setText(prestamo.getFechaPrestamo());
            txtFechaDevolucion.setText(prestamo.getFechaDevolucion());
            txtIsbn.setText(prestamo.getIsbn());
            txtTituloLibro.setText(prestamo.getTitulo().toUpperCase());
            txtCampus.setText(prestamo.getCampus());
            txtDetalles.setText(prestamo.getDetalles());
            txtCosto.setText("$ " + String.format("%.2f", prestamo.getCosto()));
        }
    }
}