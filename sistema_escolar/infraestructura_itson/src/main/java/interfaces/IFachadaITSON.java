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
 * The interface Fachada itson.
 *
 * @author Escalante, Sebastian.
 */
public interface IFachadaITSON {

    /**
     * Verificar login boolean.
     *
     * @param solicitudLoginDTO the solicitud login dto
     * @return the boolean
     */
    boolean verificarLogin(LoginDTOItson solicitudLoginDTO);

    /**
     * Verificar estudiante estudiante dto itson.
     *
     * @param matricula the matricula
     * @return the estudiante dto itson
     */
    EstudianteDTOItson verificarEstudiante(Long matricula);

    /**
     * Verificar historial academcio historial academico dto itson.
     *
     * @param matricula the matricula
     * @return the historial academico dto itson
     */
    HistorialAcademicoDTOItson verificarHistorialAcademcio(Long matricula);


    //pagar adeudo
    List<PrestamoDTOI> solicitarListaPrestamos(Long matricula);
    List<ClaseDTOI> solicitarListaClases(Long matricula);
    boolean notificarLiquidacion(SolicitudPagoDTOI solicitudPagoDTOI);


    //actividades
    ActividadesDTOItson obtenerActividades();
    ActividadDTOItson obtenerActividaddPorNombre(ActividadDTOItson actividad);
    GruposResponseDTOItson otenerGrupos(ActividadDTOItson actividadDTO);
    InscripcionDTOItson inscribirActividadExterno(InscripcionDTOItson inscripcionDTOItson);
    InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO);
    BajaDTOItson darBajaActividad(BajaDTOItson baja);
    
  //  boolean actualizarEstadoInscripcion(String idInscripcion);
   GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcion);
}