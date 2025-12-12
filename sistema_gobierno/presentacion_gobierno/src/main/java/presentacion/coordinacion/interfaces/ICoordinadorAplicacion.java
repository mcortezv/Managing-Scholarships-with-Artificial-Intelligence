package presentacion.coordinacion.interfaces;
import dtoGobierno.BecaDTO;
import dtoGobierno.EvaluadorLoginDTO;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;

/**
 * Interface para el coordinador de aplicación
 *
 * @author Cortez, Manuel
 */
public interface ICoordinadorAplicacion {

    /**
     * Iniciar sesion.
     *
     * @param evaluadorLoginDTO the evaluador login dto
     */
// Gestión de sesión
    void iniciarSesion(EvaluadorLoginDTO evaluadorLoginDTO);

    /**
     * Cerrar sesion.
     */
    void cerrarSesion();

    /**
     * Volver hub.
     */
    void volverHub();

    /**
     * Iniciar evaluar convocatoria.
     */
// Evaluar Solicitudes - Navegación
    void iniciarEvaluarConvocatoria();

    /**
     * Seleccionar convocatoria evaluar.
     *
     * @param becaDTO the beca dto
     */
    void seleccionarConvocatoriaEvaluar(BecaDTO becaDTO);

    /**
     * Evaluar automatica.
     *
     * @param solicitud the solicitud
     */
    void evaluarAutomatica(SolicitudDTO solicitud);

    /**
     * Evaluar manual.
     *
     * @param resolucion the resolucion
     */
    void evaluarManual(ResolucionDTO resolucion);

    /**
     * Evaluar otra solicitud.
     */
    void evaluarOtraSolicitud();

    /**
     * Finalizar evaluacion.
     */
    void finalizarEvaluacion();

    /**
     * Iniciar modificar convocatoria.
     */
// Modificar Resolución - Navegación
    void iniciarModificarConvocatoria();

    /**
     * Iniciar busqueda resolucion.
     */
    void iniciarBusquedaResolucion();

    /**
     * Buscar resolucion.
     *
     * @param tipoFiltro the tipo filtro
     * @param filtro     the filtro
     */
    void buscarResolucion(String tipoFiltro, String filtro);

    /**
     * Reevaluar automatica.
     *
     * @param solicitud the solicitud
     */
    void reevaluarAutomatica(SolicitudDTO solicitud);

    /**
     * Modificar resolucion.
     *
     * @param resolucion the resolucion
     */
    void modificarResolucion(ResolucionDTO resolucion);

    /**
     * Modificar otra resolucion.
     */
    void modificarOtraResolucion();
}