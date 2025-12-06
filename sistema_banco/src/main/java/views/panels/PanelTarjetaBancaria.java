package views.panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import datos.dtosBanco.DatosTarjetaDTO;
import views.exceptions.BancoViewsException;
import views.stylesBanco.*;
import views.stylesBanco.Button;
import views.stylesBanco.Label;
import views.stylesBanco.TextField;
import views.validadoresBanco.Validador;

public class PanelTarjetaBancaria extends JPanel {
    private TextField campoNumero;
    private TextField campoFecha;
    private TextField campoCVV;
    private TextField campoNombre;
    private TextField campoTelefono;
    private TextField campoEmail;
    private Button btnAgregar;
    private Button btnCancelar;

    public PanelTarjetaBancaria() {
        startComponents();
    }

    private void startComponents() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Style.PANEL_COLOR != null ? Style.PANEL_COLOR : new Color(245, 245, 245));
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(new Color(235, 235, 235));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                new EmptyBorder(30, 40, 30, 40)
        ));

        Label lblTitulo = new Label("Tarjeta Bancaria");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(Style.SUBTITLE_FONT);
        lblTitulo.setForeground(Color.BLACK);
        cardPanel.add(lblTitulo);
        cardPanel.add(Box.createVerticalStrut(30));

        campoNumero = new TextField(20);
        cardPanel.add(crearBloqueInput("Número de tarjeta", campoNumero));
        cardPanel.add(Box.createVerticalStrut(15));

        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        rowPanel.setBackground(new Color(235, 235, 235));
        rowPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoFecha = new TextField(8);
        campoFecha.setMaximumSize(new Dimension(150, 50));

        campoCVV = new TextField(4);
        campoCVV.setMaximumSize(new Dimension(80, 50));

        rowPanel.add(crearBloqueInput("Vencimiento (MM/YY)", campoFecha));
        rowPanel.add(Box.createHorizontalStrut(20));
        rowPanel.add(crearBloqueInput("CVV", campoCVV));
        rowPanel.add(Box.createHorizontalGlue());

        cardPanel.add(rowPanel);
        cardPanel.add(Box.createVerticalStrut(15));

        campoNombre = new TextField(20);
        cardPanel.add(crearBloqueInput("Nombre del titular", campoNombre));
        cardPanel.add(Box.createVerticalStrut(15));

        campoTelefono = new TextField(15);
        cardPanel.add(crearBloqueInput("Teléfono", campoTelefono));
        cardPanel.add(Box.createVerticalStrut(15));

        campoEmail = new TextField(20);
        cardPanel.add(crearBloqueInput("Email de contacto", campoEmail));
        cardPanel.add(Box.createVerticalStrut(30));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setBackground(new Color(235, 235, 235));
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnCancelar = new Button("Cancelar");
        btnCancelar.setMaximumSize(new Dimension(150, 50));
        btnCancelar.setBackground(new Color(200, 60, 60));
        btnCancelar.setForeground(Color.BLACK);

        btnAgregar = new Button("Pagar");
        btnAgregar.setMaximumSize(new Dimension(150, 50));

        buttonsPanel.add(btnCancelar);
        buttonsPanel.add(Box.createHorizontalStrut(20));
        buttonsPanel.add(btnAgregar);
        cardPanel.add(buttonsPanel);
        this.add(cardPanel);
    }

    private JPanel crearBloqueInput(String texto, TextField campo) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(235, 235, 235));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Label lbl = new Label(texto);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        lbl.setForeground(Color.BLACK);
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        campo.setBackground(new Color(250, 250, 245));
        campo.setForeground(Color.BLACK);

        panel.add(lbl);
        panel.add(Box.createVerticalStrut(5));
        panel.add(campo);
        return panel;
    }

    public void addAgregarListener(ActionListener listener) {
        btnAgregar.addActionListener(listener);
    }

    public void addCancelarListener(ActionListener listener) {
        btnCancelar.addActionListener(listener);
    }

    public DatosTarjetaDTO getDatos() {
        try {
            String numero = campoNumero.getText();
            String fecha = campoFecha.getText();
            String cvv = campoCVV.getText();
            String nombre = campoNombre.getText();
            String telefono = campoTelefono.getText();
            String email = campoEmail.getText();
            Validador.validarNumeroTarjeta(numero);
            Validador.validarFechaCaducidad(fecha);
            Validador.validarCVV(cvv);
            Validador.validarNombre(nombre);
            Validador.validarTelefono(telefono);
            Validador.validarEmail(email);
            DatosTarjetaDTO dto = new DatosTarjetaDTO();
            dto.setNumeroTarjeta(numero);
            dto.setFechaVencimiento(fecha);
            dto.setCv(cvv);
            dto.setNombreTitular(nombre);
            dto.setTelefono(telefono);
            dto.setEmail(email);
            return dto;
        } catch (BancoViewsException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos Incorrectos", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
}