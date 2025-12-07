package controles;

import java.awt.event.ActionListener;
import java.util.List;

import adaptadores.pagarAdeudo.SolicitudPagoAdaptador;
import adaptadoresPagoAdeudo.ClaseAdaptador;
import adaptadoresPagoAdeudo.PrestamoAdaptador;

import itson.pagarAdeudo.ClaseDTOI;
import itson.pagarAdeudo.PrestamoDTOI;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import interfaces.*;
import interfaces.pagarAdeudo.IAdeudoBO;
import pagarAdeudo.ClaseDTO;
import pagarAdeudo.PrestamoDTO;
import pagarAdeudo.SolicitudPagoDTO;
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

    public List<PrestamoDTO>solicitarListaPrestamos(EstudianteDTO estudianteDTO){
        Long matricula = estudianteDTO.getMatricula();
        List<PrestamoDTOI> prestamosI = iAdeudoBO.obtenerDetallePrestamo(matricula);
        return prestamosI.stream().map(PrestamoAdaptador::toDTO).toList();
    }

    public List<ClaseDTO> solicitarListaClases(EstudianteDTO estudianteDTO){
        Long matricula = estudianteDTO.getMatricula();
        List<ClaseDTOI> clasesI = iAdeudoBO.obtenerDetalleClase(matricula);
        return clasesI.stream().map(ClaseAdaptador::toDTO).toList();
    }

    public void solicitarVistaPago(ActionListener listenerBotonPagar) {
        iFachadaBanco.mostrarPantallaPago(listenerBotonPagar);
    }

    public SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO){
        return iFachadaBanco.ejecutarPago(solicitudPagoDTO);
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


    public boolean notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO){
        SolicitudPagoDTOI solicitudPagoDTOI = SolicitudPagoAdaptador.toDTOI(solicitudPagoDTO);
        return iAdeudoBO.enviarSolicitudPago(solicitudPagoDTOI);
    }
}