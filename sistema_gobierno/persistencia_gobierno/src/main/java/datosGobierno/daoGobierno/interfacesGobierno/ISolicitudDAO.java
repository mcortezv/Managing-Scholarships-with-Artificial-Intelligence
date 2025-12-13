package datosGobierno.daoGobierno.interfacesGobierno;
import datosGobierno.documents.SolicitudDocument;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * The interface Solicitud dao.
 */
public interface ISolicitudDAO {

    /**
     * Guardar solicitud boolean.
     *
     * @param solicitud the solicitud
     * @return the boolean
     */
    boolean guardar(SolicitudDocument solicitud);

    /**
     * Obtener listado solicitudes list.
     *
     * @param tipoBeca the tipo beca
     * @return the list
     */
    List<Solicitud> obtenerListadoSolicitudes(String tipoBeca);

    /**
     * Obtener por id solicitud.
     *
     * @param idSolicitud the id solicitud
     * @return the solicitud
     */
    Solicitud obtenerPorId(int idSolicitud);

    /**
     * Cambiar estado boolean.
     *
     * @param id     the id
     * @param estado the estado
     * @return the boolean
     */
    boolean cambiarEstado(int id, EstadoSolicitud estado);


    //apelar resultado
    List<Solicitud> obtenerPorIdEstudiante(ObjectId idEstudiante);
}
