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
}
