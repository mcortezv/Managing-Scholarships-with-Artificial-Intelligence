package adaptadoresPagoAdeudo;

import itson.pagarAdeudo.ClaseDTOI;
import pagarAdeudo.ClaseDTO;

public class ClaseAdaptador {

    public static ClaseDTO toDTO(ClaseDTOI dtoi) {
        if (dtoi == null) {
            return null;
        }
        ClaseDTO dto = new ClaseDTO();
        dto.setNombre(dtoi.getNombre());
        dto.setCosto(dtoi.getCosto());
        dto.setHorario(dtoi.getHorario());
        dto.setProfesor(dtoi.getProfesor());
        dto.setAula(dtoi.getAula());
        dto.setCampus(dtoi.getCampus());
        dto.setDetalles(dtoi.getDetalles());
        dto.setEstatus(dtoi.getEstatus());
        dto.setIdEstudiante(dtoi.getIdEstudiante());
        return dto;
    }

    public static ClaseDTOI toDTOI(ClaseDTO dto) {
        if (dto == null) {
            return null;
        }
        ClaseDTOI dtoi = new ClaseDTOI();
        dtoi.setNombre(dto.getNombre());
        dtoi.setCosto(dto.getCosto());
        dtoi.setHorario(dto.getHorario());
        dtoi.setProfesor(dto.getProfesor());
        dtoi.setAula(dto.getAula());
        dtoi.setCampus(dto.getCampus());
        dtoi.setDetalles(dto.getDetalles());
        dtoi.setEstatus(dto.getEstatus());
        dtoi.setIdEstudiante(dto.getIdEstudiante());
        return dtoi;
    }
}