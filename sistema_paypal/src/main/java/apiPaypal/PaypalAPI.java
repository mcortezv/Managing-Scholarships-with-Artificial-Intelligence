package apiPaypal;

import apiPaypal.controles.ControlPaypalAPI;
import apiPaypal.interfaces.IPaypalAPI;

import java.awt.event.ActionListener;

public class PaypalAPI implements IPaypalAPI {
    private ControlPaypalAPI controlPaypalAPI;

    @Override
    public void mostrarVentanaPago(ActionListener actionListener) {

    }

    @Override
    public boolean ejecutarPago(double monto, String concepto) {
        return false;
    }

    @Override
    public void cerrarVentana() {

    }
}
