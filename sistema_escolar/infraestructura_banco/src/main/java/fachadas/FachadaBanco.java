package fachadas;
import controles.ControlBanco;
import dto.pagarAdeudo.SolicitudPagoDTO;
import interfaces.IFachadaBanco;

import java.awt.event.ActionListener;


/**
 *  CASO DE USO PAGAR ADEUDO
 * @author Escalante, Sebastian
 */
public class FachadaBanco implements IFachadaBanco {

    private final ControlBanco controlBanco;

    public FachadaBanco(ControlBanco controlBanco) {
        this.controlBanco = controlBanco;
    }

    @Override
    public void mostrarPantallaPago(ActionListener listenerBotonPagar) {
        controlBanco.mostrarVentanaPago(listenerBotonPagar);
    }

    @Override
    public SolicitudPagoDTO ejecutarPago(SolicitudPagoDTO solicitud) {
        return controlBanco.ejecutarPago(solicitud);
    }

    @Override
    public void cerrarVentana() {
        controlBanco.cerrarVentana();
    }
}
