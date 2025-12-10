package objetosNegocio.adaptadores.pagarAdeudo;

import datos.dominioItson.pagarAdeudo.Clase;
import datos.dominioItson.pagarAdeudo.enums.Campus;
import dto.pagarAdeudo.ClaseDTO;
import itson.pagarAdeudo.ClaseDTOI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClaseAdaptador {

    public static ClaseDTO toDTO(ClaseDTOI dtoI) {
        if (dtoI == null) return null;

        ClaseDTO dto = new ClaseDTO();
        dto.setCosto(dtoI.getCosto());
        dto.setNombre(dtoI.getNombre());
        dto.setHorario(dtoI.getHorario());
        dto.setProfesor(dtoI.getProfesor());
        dto.setAula(dtoI.getAula());
        dto.setCampus(dtoI.getCampus());
        dto.setDetalles(dtoI.getDetalles());

        return dto;
    }

    public static ClaseDTOI toDTOI(ClaseDTO dto) {
        if (dto == null) return null;
        ClaseDTOI dtoI = new ClaseDTOI();
        dtoI.setCosto(dto.getCosto());
        dtoI.setNombre(dto.getNombre());
        dtoI.setHorario(dto.getHorario());
        dtoI.setProfesor(dto.getProfesor());
        dtoI.setAula(dto.getAula());
        dtoI.setCampus(dto.getCampus());
        dtoI.setDetalles(dto.getDetalles());

        return dtoI;
    }

    public static Clase toEntity(ClaseDTOI dto) {
        if (dto == null) {
            return null;
        }
        Clase entity = new Clase();
        entity.setIdEstudiante(dto.getIdEstudiante());
        entity.setNombre(dto.getNombre());
        entity.setCosto(dto.getCosto());
        entity.setHorario(dto.getHorario());
        entity.setProfesor(dto.getProfesor());
        entity.setAula(dto.getAula());
        entity.setDetalles(dto.getDetalles());
        entity.setEstatus(dto.getEstatus());
        entity.setCampus(Campus.valueOf(dto.getCampus()));
        return entity;
    }

    public static List<Clase> toEntity(List<ClaseDTOI> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }

        return dtos.stream()
                .map(ClaseAdaptador::toEntity)
                .collect(Collectors.toList());
    }

}
