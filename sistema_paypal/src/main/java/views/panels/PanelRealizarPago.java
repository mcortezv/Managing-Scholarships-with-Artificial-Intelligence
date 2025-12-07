package views.panels;

import views.stylesPaypal.Button;
import views.stylesPaypal.Label;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelRealizarPago extends JPanel {
    private final Label lblNombreUsuario;
    private final Label lblSaldoDisponible;
    private final Label lblMontoPagar;
    private final Button btnPagarFinal;

    public PanelRealizarPago() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
        this.setBorder(new EmptyBorder(40, 40, 40, 40));

        Label lblTitulo = new Label("Confirmar Pago");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color TEXT_COLOR_DARK = new Color(50, 50, 50);
        lblTitulo.setForeground(TEXT_COLOR_DARK); // Forzamos color oscuro

        lblNombreUsuario = new Label("Hola, Usuario");
        lblNombreUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblNombreUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNombreUsuario.setForeground(Color.GRAY); // Un poco más claro para diferenciar

        lblSaldoDisponible = new Label("Saldo disponible: $0.00");
        lblSaldoDisponible.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblSaldoDisponible.setForeground(new Color(0, 180, 0)); // Verde (ajustado para fondo blanco)
        lblSaldoDisponible.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(300, 2));
        sep.setForeground(new Color(230, 230, 230)); // Gris muy claro

        lblMontoPagar = new Label("Total a Pagar: $0.00");
        lblMontoPagar.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblMontoPagar.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color TEXT_COLOR_BLACK = Color.BLACK;
        lblMontoPagar.setForeground(TEXT_COLOR_BLACK);

        btnPagarFinal = new Button("Pagar Ahora") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(28, 100, 238));

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btnPagarFinal.setForeground(Color.BLACK);
        btnPagarFinal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnPagarFinal.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPagarFinal.setMaximumSize(new Dimension(220, 50));
        btnPagarFinal.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.add(Box.createVerticalGlue());
        this.add(lblTitulo);
        this.add(Box.createVerticalStrut(20));

        this.add(lblNombreUsuario);
        this.add(Box.createVerticalStrut(10));

        this.add(lblSaldoDisponible);
        this.add(Box.createVerticalStrut(30));

        this.add(sep);
        this.add(Box.createVerticalStrut(30));

        this.add(lblMontoPagar);
        this.add(Box.createVerticalStrut(40));

        this.add(btnPagarFinal);
        this.add(Box.createVerticalGlue());
    }

    public void setDatos(String nombre, double saldo, double montoCobro) {
        lblNombreUsuario.setText("Hola, " + nombre);
        lblSaldoDisponible.setText(String.format("Saldo disponible: $%,.2f MXN", saldo));
        lblMontoPagar.setText(String.format("Total a pagar: $%,.2f MXN", montoCobro));
    }

    public void setAccionPagarFinal(ActionListener listener) {
        for (ActionListener al : btnPagarFinal.getActionListeners()) {
            btnPagarFinal.removeActionListener(al);
        }
        btnPagarFinal.addActionListener(listener);
    }
}