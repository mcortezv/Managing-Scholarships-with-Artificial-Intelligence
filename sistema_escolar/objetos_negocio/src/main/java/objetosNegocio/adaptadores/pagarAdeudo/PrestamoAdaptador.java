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
        System.out.println("Adaptador recibiendo costo: " + dtoI.getCosto());
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

    public static PrestamoDTOI toDTOI(PrestamoDTO dto) {
        if (dto == null) return null;

        PrestamoDTOI dtoI = new PrestamoDTOI();
        dtoI.setCosto(dto.getCosto());
        dtoI.setFechaPrestamo(dto.getFechaPrestamo());
        dtoI.setFechaDevolucion(dto.getFechaDevolucion());
        dtoI.setIsbn(dto.getIsbn());
        dtoI.setTitulo(dto.getTitulo());
        dtoI.setCampus(dto.getCampus());
        dtoI.setDetalles(dto.getDetalles());

        return dtoI;
    }

    public static Prestamo toEntity(PrestamoDTOI dto) {
        if (dto == null) {
            return null;
        }
        Prestamo entity = new Prestamo();
        entity.setIdEstudiante(dto.getIdEstudiante());
        entity.setCosto(dto.getCosto());
        entity.setFechaPrestamo(dto.getFechaPrestamo());
        entity.setIsbn(dto.getIsbn());
        entity.setTitulo(dto.getTitulo());
        entity.setCampus(dto.getCampus());
        entity.setDetalles(dto.getDetalles());
        entity.setEstatus(dto.getEstatus());
        entity.setFechaDevolucionProgramada(dto.getFechaDevolucion());
        return entity;
    }

    public static List<Prestamo> toEntity(List<PrestamoDTOI> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos.stream()
                .map(PrestamoAdaptador::toEntity)
                .collect(Collectors.toList());
    }
}
