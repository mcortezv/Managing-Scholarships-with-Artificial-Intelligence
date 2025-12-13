package interfaces;

import itson.pagarAdeudo.ClaseDTOI;
import itson.pagarAdeudo.PrestamoDTOI;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import itson.actividades.ActividadDTOItson;
import itson.actividades.ActividadesDTOItson;
import itson.EstudianteDTOItson;
import itson.HistorialAcademicoDTOItson;
import itson.LoginDTOItson;
import itson.actividades.BajaDTOItson;
import itson.actividades.GrupoResponseDTOItson;
import itson.actividades.GruposResponseDTOItson;
import itson.actividades.InscripcionDTOItson;
import itson.actividades.InscripcionesDTOItson;
import java.util.List;

/**
 * Interfaz de la Fachada para el sistema ITSON.
 * <p>
 * Esta interfaz define el contrato de comunicación con el sistema legado o API central de la institución.
 * Centraliza todos los servicios disponibles para los estudiantes, incluyendo:
 * 1. Autenticación y datos personales.
 * 2. Gestión de adeudos (Biblioteca y Colegiatura).
 * 3. Gestión de actividades deportivas/culturales (Inscripciones y bajas).
 *
 * @author Escalante, Sebastian.
 */
public interface IFachadaITSON {

    /**
     * Valida las credenciales de un usuario contra el directorio activo institucional.
     *
     * @param solicitudLoginDTO Objeto con el ID y contraseña proporcionados.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    boolean verificarLogin(LoginDTOItson solicitudLoginDTO);

    /**
     * Recupera la información completa del perfil de un estudiante.
     *
     * @param matricula Identificador único del estudiante.
     * @return DTO con datos personales y estatus académico del alumno.
     */
    EstudianteDTOItson verificarEstudiante(Long matricula);

    /**
     * Consulta el historial académico (kardex) del estudiante.
     *
     * @param matricula Identificador único del estudiante.
     * @return DTO con la lista de materias cursadas y promedios.
     */
    HistorialAcademicoDTOItson verificarHistorialAcademcio(Long matricula);


    //pagar adeudo

    /**
     * Obtiene la lista de préstamos de libros activos y sus posibles multas.
     *
     * @param matricula Identificador del estudiante.
     * @return Lista de DTOs externos con información de biblioteca.
     */
    List<PrestamoDTOI> solicitarListaPrestamos(Long matricula);

    /**
     * Obtiene la lista de materias que presentan adeudos de colegiatura.
     *
     * @param matricula Identificador del estudiante.
     * @return Lista de DTOs externos con información de contabilidad.
     */
    List<ClaseDTOI> solicitarListaClases(Long matricula);

    /**
     * Notifica al sistema central que se ha realizado un pago exitoso.
     *
     * @param solicitudPagoDTOI DTO con los detalles de la transacción bancaria.
     * @return true si el sistema central procesó la actualización correctamente.
     */
    boolean notificarLiquidacion(SolicitudPagoDTOI solicitudPagoDTOI);


    //actividades

    /**
     * Recupera el catálogo general de actividades disponibles (deportivas, culturales, etc.).
     *
     * @return Objeto contenedor con la lista de todas las actividades ofertadas.
     */
    ActividadesDTOItson obtenerActividades();

    /**
     * Busca una actividad específica por su nombre o identificador.
     *
     * @param actividad DTO con los criterios de búsqueda.
     * @return La información detallada de la actividad encontrada.
     */
    ActividadDTOItson obtenerActividaddPorNombre(ActividadDTOItson actividad);

    /**
     * Obtiene los grupos y horarios disponibles para una actividad específica.
     *
     * @param actividadDTO La actividad de la cual se quieren consultar los grupos.
     * @return Objeto contenedor con la lista de grupos abiertos.
     */
    GruposResponseDTOItson otenerGrupos(ActividadDTOItson actividadDTO);

    /**
     * Registra la inscripción de un estudiante en una actividad específica.
     *
     * @param inscripcionDTOItson Datos de la inscripción (alumno, grupo, actividad).
     * @return DTO con la confirmación y detalles del registro generado.
     */
    InscripcionDTOItson inscribirActividadExterno(InscripcionDTOItson inscripcionDTOItson);

    /**
     * Consulta las actividades en las que el estudiante está inscrito actualmente.
     *
     * @param estudianteDTO Datos del estudiante a consultar.
     * @return Objeto contenedor con el historial de inscripciones activas.
     */
    InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO);

    /**
     * Procesa la baja (desinscripción) de un estudiante de una actividad.
     *
     * @param baja DTO con la información necesaria para procesar la baja.
     * @return DTO de confirmación de la operación.
     */
    BajaDTOItson darBajaActividad(BajaDTOItson baja);

    /**
     * Obtiene los detalles específicos de un grupo basado en una inscripción existente.
     *
     * @param inscripcion La inscripción de referencia.
     * @return Información detallada del grupo asociado a esa inscripción.
     */
    GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcion);
}