package interfaces;

import dtoGobierno.EstudianteDTO;
import pagarAdeudo.ClaseDTO;
import pagarAdeudo.PrestamoDTO;
import pagarAdeudo.SolicitudPagoDTO;
import solicitarBeca.dominio.enums.pagarAdeudo.MetodoPago;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public interface IFachadaPago {

    //itson
    List<PrestamoDTO> solicitarListaPrestamos(EstudianteDTO estudianteDTO);
    List<ClaseDTO> solicitarListaClases(EstudianteDTO estudianteDTO);
    boolean notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO);

    //banco
    void solicitarVistaDePago(ActionListener listener);
    SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO);
    void cerrarVentana();
    //paypal
    void solicitarVistaPaypal(ActionListener listener);
    void cerrarVentanaPaypal();
    SolicitudPagoDTO realizarPagoPaypal(SolicitudPagoDTO dto);
}
