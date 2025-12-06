package controles;

import apiPaypal.PaypalAPI;
import apiPaypal.interfaces.IPaypalAPI;
import pagarAdeudo.SolicitudPagoDTO;
import java.awt.event.ActionListener;

/**
 *  CASO DE USO PAGAR ADEUDO
 * @author Escalante, Sebastian
 */
public class ControlPayPal {
    IPaypalAPI api;

    public ControlPayPal(){
        this.api = new PaypalAPI();
    }

    public void mostrarVentanaPago(ActionListener listener) {
        api.mostrarVentanaPago(listener);
    }

    public SolicitudPagoDTO ejecutarPago(SolicitudPagoDTO solicitud) {
        double monto = solicitud.getMontoPagado();
        String concepto = "Pago Colegiatura/Libros";
        boolean exito = api.ejecutarPago(monto, concepto);
        if (exito) {
            solicitud.setEstatusPago("Pagado");
        } else {
            solicitud.setEstatusPago("Rechazado");
        }
        return solicitud;
    }

    public void cerrarVentana() {
        api.cerrarVentana();
    }

}
