package interfaces;
import dtoGobierno.BecaDTO;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import java.util.List;

/**
 * The interface Fachada evaluar solicitudes.
 *
 * @author Cortez, Manuel;
 */
public interface IFachadaEvaluarSolicitudes {

    /**
     * Obtener listado becas list.
     *
     * @return the list
     */
    List<BecaDTO> obtenerListadoBecas();

    /**
     * Evaluacion automatica resolucion dto.
     *
     * @param solicitud the solicitud
     * @return the resolucion dto
     */
    ResolucionDTO evaluacionAutomatica(SolicitudDTO solicitud);

    /**
     * Resolver solicitud manual boolean.
     *
     * @param resolucionDTO the resolucion dto
     * @return the boolean
     */
    boolean resolverSolicitudManual(ResolucionDTO resolucionDTO);

    /**
     * Resolver boolean.
     *
     * @param resolucionDTO the resolucion dto
     * @return the boolean
     */
    boolean resolver(ResolucionDTO resolucionDTO);

    /**
     * Obtener solicitudes list.
     *
     * @param tipoBeca the tipo beca
     * @return the list
     */
    List<SolicitudDTO> obtenerSolicitudes(String tipoBeca);
}
