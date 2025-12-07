package interfaces;


import pagarAdeudo.SolicitudPagoDTO;

import java.awt.event.ActionListener;

/**
 *  CASO DE USO PAGAR ADEUDO
 * @author Escalante, Sebastian
 */
public interface IFachadaPayPal {
    void mostrarPantallaPago(double monto, String concepto, ActionListener listener);
    void cerrarVentanaPaypal();
}
