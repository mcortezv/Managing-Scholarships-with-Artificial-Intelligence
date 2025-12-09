package controles;

import apiBanco.BancoAPI;
import apiBanco.interfaces.IBancoAPI;
import dto.pagarAdeudo.SolicitudPagoDTO;
import itson.pagarAdeudo.SolicitudPagoDTOI;

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

    public SolicitudPagoDTOI ejecutarPago(SolicitudPagoDTOI solicitud) {
        double monto = solicitud.getMontoPagado();
        String estatusResultante = procesarTransaccion(monto);
        solicitud.setEstatusPago(estatusResultante);
        return solicitud;
    }

    private String procesarTransaccion(double monto) {
        String concepto = "Pago desde app de itson";
        boolean exito = iBancoAPI.ejecutarPago(monto, concepto);
        if (exito) {
            return "Pagado";
        } else {
            return "Rechazado";
        }
    }

    public void cerrarVentana() {
        iBancoAPI.cerrarVentana();
    }
}
