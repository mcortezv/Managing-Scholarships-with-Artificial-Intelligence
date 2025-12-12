package presentacion.interfaces;
import objetosNegocio.bo.solicitarBeca.excepciones.SolicitudInvalidaException;
import itson.LoginDTOItson;
import solicitarBeca.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * The interface Coordinador negocio.
 *
 * @author Cortez, Manuel;
 */
public interface ICoordinadorNegocio {
    /**
     * Solicitar inicio sesion boolean.
     *
     * @param solicitudLoginDTO the solicitud login dto
     * @return the boolean
     */
    boolean solicitarInicioSesion(LoginDTOItson solicitudLoginDTO);

    /**
     * Solicitar cerrar sesion.
     */
    void solicitarCerrarSesion();

    /**
     * Gets estudiante logueado.
     *
     * @return the estudiante logueado
     */
    EstudianteDTO getEstudianteLogueado();

    /**
     * Obtener becas disponibles becas filtradas dto.
     *
     * @param requisitosDTO the requisitos dto
     * @return the becas filtradas dto
     */
    BecasFiltradasDTO obtenerBecasDisponibles(RequisitosDTO requisitosDTO);

    /**
     * Iniciar solicitud.
     *
     * @param becaDTO the beca dto
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    void iniciarSolicitud(BecaDTO becaDTO) throws SolicitudInvalidaException;

    /**
     * Procesar estudiante estudiante dto.
     *
     * @param estudianteDTO the estudiante dto
     * @return the estudiante dto
     */
    EstudianteDTO procesarEstudiante(EstudianteDTO estudianteDTO);

    /**
     * Procesar historial academico.
     *
     * @param historialAcademicDTO the historial academic dto
     */
    void procesarHistorialAcademico(HistorialAcademicoDTO historialAcademicDTO);

    /**
     * Procesar tutor.
     *
     * @param tutorDTO the tutor dto
     */
    void procesarTutor(TutorDTO tutorDTO);

    /**
     * Procesar informacion socioeconomica.
     *
     * @param informacionSocioeconomicaDTO the informacion socioeconomica dto
     */
    void procesarInformacionSocioeconomica(InformacionSocioeconomicaDTO informacionSocioeconomicaDTO);

    /**
     * Procesar documentos.
     *
     * @param documentosCargados the documentos cargados
     * @throws IOException the io exception
     */
    void procesarDocumentos(Map<String, File> documentosCargados) throws IOException;

    /**
     * Gets solicitud actual.
     *
     * @return the solicitud actual
     */
    SolicitudDTO getSolicitudActual();

    /**
     * Enviar solicitud a gobierno boolean.
     *
     * @return the boolean
     */
    boolean enviarSolicitudAGobierno();
}
