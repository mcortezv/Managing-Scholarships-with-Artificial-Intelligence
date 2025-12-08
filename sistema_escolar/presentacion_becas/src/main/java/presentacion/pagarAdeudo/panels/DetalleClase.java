package presentacion.pagarAdeudo.panels;

import dto.pagarAdeudo.ClaseDTO;
import presentacion.pagarAdeudo.PagarAdeudo;
import presentacion.pagarAdeudo.PanelPagarAdeudo;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DetalleClase extends PanelPagarAdeudo {
    private ClaseDTO claseDTO;

    private JLabel lblTituloMateria;
    private JLabel lblEstado;
    private JLabel txtProfesor;
    private JLabel txtHorario;
    private JLabel txtAula;
    private JLabel txtCampus;
    private JLabel txtDetalles;
    private JLabel txtCosto;

    public DetalleClase(PagarAdeudo frame, CoordinadorAplicacionPagarAdeudo coordinadorAplicacion) {
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
            mainFrame.showPanel("listaClasesColegiatura");
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

        lblTituloMateria = new JLabel("MATERIA");
        lblTituloMateria.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTituloMateria.setForeground(Color.BLACK);

        lblEstado = new JLabel(" Por Pagar ", SwingConstants.CENTER);
        lblEstado.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblEstado.setOpaque(true);
        lblEstado.setBackground(new Color(255, 193, 7));
        lblEstado.setForeground(Color.WHITE);
        lblEstado.setBorder(new EmptyBorder(5, 10, 5, 10));

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
        card.add(lblTituloMateria, gbc);

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

        card.add(crearLabel("Profesor:"), configLabelGBC(0, row));
        txtProfesor = crearValor("-");
        card.add(txtProfesor, configValorGBC(1, row));
        row++;

        card.add(crearLabel("Horario:"), configLabelGBC(0, row));
        txtHorario = crearValor("-");
        card.add(txtHorario, configValorGBC(1, row));
        row++;

        card.add(crearLabel("Aula:"), configLabelGBC(0, row));
        txtAula = crearValor("-");
        card.add(txtAula, configValorGBC(1, row));
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
        lbl.setForeground(new Color(100, 100, 100));
        return lbl;
    }

    public void setClase(ClaseDTO claseDTO){
        this.claseDTO = claseDTO;
        if(claseDTO != null){
            lblTituloMateria.setText(claseDTO.getNombre().toUpperCase());
            txtProfesor.setText(claseDTO.getProfesor());
            txtHorario.setText(claseDTO.getHorario());
            txtAula.setText(claseDTO.getAula());
            txtCampus.setText(claseDTO.getCampus());
            txtDetalles.setText(claseDTO.getDetalles());
            txtCosto.setText("$ " + String.format("%.2f", claseDTO.getCosto()));
        }
    }
}