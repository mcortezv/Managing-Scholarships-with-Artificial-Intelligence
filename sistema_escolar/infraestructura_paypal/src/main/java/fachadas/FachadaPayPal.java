package fachadas;

import controles.ControlPayPal;
import interfaces.IFachadaPayPal;
import java.awt.event.ActionListener;

/**
 * CASO DE USO PAGAR ADEUDO (Subsistema PayPal)
 * @author Escalante, Sebastian
 */
public class FachadaPayPal implements IFachadaPayPal {
    private final ControlPayPal controlPayPal;
    public FachadaPayPal(ControlPayPal controlPayPal){
        this.controlPayPal = controlPayPal;
    }

    @Override
    public void mostrarPantallaPago(double monto, String concepto, ActionListener listener) {
        controlPayPal.mostrarVentanaPago(monto, concepto, listener);
    }

    @Override
    public void cerrarVentanaPaypal() {
        controlPayPal.cerrarVentana();
    }
}