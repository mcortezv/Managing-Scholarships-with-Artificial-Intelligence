package apiGobierno.interfaces;
import dtoGobierno.EstudianteDTO;
import dtoGobierno.SolicitudDTO;
import gobierno.BecasDisponiblesDTOGobierno;
import gobierno.EstudianteDTOGobierno;
import gobierno.RequisitosDTOGobierno;
import gobierno.SolicitudDTOGobierno;

import java.util.List;

/**
 * The interface Gobierno api.
 *
 * @author Cortez, Manuel;
 */
public interface IGobiernoAPI {

    /**
     * Guardar solicitud boolean.
     *
     * @param solicitudDTO the solicitud dto
     * @return the boolean
     */
    boolean guardarSolicitud(SolicitudDTOGobierno solicitudDTO);

    /**
     * Obtener becas becas disponibles dto gobierno.
     *
     * @param requisitosDTO the requisitos dto
     * @return the becas disponibles dto gobierno
     */
    BecasDisponiblesDTOGobierno obtenerBecas(RequisitosDTOGobierno requisitosDTO);


    //apelar resultado
    List<SolicitudDTOGobierno> obtenerListaSolicitudesPorEstudiante(EstudianteDTOGobierno estudianteDTO);
}
