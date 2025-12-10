package controles;

import java.awt.event.ActionListener;
import java.util.List;
import adaptadoresPagoAdeudo.ClaseAdaptador;
import adaptadoresPagoAdeudo.PrestamoAdaptador;
import adaptadoresPagoAdeudo.SolicitudPagoAdapdator;
import datos.dominioItson.pagarAdeudo.Clase;
import datos.dominioItson.pagarAdeudo.Prestamo;
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
        List<Prestamo> listaEntidades = iAdeudoBO.obtenerDetallePrestamo(matricula);
        return listaEntidades.stream()
                .map(PrestamoAdaptador::toDTO)
                .toList();
    }

    public List<ClaseDTO> solicitarListaClases(EstudianteDTO estudianteDTO) {
        Long matricula = estudianteDTO.getMatricula();
        List<Clase> listaEntidades = iAdeudoBO.obtenerDetalleClase(matricula);
        return listaEntidades.stream()
                .map(ClaseAdaptador::toDTO)
                .toList();
    }
    public void solicitarVistaPago(ActionListener listenerBotonPagar) {
        iFachadaBanco.mostrarPantallaPago(listenerBotonPagar);
    }

    public SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO){
        SolicitudPagoDTOI solicitudPagoDTOI = iFachadaBanco.ejecutarPago(SolicitudPagoAdapdator.toDTOI(solicitudPagoDTO));
        return SolicitudPagoAdapdator.toDTO(solicitudPagoDTOI);
    }

    public void cerrarVentanaBanco() {
        iFachadaBanco.cerrarVentana();
    }

    public void solicitarVistaPayPal(double monto, String concepto, ActionListener listener){
        iFachadaPayPal.mostrarPantallaPago(monto, concepto, listener);
    }

    public void cerrarVentanaPaypal(){
        iFachadaPayPal.cerrarVentanaPaypal();
    }


    public void notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO){
        SolicitudPagoDTOI solicitudPagoDTOI = SolicitudPagoAdapdator.toDTOI(solicitudPagoDTO);
        iAdeudoBO.enviarSolicitudPago(solicitudPagoDTOI);
    }
}