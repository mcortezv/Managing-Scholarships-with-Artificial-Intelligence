package fachadas;

import controles.ControlPago;
import dtoGobierno.EstudianteDTO;
import pagarAdeudo.ClaseDTO;
import pagarAdeudo.PrestamoDTO;
import pagarAdeudo.SolicitudPagoDTO;
import interfaces.IFachadaPago;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class FachadaPago implements IFachadaPago {
    public ControlPago controlPago;

    public FachadaPago(ControlPago controlPago){
        this.controlPago = controlPago;
    }

    @Override
    public List<PrestamoDTO> solicitarListaPrestamos(EstudianteDTO estudianteDTO) {
        return controlPago.solicitarListaPrestamos(estudianteDTO);
    }

    @Override
    public List<ClaseDTO> solicitarListaClases(EstudianteDTO estudianteDTO) {
        return controlPago.solicitarListaClases(estudianteDTO);
    }

    @Override
    public void solicitarVistaDePago(ActionListener listener) {
        controlPago.solicitarVistaPago(listener);
    }

    @Override
    public SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO) {
        return controlPago.realizarPago(solicitudPagoDTO);
    }

    @Override
    public void cerrarVentana() {
        controlPago.cerrarVentanaBanco();
    }

    @Override
    public void solicitarVistaPaypal(ActionListener listener) {
        controlPago.solicitarVistaPayPal(listener);
    }

    @Override
    public void cerrarVentanaPaypal() {
        controlPago.cerrarVentanaPaypal();
    }

    @Override
    public SolicitudPagoDTO realizarPagoPaypal(SolicitudPagoDTO dto) {
        return controlPago.realizarPagoPaypal(dto);
    }

    @Override
    public boolean notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO) {
        return controlPago.notificarLiquidacion(solicitudPagoDTO);
    }
}
