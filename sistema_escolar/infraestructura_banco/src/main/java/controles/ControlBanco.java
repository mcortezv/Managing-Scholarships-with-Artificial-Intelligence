package controles;

import apiBanco.BancoAPI;
import apiBanco.interfaces.IBancoAPI;
import dto.pagarAdeudo.SolicitudPagoDTO;

import java.awt.event.ActionListener;

/**
 *  CASO DE USO PAGAR ADEUDO
 * @author Escalante, Sebastian
 */
public class ControlBanco {
    IBancoAPI iBancoAPI;

    public ControlBanco(){
        this.iBancoAPI = new BancoAPI();
    }

    public void mostrarVentanaPago(ActionListener listener) {
        iBancoAPI.mostrarVentanaPago(listener);
    }

    public SolicitudPagoDTO ejecutarPago(SolicitudPagoDTO solicitud) {
        double monto = solicitud.getMontoPagado();
        String concepto = "Pago Colegiatura/Libros";
        boolean exito = iBancoAPI.ejecutarPago(monto, concepto);
        if (exito) {
            solicitud.setEstatusPago("Pagado");
        } else {
            solicitud.setEstatusPago("Rechazado");
        }
        return solicitud;
    }

    public void cerrarVentana() {
        iBancoAPI.cerrarVentana();
    }
}
