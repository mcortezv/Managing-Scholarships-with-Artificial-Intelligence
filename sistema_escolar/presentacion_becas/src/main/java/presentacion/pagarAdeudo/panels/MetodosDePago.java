package presentacion.pagarAdeudo.panels;

import bo.sesion.SesionUsuario;
import dtoGobierno.EstudianteDTO;
import presentacion.pagarAdeudo.PagarAdeudo;
import presentacion.pagarAdeudo.PanelPagarAdeudo;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import presentacion.styles.Button;
import presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MetodosDePago extends PanelPagarAdeudo {

    public MetodosDePago(PagarAdeudo frame, CoordinadorAplicacionPagarAdeudo coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
    }

    @Override
    public void startComponents() {
        centralPanel.removeAll();

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Style.PANEL_COLOR);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel title = new JLabel("Metodo de Pago");
        title.setFont(new Font("SansSerif", Font.PLAIN, 28));
        title.setOpaque(true);
        title.setBackground(new Color(235, 235, 235));
        title.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(title);
        centralPanel.add(titlePanel);

        JPanel contentBox = new JPanel();
        contentBox.setPreferredSize(new Dimension(450, 500));
        contentBox.setMaximumSize(new Dimension(450, 500));
        contentBox.setBackground(new Color(235, 235, 235));
        contentBox.setLayout(new BoxLayout(contentBox, BoxLayout.Y_AXIS));
        contentBox.setBorder(BorderFactory.createEmptyBorder(80, 30, 80, 30));

        presentacion.styles.Button btnTarjeta = new presentacion.styles.Button("Débito/Crédito");
        btnTarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTarjeta.setMaximumSize(new Dimension(200, 50));

        presentacion.styles.Button btnPayPal = new Button("PayPal");
        btnPayPal.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPayPal.setMaximumSize(new Dimension(200, 50));

        contentBox.add(btnTarjeta);
        contentBox.add(Box.createVerticalStrut(30));
        contentBox.add(btnPayPal);

        centralPanel.add(contentBox);

        for (ActionListener al : btnBack.getActionListeners()) {
            btnBack.removeActionListener(al);
        }

        btnBack.addActionListener(e -> {
            String tipo = coordinadorAplicacion.getTipoAdeudo();
            if (Objects.equals(tipo, "Biblioteca")) {
                mainFrame.showPanel("listaPrestamosBiblioteca");
            } else if (Objects.equals(tipo, "Colegiatura")) {
                mainFrame.showPanel("listaClasesColegiatura");
            }
        });

        btnTarjeta.addActionListener(e -> {
            coordinadorAplicacion.seleccionarMetodoPago("BANCO");
        });

        btnPayPal.addActionListener(e -> {
            coordinadorAplicacion.seleccionarMetodoPago("PAYPAL");
        });

        revalidate();
        repaint();
    }
}
