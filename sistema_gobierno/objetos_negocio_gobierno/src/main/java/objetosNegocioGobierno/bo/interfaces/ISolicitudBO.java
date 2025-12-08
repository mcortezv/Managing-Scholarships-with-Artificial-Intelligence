package objetosNegocioGobierno.bo.interfaces;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.SolicitudDTO;

import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public interface ISolicitudBO {

    boolean cambiarEstado(int id, EstadoSolicitud nuevoEstado);

    Solicitud obtenerSolicitud(int id);

    List<SolicitudDTO> obtenerListadoSolicitudes(String tipoBeca);

}
