package views.panels;

import datos.dtosBanco.DatosTarjetaDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrameBanco extends JDialog {

    private final PanelTarjetaBancaria panelTarjeta;

    public MainFrameBanco() {
        super((Frame) null, "Pasarela de Pago - Banco", true);
        setUndecorated(true);
        setSize(600, 750);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        panelTarjeta = new PanelTarjetaBancaria();
        this.add(panelTarjeta);
    }

    public void mostrar() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }

    public void setAccionPagar(ActionListener listener) {
        panelTarjeta.addAgregarListener(listener);
    }

    public void setAccionCancelar(ActionListener listener) {
        this.panelTarjeta.addCancelarListener(listener);
    }

    public DatosTarjetaDTO obtenerDatos() {
        return panelTarjeta.getDatos();
    }
}