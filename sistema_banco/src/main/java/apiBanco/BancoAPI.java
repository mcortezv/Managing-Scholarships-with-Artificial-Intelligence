package apiBanco;

import apiBanco.controles.ControlBancoAPI;
import apiBanco.interfaces.IBancoAPI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BancoAPI implements IBancoAPI {
    private final ControlBancoAPI controlBancoAPI;

    public BancoAPI() {
        this.controlBancoAPI = new ControlBancoAPI();
    }

    @Override
    public void mostrarVentanaPago(ActionListener actionListener) {
        controlBancoAPI.abrirVentanaPago(actionListener);
    }

    @Override
    public boolean ejecutarPago(double monto, String concepto) {
        return controlBancoAPI.confirmarPago(monto, concepto);
    }

    @Override
    public void cerrarVentana() {
        controlBancoAPI.cerrarVentana();
    }
}
