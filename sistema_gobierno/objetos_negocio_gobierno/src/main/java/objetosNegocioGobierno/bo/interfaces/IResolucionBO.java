package objetosNegocioGobierno.bo.interfaces;
import datosGobierno.dominioGobierno.Resolucion;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.Decision;
import gobierno.ResolucionDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import java.time.LocalDate;

/**
 * The interface Resolucion bo.
 *
 * @author Cortez, Manuel;
 */
public interface IResolucionBO {

    /**
     * Crear resolucion resolucion.
     *
     * @param solicitud       the solicitud
     * @param decision        the decision
     * @param motivo          the motivo
     * @param fechaEvaluacion the fecha evaluacion
     * @return the resolucion
     */
    Resolucion crearResolucion(Solicitud solicitud, Decision decision, String motivo, LocalDate fechaEvaluacion);

    /**
     * Resolver boolean.
     *
     * @param resolucion the resolucion
     * @return the boolean
     */
    boolean resolver(Resolucion resolucion);

    /**
     * Crear resolucion automatica resolucion dto gobierno.
     *
     * @param solicitud the solicitud
     * @return the resolucion dto gobierno
     */
    ResolucionDTOGobierno crearResolucionAutomatica(SolicitudDTOGobierno solicitud);

    /**
     * Obtener resolucion resolucion.
     *
     * @param id the id
     * @return the resolucion
     */
    Resolucion obtenerResolucion(int id);

    /**
     * Obtener resolucion por filtro resolucion.
     *
     * @param tipoFiltro the tipo filtro
     * @param filtro     the filtro
     * @return the resolucion
     */
    Resolucion obtenerResolucionPorFiltro(String tipoFiltro, String filtro);

    /**
     * Actualizar resolucion boolean.
     *
     * @param resolucion the resolucion
     * @return the boolean
     */
    boolean actualizarResolucion(Resolucion resolucion);
}
