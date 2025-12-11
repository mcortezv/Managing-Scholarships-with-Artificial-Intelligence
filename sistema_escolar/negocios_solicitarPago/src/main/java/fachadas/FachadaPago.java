package fachadas;

import controles.ControlPago;
import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import dto.pagarAdeudo.SolicitudPagoDTO;
import excepciones.NegociosSolicitarPagoException;
import interfaces.IFachadaPago;
import objetosNegocio.bo.sesion.SesionUsuario;
import solicitarBeca.EstudianteDTO;

import java.awt.event.ActionListener;
import java.util.List;

public class FachadaPago implements IFachadaPago {
    protected ControlPago controlPago;

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
    public void solicitarVistaDePago(ActionListener listener){
        controlPago.solicitarVistaPago(listener);
    }

    @Override
    public SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO) {
        solicitudPagoDTO.setIdEstudiante(SesionUsuario.getInstance().getEstudianteLogeado().getMatricula());
        return controlPago.realizarPago(solicitudPagoDTO);
    }

    @Override
    public void cerrarVentana() {
        controlPago.cerrarVentanaBanco();
    }

    @Override
    public void solicitarVistaPaypal(double monto, String concepto, ActionListener listener) {
        controlPago.solicitarVistaPayPal(monto, concepto, listener);
    }

    @Override
    public void cerrarVentanaPaypal() {
        controlPago.cerrarVentanaPaypal();
    }

    @Override
    public SolicitudPagoDTO realizarPagoPaypal(SolicitudPagoDTO dto) {
        dto.setIdEstudiante(SesionUsuario.getInstance().getEstudianteLogeado().getMatricula());
        dto.setEstatusPago("Pagado");
        return dto;
    }

    @Override
    public void notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO) {
        controlPago.notificarLiquidacion(solicitudPagoDTO);
    }
}
