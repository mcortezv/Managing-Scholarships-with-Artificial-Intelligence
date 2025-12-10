package datos.adaptadoresItson.pagarAdeudo;

import datos.dominioItson.pagarAdeudo.enums.Campus;
import itson.pagarAdeudo.ClaseDTOI;
import datos.dominioItson.pagarAdeudo.Clase;
import datos.repositoryItson.daoItson.pagarAdeudo.documents.ClaseDocument;

import java.util.ArrayList;
import java.util.List;

public class ClaseAdaptador {

    public static Clase toEntity(ClaseDocument document){
        if (document == null) return null;
        Clase clase = new Clase();
        clase.setEstatus(document.getEstatus());
        clase.setCosto(document.getCosto());
        clase.setIdEstudiante(document.getIdEstudiante());
        clase.setNombre(document.getNombre());
        clase.setHorario(document.getHorario());
        clase.setProfesor(document.getProfesor());
        clase.setAula(document.getAula());
        clase.setCampus(Campus.valueOf(document.getCampus().toUpperCase()));
        clase.setDetalles(document.getDetalles());
        return clase;
    }

    public static ClaseDTOI toDtoItson(Clase clase){
        if (clase == null) return null;
        ClaseDTOI dto = new ClaseDTOI();
        dto.setEstatus(clase.getEstatus());
        dto.setCosto(clase.getCosto());
        dto.setIdEstudiante(clase.getIdEstudiante());
        dto.setNombre(clase.getNombre());
        dto.setHorario(clase.getHorario());
        dto.setProfesor(clase.getProfesor());
        dto.setAula(clase.getAula());
        dto.setCampus(clase.getCampus().toString());
        dto.setDetalles(clase.getDetalles());
        return dto;
    }

    public static List<ClaseDTOI> toDtoItson(List<Clase> clases){
        List<ClaseDTOI> lista = new ArrayList<>();
        if (clases != null) {
            for (Clase c : clases) {
                lista.add(toDtoItson(c));
            }
        }
        return lista;
    }
}