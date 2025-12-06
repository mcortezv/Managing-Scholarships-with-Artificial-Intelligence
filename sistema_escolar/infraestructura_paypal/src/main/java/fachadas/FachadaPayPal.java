package fachadas;
import controles.ControlPayPal;
import pagarAdeudo.SolicitudPagoDTO;
import interfaces.IFachadaPayPal;

import java.awt.event.ActionListener;

/**
 *  CASO DE USO PAGAR ADEUDO
 * @author Escalante, Sebastian
 */
public class FachadaPayPal implements IFachadaPayPal {
    private final ControlPayPal controlPayPal;

    public FachadaPayPal(ControlPayPal controlPayPal){
        this.controlPayPal = controlPayPal;
    }

    @Override
    public void mostrarPantallaPago(ActionListener listener) {
        controlPayPal.mostrarVentanaPago(listener);
    }

    @Override
    public void cerrarVentanaPaypal() {
        controlPayPal.cerrarVentana();
    }

    @Override
    public SolicitudPagoDTO ejecutarPago(SolicitudPagoDTO solicitudPagoDTO) {
        return controlPayPal.ejecutarPago(solicitudPagoDTO);
    }
}
