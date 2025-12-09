package interfaces;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;

/**
 * The interface Fachada modificar resolucion.
 *
 * @author Cortez, Manuel;
 */
public interface IFachadaModificarResolucion {

    /**
     * Buscar resolucion resolucion dto.
     *
     * @param nombre the nombre
     * @param filtro the filtro
     * @return the resolucion dto
     */
    ResolucionDTO buscarResolucion(String nombre, String filtro);

    /**
     * Resolver automatico resolucion dto.
     *
     * @param solicitud the solicitud
     * @return the resolucion dto
     */
    ResolucionDTO resolverAutomatico(SolicitudDTO solicitud);

    /**
     * Resolver manual boolean.
     *
     * @param resolucionDTO the resolucion dto
     * @return the boolean
     */
    boolean resolverManual(ResolucionDTO resolucionDTO);

    /**
     * Modificar resolucion boolean.
     *
     * @param resolucionDTO the resolucion dto
     * @return the boolean
     */
    boolean modificarResolucion(ResolucionDTO resolucionDTO);
}
