package interfaces;
import gobierno.BecasDisponiblesDTOGobierno;
import gobierno.EstudianteDTOGobierno;
import gobierno.RequisitosDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import gobierno.apelacion.ApelacionDTOGobierno;

import java.util.List;

/**
 * The interface Fachada gobierno.
 *
 * @author Cortez, Manuel;
 */
public interface IFachadaGobierno {

    /**
     * Obtener becas becas disponibles dto gobierno.
     *
     * @param requisitosDTO the requisitos dto
     * @return the becas disponibles dto gobierno
     */
    BecasDisponiblesDTOGobierno obtenerBecas(RequisitosDTOGobierno requisitosDTO);

    /**
     * Enviar solicitud boolean.
     *
     * @param solicitudDTO the solicitud dto
     * @return the boolean
     */
    boolean enviarSolicitud(SolicitudDTOGobierno solicitudDTO);

    List<SolicitudDTOGobierno> obtenerSolicitudesPorEstudiante(EstudianteDTOGobierno estudianteDTO);

    boolean registrarApelacion(ApelacionDTOGobierno apelacionDTO);
}
