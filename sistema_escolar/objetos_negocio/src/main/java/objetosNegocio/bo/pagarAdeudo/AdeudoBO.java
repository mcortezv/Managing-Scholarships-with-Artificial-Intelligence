package bo.pagarAdeudo;

import adaptadores.pagarAdeudo.ClaseAdaptador;
import adaptadores.pagarAdeudo.PrestamoAdaptador;
import bo.pagarAdeudo.excepciones.AdeudoException;
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
        List<Prestamo> prestamos = PrestamoAdaptador.toEntity(listaPrestamosI);
        if (prestamos.isEmpty()) {
            throw new AdeudoException("El estudiante no tiene préstamos pendientes de pago.");
        }
        return prestamos;
    }

    @Override
    public List<Clase> obtenerDetalleClase(Long matricula) {
        List<ClaseDTOI> listaClasesI = iFachadaITSON.solicitarListaClases(matricula);
        List<Clase> clases = ClaseAdaptador.toEntity(listaClasesI);
        if (clases.isEmpty()) {
            throw new AdeudoException("El estudiante no tiene adeudos de colegiatura.");
        }
        return clases;
    }

    @Override
    public void enviarSolicitudPago(SolicitudPagoDTOI solicitudPagoDTO) {
        iFachadaITSON.notificarLiquidacion(solicitudPagoDTO);
    }
}