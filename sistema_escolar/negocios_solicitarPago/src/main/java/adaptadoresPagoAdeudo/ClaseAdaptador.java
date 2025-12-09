package adaptadoresPagoAdeudo;

import datos.dominioItson.pagarAdeudo.Clase;
import dto.pagarAdeudo.ClaseDTO;

public class ClaseAdaptador {
    public static ClaseDTO toDTO(Clase entity) {
        if (entity == null) return null;
        ClaseDTO dto = new ClaseDTO();
        dto.setNombre(entity.getNombre());
        dto.setCosto(entity.getCosto());
        dto.setHorario(entity.getHorario());
        dto.setProfesor(entity.getProfesor());
        dto.setAula(entity.getAula());
        dto.setCampus(String.valueOf(entity.getCampus()));
        dto.setDetalles(entity.getDetalles());
        dto.setEstatus(entity.getEstatus());
        dto.setIdEstudiante(entity.getIdEstudiante());
        return dto;
    }
}