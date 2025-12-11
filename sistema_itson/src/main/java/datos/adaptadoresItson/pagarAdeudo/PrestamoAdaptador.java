package datos.adaptadoresItson.pagarAdeudo;

import itson.pagarAdeudo.PrestamoDTOI;
import datos.dominioItson.pagarAdeudo.Prestamo;
import datos.repositoryItson.daoItson.pagarAdeudo.documents.PrestamoDocument;

import java.util.ArrayList;
import java.util.List;

public class PrestamoAdaptador {

    public static Prestamo toEntity(PrestamoDocument document) {
        if (document == null) return null;
        Prestamo prestamo = new Prestamo();
        prestamo.setEstatus(document.getEstatus());
        prestamo.setCosto(document.getCosto());
        prestamo.setIsbn(document.getIsbn());
        prestamo.setTitulo(document.getTitulo());
        prestamo.setFechaPrestamo(document.getFechaPrestamo());
        prestamo.setFechaDevolucionProgramada(document.getFechaDevolucion());
        prestamo.setCampus(document.getCampus());
        prestamo.setDetalles(document.getDetalles());

        return prestamo;
    }
    public static PrestamoDTOI toDtoItson(Prestamo prestamo){
        PrestamoDTOI dto = new PrestamoDTOI();
        dto.setEstatus(prestamo.getEstatus());
        dto.setCosto(prestamo.getCosto());
        dto.setIdEstudiante(prestamo.getIdEstudiante());
        dto.setFechaPrestamo(prestamo.getFechaPrestamo());
        dto.setFechaDevolucion(prestamo.getFechaDevolucionProgramada());
        dto.setIsbn(prestamo.getIsbn());
        dto.setTitulo(prestamo.getTitulo());
        dto.setCampus(prestamo.getCampus().toUpperCase());
        dto.setDetalles(prestamo.getDetalles());
        return dto;
    }

    public static List<PrestamoDTOI> toDtoItson(List<Prestamo> prestamos){
        List<PrestamoDTOI> lista = new ArrayList<>();
        for (Prestamo p : prestamos) {
            lista.add(toDtoItson(p));
        }
        return lista;
    }
}
