package presentacion.coordinacion.interfaces;
import dtoGobierno.BecaDTO;
import dtoGobierno.EvaluadorLoginDTO;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import java.util.List;

/**
 * Interface para el coordinador de negocio
 *
 * @author Cortez, Manuel
 */
public interface ICoordinadorNegocio {

    /**
     * Iniciar sesion boolean.
     *
     * @param evaluadorLoginDTO the evaluador login dto
     * @return the boolean
     */
// Gestión de sesión
    boolean iniciarSesion(EvaluadorLoginDTO evaluadorLoginDTO);

    /**
     * Cerrar sesion.
     */
    void cerrarSesion();

    /**
     * Hay sesion iniciada boolean.
     *
     * @return the boolean
     */
    boolean haySesionIniciada();

    /**
     * Obtener becas con solicitudes list.
     *
     * @return the list
     */
// Evaluar Solicitudes
    List<BecaDTO> obtenerBecasConSolicitudes();

    /**
     * Obtener solicitudes list.
     *
     * @param tipoBeca the tipo beca
     * @return the list
     */
    List<SolicitudDTO> obtenerSolicitudes(String tipoBeca);

    /**
     * Evaluar solicitud automatica resolucion dto.
     *
     * @param solicitud the solicitud
     * @return the resolucion dto
     */
    ResolucionDTO evaluarSolicitudAutomatica(SolicitudDTO solicitud);

    /**
     * Guardar resolucion boolean.
     *
     * @param resolucion the resolucion
     * @return the boolean
     */
    boolean guardarResolucion(ResolucionDTO resolucion);

    /**
     * Buscar resolucion resolucion dto.
     *
     * @param tipoFiltro the tipo filtro
     * @param filtro     the filtro
     * @return the resolucion dto
     */
// Modificar Resolución
    ResolucionDTO buscarResolucion(String tipoFiltro, String filtro);

    /**
     * Reevaluar automatica resolucion dto.
     *
     * @param solicitud the solicitud
     * @return the resolucion dto
     */
    ResolucionDTO reevaluarAutomatica(SolicitudDTO solicitud);

    /**
     * Modificar resolucion boolean.
     *
     * @param resolucion the resolucion
     * @return the boolean
     */
    boolean modificarResolucion(ResolucionDTO resolucion);
}