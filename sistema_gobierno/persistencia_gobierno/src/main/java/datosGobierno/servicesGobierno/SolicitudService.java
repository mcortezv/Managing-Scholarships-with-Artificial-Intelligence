package datosGobierno.servicesGobierno;
import datosGobierno.daoGobierno.SolicitudDAO;
import datosGobierno.daoGobierno.excepcionesGobierno.SolicitudDAOException;
import gobierno.SolicitudDTOGobierno;

/**
 *
 * @author Cortez, Manuel;
 */
public class SolicitudService {
    private final SolicitudDAO solicitudDAO;

    public SolicitudService(){
        this.solicitudDAO = new SolicitudDAO();
    }

    public boolean guardarSolicitud(SolicitudDTOGobierno solicitud) throws SolicitudDAOException {
        return solicitudDAO.guardarSolicitud(solicitud);
    }
}
