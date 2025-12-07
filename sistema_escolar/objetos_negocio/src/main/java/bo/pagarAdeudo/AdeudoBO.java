package bo.pagarAdeudo;

import adaptadores.pagarAdeudo.ClaseAdaptador;
import adaptadores.pagarAdeudo.PrestamoAdaptador;
import datos.dominioItson.pagarAdeudo.Clase;
import datos.dominioItson.pagarAdeudo.Prestamo;
import itson.pagarAdeudo.ClaseDTOI;
import itson.pagarAdeudo.PrestamoDTOI;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import interfaces.IFachadaITSON;
import interfaces.pagarAdeudo.IAdeudoBO;

import java.util.List;

public class AdeudoBO implements IAdeudoBO {
    private final IFachadaITSON iFachadaITSON;

    public AdeudoBO(IFachadaITSON iFachadaITSON){
        this.iFachadaITSON = iFachadaITSON;
    }

    @Override
    public List<Prestamo> obtenerDetallePrestamo(Long matricula) {
        List<PrestamoDTOI> listaPrestamosI = iFachadaITSON.solicitarListaPrestamso(matricula);
        return PrestamoAdaptador.toEntity(listaPrestamosI);
    }

    @Override
    public List<Clase> obtenerDetalleClase(Long matricula) {
        List<ClaseDTOI> listaClasesI = iFachadaITSON.solicitarListaClases(matricula);
        return ClaseAdaptador.toEntity(listaClasesI);
    }

    @Override
    public boolean enviarSolicitudPago(SolicitudPagoDTOI solicitudPagoDTO) {
        return iFachadaITSON.notificarLiquidacion(solicitudPagoDTO);
    }
}