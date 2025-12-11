package controles;

import java.awt.event.ActionListener;
import java.util.List;
import adaptadoresPagoAdeudo.SolicitudPagoAdapdator;
import excepciones.NegociosSolicitarPagoException;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import interfaces.*;
import objetosNegocio.bo.pagarAdeudo.interfaces.IAdeudoBO;
import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import dto.pagarAdeudo.SolicitudPagoDTO;
import solicitarBeca.EstudianteDTO;

public class ControlPago {
    protected final IAdeudoBO iAdeudoBO;
    protected final IFachadaBanco iFachadaBanco;
    protected final IFachadaPayPal iFachadaPayPal;

    public ControlPago(IAdeudoBO adeudoBO, IFachadaBanco iFachadaBanco, IFachadaPayPal fachadaPayPal){
        this.iAdeudoBO = adeudoBO;
        this.iFachadaPayPal = fachadaPayPal;
        this.iFachadaBanco = iFachadaBanco;
    }

    public List<PrestamoDTO> solicitarListaPrestamos(EstudianteDTO estudianteDTO) {
        Long matricula = estudianteDTO.getMatricula();
        return iAdeudoBO.obtenerDetallePrestamo(matricula);
    }

    public List<ClaseDTO> solicitarListaClases(EstudianteDTO estudianteDTO) {
        Long matricula = estudianteDTO.getMatricula();
        return iAdeudoBO.obtenerDetalleClase(matricula);
    }

    public void solicitarVistaPago(ActionListener listenerBotonPagar) throws NegociosSolicitarPagoException{
        try{
            iFachadaBanco.mostrarPantallaPago(listenerBotonPagar);
        }catch (NegociosSolicitarPagoException exception){
            throw new NegociosSolicitarPagoException("Error al solicitar la vista de pago");
        }
    }

    public SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO){
        try{
            SolicitudPagoDTOI solicitudPagoDTOI = iFachadaBanco.ejecutarPago(SolicitudPagoAdapdator.toDTOI(solicitudPagoDTO));
            return SolicitudPagoAdapdator.toDTO(solicitudPagoDTOI);
        }catch (NegociosSolicitarPagoException exception){
            throw new NegociosSolicitarPagoException("Error al realizar el pago con el banco");
        }

    }

    public void cerrarVentanaBanco() {
        try{
            iFachadaBanco.cerrarVentana();
        }catch (NegociosSolicitarPagoException exception){
            throw new NegociosSolicitarPagoException("Error al intentar solicitar cerrar ventana del banco");
        }

    }

    public void solicitarVistaPayPal(double monto, String concepto, ActionListener listener){
        try{
            iFachadaPayPal.mostrarPantallaPago(monto, concepto, listener);
        }catch (NegociosSolicitarPagoException exception){
            throw new NegociosSolicitarPagoException("Error al solictar vista de paypal");
        }

    }

    public void cerrarVentanaPaypal(){
        try{
            iFachadaPayPal.cerrarVentanaPaypal();
        }catch (NegociosSolicitarPagoException exception){
            throw new NegociosSolicitarPagoException("Error al intentar solicitar cerrar ventana de paypal");
        }

    }


    public void notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO){
        SolicitudPagoDTOI solicitudPagoDTOI = SolicitudPagoAdapdator.toDTOI(solicitudPagoDTO);
        iAdeudoBO.enviarSolicitudPago(solicitudPagoDTOI);
    }
}