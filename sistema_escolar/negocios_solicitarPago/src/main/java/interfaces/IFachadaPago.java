package interfaces;

import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import dto.pagarAdeudo.SolicitudPagoDTO;
import solicitarBeca.EstudianteDTO;
import java.awt.event.ActionListener;
import java.util.List;

public interface IFachadaPago {

    //itson
    List<PrestamoDTO> solicitarListaPrestamos(EstudianteDTO estudianteDTO);
    List<ClaseDTO> solicitarListaClases(EstudianteDTO estudianteDTO);
    void notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO);

    //banco
    void solicitarVistaDePago(ActionListener listener);
    SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO);
    void cerrarVentana();

    //paypal
    void solicitarVistaPaypal(double monto, String concepto, ActionListener listener);
    void cerrarVentanaPaypal();
    SolicitudPagoDTO realizarPagoPaypal(SolicitudPagoDTO dto);
}
