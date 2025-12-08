package interfaces.pagarAdeudo;

import datos.dominioItson.pagarAdeudo.Clase;
import datos.dominioItson.pagarAdeudo.Prestamo;
import itson.pagarAdeudo.SolicitudPagoDTOI;

import java.util.List;

public interface IAdeudoBO {
    List<Prestamo> obtenerDetallePrestamo(Long matricula);
    List<Clase> obtenerDetalleClase(Long matricula);
    void enviarSolicitudPago(SolicitudPagoDTOI solicitudPagoDTOI);
}
