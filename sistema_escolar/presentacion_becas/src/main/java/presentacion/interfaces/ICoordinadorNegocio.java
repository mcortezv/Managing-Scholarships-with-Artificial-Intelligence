package presentacion.interfaces;


import bo.solicitarBeca.excepciones.SolicitudInvalidaException;
import itson.LoginDTOItson;
import solicitarBeca.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Cortez, Manuel;
 */
public interface ICoordinadorNegocio {
    boolean solicitarInicioSesion(LoginDTOItson solicitudLoginDTO);
    void solicitarCerrarSesion();
    EstudianteDTO getEstudianteLogueado();
    BecasFiltradasDTO obtenerBecasDisponibles(RequisitosDTO requisitosDTO);
    void iniciarSolicitud(BecaDTO becaDTO) throws SolicitudInvalidaException;
    EstudianteDTO procesarEstudiante(EstudianteDTO estudianteDTO);
    void procesarHistorialAcademico(HistorialAcademicoDTO historialAcademicDTO);
    void procesarTutor(TutorDTO tutorDTO);
    void procesarInformacionSocioeconomica(InformacionSocioeconomicaDTO informacionSocioeconomicaDTO);
    void procesarDocumentos(Map<String, File> documentosCargados) throws IOException;
    SolicitudDTO getSolicitudActual();
    boolean enviarSolicitudAGobierno();
}