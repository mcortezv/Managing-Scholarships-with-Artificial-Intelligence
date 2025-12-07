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
    public FachadaPayPal() {
        this.controlPayPal = new ControlPayPal();
    }

    // O tu constructor con inyección si prefieres mantenerlo
    public FachadaPayPal(ControlPayPal controlPayPal){
        this.controlPayPal = controlPayPal;
    }

    @Override
    public void mostrarPantallaPago(double monto, String concepto, ActionListener listener) {
        // Pasamos los datos al control de infraestructura
        controlPayPal.mostrarVentanaPago(monto, concepto, listener);
    }

    @Override
    public void cerrarVentanaPaypal() {
        controlPayPal.cerrarVentana();
    }

    // ELIMINADO: public SolicitudPagoDTO ejecutarPago(...)
    // La lógica de cobro se movió dentro de la ventana de PayPal (ControlPaypalAPI)
}