package interfaces.solicitarBeca;
import gobierno.SolicitudDTOGobierno;
import bo.solicitarBeca.excepciones.SolicitudInvalidaException;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.dominio.*;
import solicitarBeca.repository.documents.SolicitudDocument;

import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public interface ISolicitudBO {

    Solicitud crearSolicitud(Beca beca) throws SolicitudInvalidaException;

    void asignarEstudiante(Solicitud solicitud, Estudiante estudiante) throws SolicitudInvalidaException;

    void asignarBeca(Solicitud solicitud, Beca beca) throws SolicitudInvalidaException;

    void asignarDocumentos(Solicitud solicitud, List<Documento> documentos) throws SolicitudInvalidaException;

    void asignarHistorial(Solicitud solicitud, HistorialAcademico historialAcademico) throws SolicitudInvalidaException;

    void asignarSocioeconomico(Solicitud solicitud, InformacionSocioeconomica info) throws SolicitudInvalidaException;

    void validarSolicitudCompleta(Solicitud solicitud) throws SolicitudInvalidaException;

    void guardarSolicitud(SolicitudDocument solicitud) throws SolicitudInvalidaException;

    void enviarSolicitud(SolicitudDTOGobierno solicitud) throws SolicitudInvalidaException;

    //apelar resultado
    List<Solicitud> obtenerSolicitudesPorEstudiante(EstudianteDTO estudianteDTO);
}
