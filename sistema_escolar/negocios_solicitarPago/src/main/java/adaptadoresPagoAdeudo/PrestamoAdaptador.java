package adaptadoresPagoAdeudo;

import datos.dominioItson.pagarAdeudo.Prestamo;
import itson.pagarAdeudo.PrestamoDTOI;
import pagarAdeudo.PrestamoDTO;

public class PrestamoAdaptador {

    public static PrestamoDTO toDTO(PrestamoDTOI dtoi) {
        if (dtoi == null) {
            return null;
        }
        PrestamoDTO dto = new PrestamoDTO();
        dto.setCosto(dtoi.getCosto());
        dto.setFechaPrestamo(dtoi.getFechaPrestamo());
        dto.setFechaDevolucion(dtoi.getFechaDevolucion());
        dto.setIsbn(dtoi.getIsbn());
        dto.setTitulo(dtoi.getTitulo());
        dto.setCampus(dtoi.getCampus());
        dto.setDetalles(dtoi.getDetalles());
        dto.setEstatus(dtoi.getEstatus());
        dto.setIdEstudiante(dtoi.getIdEstudiante());
        return dto;
    }

    public static PrestamoDTOI toDTOI(PrestamoDTO dto) {
        if (dto == null) {
            return null;
        }
        PrestamoDTOI dtoi = new PrestamoDTOI();
        dtoi.setCosto(dto.getCosto());
        dtoi.setFechaPrestamo(dto.getFechaPrestamo());
        dtoi.setFechaDevolucion(dto.getFechaDevolucion());
        dtoi.setIsbn(dto.getIsbn());
        dtoi.setTitulo(dto.getTitulo());
        dtoi.setCampus(dto.getCampus());
        dtoi.setDetalles(dto.getDetalles());
        dtoi.setEstatus(dto.getEstatus());
        dtoi.setIdEstudiante(dto.getIdEstudiante());
        return dtoi;
    }

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