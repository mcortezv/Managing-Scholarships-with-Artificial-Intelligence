package controles;

import apiPaypal.PaypalAPI;
import apiPaypal.interfaces.IPaypalAPI;
import java.awt.event.ActionListener;

public class ControlPayPal {

    private final IPaypalAPI api;

    public ControlPayPal(){
        this.api = new PaypalAPI();
    }

    public void mostrarVentanaPago(double monto, String concepto, ActionListener listener) {
        api.mostrarVentanaPago(monto, concepto, listener);
    }

    public void cerrarVentana() {
        api.cerrarVentana();
    }
}