package interfaces;
import dto.pagarAdeudo.SolicitudPagoDTO;

import java.awt.event.ActionListener;

/**
 *  CASO DE USO PAGAR ADEUDO
 * @author Escalante, Sebastian
 */
public interface IFachadaBanco {
    void mostrarPantallaPago(ActionListener listenerBotonPagar);
    SolicitudPagoDTO ejecutarPago(SolicitudPagoDTO solicitud);
    void cerrarVentana();
}
