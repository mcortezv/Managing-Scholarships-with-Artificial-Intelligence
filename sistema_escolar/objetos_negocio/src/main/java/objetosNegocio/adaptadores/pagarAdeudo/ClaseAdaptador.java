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

    public static List<ClaseDTO> toDTO(List<ClaseDTOI> dtosI) {
        if (dtosI == null) {
            return new ArrayList<>();
        }
        return dtosI.stream()
                .map(ClaseAdaptador::toDTO)
                .collect(Collectors.toList());
    }

}
