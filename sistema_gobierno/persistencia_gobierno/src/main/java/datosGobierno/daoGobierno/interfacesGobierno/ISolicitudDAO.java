package datosGobierno.daoGobierno.interfacesGobierno;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.SolicitudDTO;
import gobierno.SolicitudDTOGobierno;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public interface ISolicitudDAO {

    boolean guardarSolicitud(SolicitudDTOGobierno solicitud);

    List<SolicitudDTO> obtenerListadoSolicitudes(String tipoBeca);

    Solicitud obtenerPorId(int idSolicitud);


    boolean actualizar(Solicitud solicitud);

    boolean cambiarEstado(int id, EstadoSolicitud estado);
}
