package objetosNegocioGobierno.bo.interfaces;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.SolicitudDTO;
import java.util.List;

/**
 * The interface Solicitud bo.
 *
 * @author Cortez, Manuel;
 */
public interface ISolicitudBO {

    /**
     * Cambiar estado boolean.
     *
     * @param id          the id
     * @param nuevoEstado the nuevo estado
     * @return the boolean
     */
    boolean cambiarEstado(int id, EstadoSolicitud nuevoEstado);

    /**
     * Obtener listado solicitudes list.
     *
     * @param tipoBeca the tipo beca
     * @return the list
     */
    List<SolicitudDTO> obtenerListadoSolicitudes(String tipoBeca);
}
