package apiPaypal;

import apiPaypal.controles.ControlPaypalAPI;
import apiPaypal.interfaces.IPaypalAPI;

import java.awt.event.ActionListener;

public class PaypalAPI implements IPaypalAPI {
    private final ControlPaypalAPI controlPaypalAPI;

    public PaypalAPI() {
        this.controlPaypalAPI = new ControlPaypalAPI();
    }

    @Override
    public void mostrarVentanaPago(double monto, String concepto, ActionListener listenerExito) {
        controlPaypalAPI.abrirVentanaPago(monto, concepto, listenerExito);
    }

    @Override
    public void cerrarVentana() {
        controlPaypalAPI.cerrarVentana();
    }
}
