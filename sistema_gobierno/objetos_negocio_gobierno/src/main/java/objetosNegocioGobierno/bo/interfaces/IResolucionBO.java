package objetosNegocioGobierno.bo.interfaces;
import datosGobierno.dominioGobierno.Resolucion;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import gobierno.ResolucionDTOGobierno;
import gobierno.SolicitudDTOGobierno;
/**
 * The interface Resolucion bo.
 *
 * @author Cortez, Manuel;
 */
public interface IResolucionBO {

    /**
     * Resolver boolean.
     *
     * @param resolucion the resolucion
     * @return the boolean
     */
    boolean resolver(ResolucionDTO resolucion);

    /**
     * Crear resolucion automatica resolucion dto gobierno.
     *
     * @param solicitud the solicitud
     * @return the resolucion dto gobierno
     */
    ResolucionDTOGobierno crearResolucionAutomatica(SolicitudDTO solicitud);

    /**
     * Obtener resolucion por filtro resolucion.
     *
     * @param tipoFiltro the tipo filtro
     * @param filtro     the filtro
     * @return the resolucion
     */
    ResolucionDTO obtenerResolucionPorFiltro(String tipoFiltro, String filtro);

    /**
     * Actualizar resolucion boolean.
     *
     * @param resolucion the resolucion
     * @return the boolean
     */
    boolean actualizarResolucion(ResolucionDTO resolucion);
}
