package interfaces;
import itson.pagarAdeudo.SolicitudPagoDTOI;

import java.awt.event.ActionListener;

/**
 *  CASO DE USO PAGAR ADEUDO
 * @author Escalante, Sebastian
 */
public interface IFachadaBanco {
    void mostrarPantallaPago(ActionListener listenerBotonPagar);
    SolicitudPagoDTOI ejecutarPago(SolicitudPagoDTOI solicitud);
    void cerrarVentana();
}
