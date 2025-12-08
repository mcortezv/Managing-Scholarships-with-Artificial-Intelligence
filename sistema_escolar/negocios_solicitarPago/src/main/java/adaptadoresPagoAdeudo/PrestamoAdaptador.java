package adaptadoresPagoAdeudo;

import datos.dominioItson.pagarAdeudo.Prestamo;
import itson.pagarAdeudo.PrestamoDTOI;
import dto.pagarAdeudo.PrestamoDTO;

public class PrestamoAdaptador {

    public static PrestamoDTO toDTO(Prestamo entity) {
        if (entity == null) return null;
        PrestamoDTO dto = new PrestamoDTO();
        dto.setCosto(entity.getCosto());
        dto.setFechaDevolucion(entity.getFechaDevolucionProgramada());
        dto.setIsbn(entity.getIsbn());
        dto.setTitulo(entity.getTitulo());
        dto.setCampus(entity.getCampus());
        dto.setDetalles(entity.getDetalles());
        dto.setEstatus(entity.getEstatus());
        dto.setIdEstudiante(entity.getIdEstudiante());
        return dto;
    }
}