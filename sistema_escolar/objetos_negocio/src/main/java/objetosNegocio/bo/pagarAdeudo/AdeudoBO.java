package objetosNegocio.bo.pagarAdeudo;

import objetosNegocio.adaptadores.pagarAdeudo.excepciones.AdeudoException;
import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import itson.pagarAdeudo.ClaseDTOI;
import itson.pagarAdeudo.PrestamoDTOI;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import interfaces.IFachadaITSON;
import objetosNegocio.adaptadores.pagarAdeudo.ClaseAdaptador;
import objetosNegocio.adaptadores.pagarAdeudo.PrestamoAdaptador;
import objetosNegocio.bo.pagarAdeudo.interfaces.IAdeudoBO;

import java.util.List;

public class AdeudoBO implements IAdeudoBO {
    private final IFachadaITSON iFachadaITSON;

    public AdeudoBO(IFachadaITSON iFachadaITSON){
        this.iFachadaITSON = iFachadaITSON;
    }

    @Override
    public List<PrestamoDTO> obtenerDetallePrestamo(Long matricula) {
        List<PrestamoDTOI> listaPrestamosI = iFachadaITSON.solicitarListaPrestamos(matricula);
        List<PrestamoDTO> prestamos = PrestamoAdaptador.toDTO(listaPrestamosI);
        if (prestamos.isEmpty()) {
            throw new AdeudoException("El estudiante no tiene préstamos pendientes de pago.");
        }
        return prestamos;
    }

    @Override
    public List<ClaseDTO> obtenerDetalleClase(Long matricula) {
        List<ClaseDTOI> listaClasesI = iFachadaITSON.solicitarListaClases(matricula);
        List<ClaseDTO> clases = ClaseAdaptador.toDTO(listaClasesI);
        if (clases.isEmpty()) {
            throw new AdeudoException("El estudiante no tiene adeudos de colegiatura.");
        }
        return clases;
    }

    @Override
    public void enviarSolicitudPago(SolicitudPagoDTOI solicitudPagoDTO) {
        try{
            iFachadaITSON.notificarLiquidacion(solicitudPagoDTO);
        }catch (AdeudoException exception){
            throw new AdeudoException("No se pudo enviar la solicitud del pago");
        }

    }
}