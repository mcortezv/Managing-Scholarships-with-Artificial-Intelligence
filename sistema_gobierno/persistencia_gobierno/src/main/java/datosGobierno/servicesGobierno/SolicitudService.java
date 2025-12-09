package datosGobierno.servicesGobierno;
import datosGobierno.adaptadoresGobierno.SolicitudAdaptador;
import datosGobierno.daoGobierno.SolicitudDAO;
import datosGobierno.daoGobierno.excepcionesGobierno.SolicitudDAOException;
import gobierno.SolicitudDTOGobierno;

/**
 * The type Solicitud service.
 *
 * @author Cortez, Manuel;
 */
public class SolicitudService {
    private final SolicitudDAO solicitudDAO;

    /**
     * Instantiates a new Solicitud service.
     */
    public SolicitudService(){
        this.solicitudDAO = new SolicitudDAO();
    }

    /**
     * Guardar solicitud boolean.
     *
     * @param solicitud the solicitud
     * @return the boolean
     * @throws SolicitudDAOException the solicitud dao exception
     */
    public boolean guardarSolicitud(SolicitudDTOGobierno solicitud) throws SolicitudDAOException {
        return solicitudDAO.guardarSolicitud(SolicitudAdaptador.toEntity(solicitud));
    }
}
