package objetosNegocio.bo.pagarAdeudo.interfaces;

import datos.dominioItson.pagarAdeudo.Clase;
import datos.dominioItson.pagarAdeudo.Prestamo;
import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import itson.pagarAdeudo.SolicitudPagoDTOI;

import java.util.List;

public interface IAdeudoBO {
    List<PrestamoDTO> obtenerDetallePrestamo(Long matricula);
    List<ClaseDTO> obtenerDetalleClase(Long matricula);
    void enviarSolicitudPago(SolicitudPagoDTOI solicitudPagoDTOI);
}
