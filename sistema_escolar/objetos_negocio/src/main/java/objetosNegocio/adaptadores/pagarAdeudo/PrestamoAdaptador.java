package objetosNegocio.adaptadores.pagarAdeudo;

import datos.dominioItson.pagarAdeudo.Prestamo;
import dto.pagarAdeudo.PrestamoDTO;
import itson.pagarAdeudo.PrestamoDTOI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrestamoAdaptador {

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

    public static List<PrestamoDTO> toDTO(List<PrestamoDTOI> dtosI) {
        if (dtosI == null) {
            return new ArrayList<>();
        }
        return dtosI.stream()
                .map(PrestamoAdaptador::toDTO)
                .collect(Collectors.toList());
    }
}
