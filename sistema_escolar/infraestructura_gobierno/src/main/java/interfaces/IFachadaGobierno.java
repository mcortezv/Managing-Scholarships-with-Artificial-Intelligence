package interfaces;
import dto.apelacionResultado.ApelacionDTO;
import gobierno.BecasDisponiblesDTOGobierno;
import gobierno.RequisitosDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import solicitarBeca.EstudianteDTO;

import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public interface IFachadaGobierno {

    BecasDisponiblesDTOGobierno obtenerBecas(RequisitosDTOGobierno requisitosDTO);

    boolean enviarSolicitud(SolicitudDTOGobierno solicitudDTO);

    List<SolicitudDTOGobierno> obtenerSolicitudesPorEstudiante(EstudianteDTO estudianteDTO);

    boolean registrarApelacion(ApelacionDTO apelacionDTO);
}
