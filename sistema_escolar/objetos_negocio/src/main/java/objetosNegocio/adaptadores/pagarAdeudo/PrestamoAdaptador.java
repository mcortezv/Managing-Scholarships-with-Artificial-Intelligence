package objetosNegocio.adaptadores.pagarAdeudo;

import datos.dominioItson.pagarAdeudo.Prestamo;
import dto.pagarAdeudo.PrestamoDTO;
import itson.pagarAdeudo.PrestamoDTOI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador de Datos para la entidad Préstamo (Biblioteca).
 * <p>
 * Esta clase de utilidad gestiona la transformación de objetos de transferencia de datos
 * relacionados con libros y multas de biblioteca. Convierte el formato externo (DTOI)
 * proporcionado por la interfaz del sistema legado al formato interno (DTO) de la aplicación.
 */
public class PrestamoAdaptador {

    /**
     * Convierte un objeto de préstamo externo (DTOI) a un objeto interno (DTO).
     * Mapea los detalles del libro, fechas de préstamo/devolución y costos asociados.
     *
     * @param dtoI El objeto Prestamo proveniente del sistema externo.
     * @return El objeto PrestamoDTO formateado para la capa de presentación, o null si la entrada es nula.
     */
    public static PrestamoDTO toDTO(PrestamoDTOI dtoI) {
        if (dtoI == null) return null;
        PrestamoDTO dto = new PrestamoDTO();
        dto.setCosto(dtoI.getCosto());
        dto.setFechaPrestamo(dtoI.getFechaPrestamo());
        dto.setFechaDevolucion(dtoI.getFechaDevolucion());
        dto.setIsbn(dtoI.getIsbn());
        dto.setTitulo(dtoI.getTitulo());
        dto.setCampus(dtoI.getCampus());
        dto.setDetalles(dtoI.getDetalles());
        return dto;
    }

    /**
     * Convierte una lista completa de préstamos externos a una lista de objetos internos.
     * Facilita la carga de tablas y listas en la interfaz de usuario procesando la colección en lote.
     *
     * @param dtosI Lista de objetos PrestamoDTOI externos.
     * @return Una lista de PrestamoDTO internos. Devuelve una lista vacía si la entrada es nula.
     */
    public static List<PrestamoDTO> toDTO(List<PrestamoDTOI> dtosI) {
        if (dtosI == null) {
            return new ArrayList<>();
        }
        return dtosI.stream()
                .map(PrestamoAdaptador::toDTO)
                .collect(Collectors.toList());
    }
}