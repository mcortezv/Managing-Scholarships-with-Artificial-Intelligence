package datosGobierno.daoGobierno.interfacesGobierno;
import datosGobierno.dominioGobierno.Resolucion;
import java.util.List;

/**
 * The interface Resolucion dao.
 *
 * @author Cortez, Manuel;
 */
public interface IResolucionDAO {

    /**
     * Guardar boolean.
     *
     * @param resolucion the resolucion
     * @return the boolean
     */
    boolean guardar(Resolucion resolucion);

    /**
     * Actualizar boolean.
     *
     * @param resolucion the resolucion
     * @return the boolean
     */
    boolean actualizar(Resolucion resolucion);

    /**
     * Obtener por id resolucion.
     *
     * @param id the id
     * @return the resolucion
     */
    Resolucion obtenerPorId(int id);

    /**
     * Obtener por filtro resolucion.
     *
     * @param tipoFiltro the tipo filtro
     * @param filtro     the filtro
     * @return the resolucion
     */
    Resolucion obtenerPorFiltro(String tipoFiltro, String filtro);

    /**
     * Obtener todas list.
     *
     * @return the list
     */
    List<Resolucion> obtenerTodas();

    /**
     * Obtener por tipo beca list.
     *
     * @param tipoBeca the tipo beca
     * @return the list
     */
    List<Resolucion> obtenerPorTipoBeca(String tipoBeca);

    /**
     * Obtener por decision list.
     *
     * @param decision the decision
     * @return the list
     */
    List<Resolucion> obtenerPorDecision(String decision);

    /**
     * Eliminar boolean.
     *
     * @param idSolicitud the id solicitud
     * @return the boolean
     */
    boolean eliminar(long idSolicitud);
}
