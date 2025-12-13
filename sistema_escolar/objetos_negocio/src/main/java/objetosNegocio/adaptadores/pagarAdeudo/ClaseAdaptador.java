package objetosNegocio.adaptadores.pagarAdeudo;

import datos.dominioItson.pagarAdeudo.Clase;
import datos.dominioItson.pagarAdeudo.enums.Campus;
import dto.pagarAdeudo.ClaseDTO;
import itson.pagarAdeudo.ClaseDTOI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador de Datos para la entidad Clase (Materia).
 * <p>
 * Esta clase es una utilidad de mapeo que convierte objetos provenientes de la interfaz externa (DTOI)
 * a objetos de transferencia de datos internos (DTO) que utiliza la aplicación.
 * Permite transformar listas completas de materias disponibles o con adeudo.
 */
public class ClaseAdaptador {

    /**
     * Convierte un objeto de clase externo (DTOI) a un objeto interno (DTO).
     * Realiza la asignación de atributos como costo, profesor, aula y campus.
     *
     * @param dtoI El objeto Clase proveniente del sistema externo/librería.
     * @return El objeto ClaseDTO formateado para uso interno, o null si la entrada es nula.
     */
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

    /**
     * Convierte una lista completa de objetos externos a una lista de objetos internos.
     * Utiliza Streams de Java para procesar la colección de manera eficiente.
     *
     * @param dtosI Lista de objetos ClaseDTOI externos.
     * @return Una lista de ClaseDTO internos. Devuelve una lista vacía si la entrada es nula.
     */
    public static List<ClaseDTO> toDTO(List<ClaseDTOI> dtosI) {
        if (dtosI == null) {
            return new ArrayList<>();
        }
        return dtosI.stream()
                .map(ClaseAdaptador::toDTO)
                .collect(Collectors.toList());
    }

}