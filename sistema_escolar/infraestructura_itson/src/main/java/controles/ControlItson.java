package controles;
import apiItson.ItsonAPI;
import apiItson.interfaces.IItsonAPI;
import itson.pagarAdeudo.ClaseDTOI;
import itson.pagarAdeudo.PrestamoDTOI;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import datos.adaptadoresItson.EstudianteAdaptador;
import datos.adaptadoresItson.HistorialAcademicoAdaptador;
import datos.adaptadoresItson.pagarAdeudo.ClaseAdaptador;
import datos.adaptadoresItson.pagarAdeudo.PrestamoAdaptador;
import datos.dominioItson.Estudiante;
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
 * The type Control itson.
 */
public class ControlItson {
    /**
     * The Api.
     */
    IItsonAPI api;

    /**
     * Instantiates a new Control itson.
     */
    public ControlItson() {
        this.api = new ItsonAPI();
    }

    /**
     * Verificar login boolean.
     *
     * @param loginDTO the login dto
     * @return the boolean
     */
    public boolean verificarLogin(LoginDTOItson loginDTO) {
        LoginDTOItson dtoInfra = new LoginDTOItson();
        dtoInfra.setUsuario(loginDTO.getUsuario());
        dtoInfra.setContrasenia(loginDTO.getContrasenia());
        return api.verificarLogin(dtoInfra);
    }

    /**
     * Verificar estudiante estudiante dto itson.
     *
     * @param matricula the matricula
     * @return the estudiante dto itson
     */
    public EstudianteDTOItson verificarEstudiante(Long matricula) {
        Estudiante estudiante = api.obtenerDatosEstudiante(matricula);
        return EstudianteAdaptador.toResponseDTO(estudiante);
    }

    /**
     * Verificar historial academico historial academico dto itson.
     *
     * @param matricula the matricula
     * @return the historial academico dto itson
     */
    public HistorialAcademicoDTOItson verificarHistorialAcademico(Long matricula) {
        return HistorialAcademicoAdaptador.toDTOItson(api.obtenerHistorialAcademico(matricula));
    }


    /**
     * Obtener lista prestamos list.
     *
     * @param matricula the matricula
     * @return the list
     */
//Caso de uso Pagar adeudo
    public List<PrestamoDTOI> obtenerListaPrestamos(Long matricula) {
        return PrestamoAdaptador.toDtoItson(api.obtenerListaPrestamosBiblioteca(matricula));
    }

    /**
     * Obtener lista clases list.
     *
     * @param matricula the matricula
     * @return the list
     */
    public List<ClaseDTOI> obtenerListaClases(Long matricula) {
        return ClaseAdaptador.toDtoItson(api.obtenerListaClaseColegiatura(matricula));
    }

    /**
     * Solicitar liquidacion boolean.
     *
     * @param solicitudPagoDTOI the solicitud pago dtoi
     * @return the boolean
     */
    public boolean solicitarLiquidacion(SolicitudPagoDTOI solicitudPagoDTOI) {
        return api.notificarLiquidacion(solicitudPagoDTOI);
    }


    /**
     * Obtener actividades actividades dto itson.
     *
     * @return the actividades dto itson
     */
// Caso de uso actividades
    public ActividadesDTOItson obtenerActividades() {
        return api.soloicitarActividades();
    }

    /**
     * Obtener grupos grupos response dto itson.
     *
     * @param actividadDTOItson the actividad dto itson
     * @return the grupos response dto itson
     */
    public GruposResponseDTOItson obtenerGrupos(ActividadDTOItson actividadDTOItson) {
        return api.solicitarGrupos(actividadDTOItson);
    }

    /**
     * Inscribir actividad inscripcion dto itson.
     *
     * @param inscripcionDTOItson the inscripcion dto itson
     * @return the inscripcion dto itson
     */
    public InscripcionDTOItson inscribirActividad(InscripcionDTOItson inscripcionDTOItson) {
        return api.inscribirActividad(inscripcionDTOItson);
    }

    /**
     * Obtener inscripciones inscripciones dto itson.
     *
     * @param estudianteDTO the estudiante dto
     * @return the inscripciones dto itson
     */
    public InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO){
        return api.obtenerInscripciones(estudianteDTO);
    }
    
    public BajaDTOItson darBajaActividad(BajaDTOItson baja){
        return api.darBajaActividad(baja);
    }
    
    public boolean actualizarEstadoInscripcion(String idInscripcion){
        return api.actualizarEstadoInscripcion(idInscripcion);
    }
    
    public GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcion){
        return api.obtenerGrupoInscrito(inscripcion);
    }
}
