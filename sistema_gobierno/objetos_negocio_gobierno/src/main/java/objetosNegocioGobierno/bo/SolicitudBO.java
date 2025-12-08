package objetosNegocioGobierno.bo;
import datosGobierno.daoGobierno.documents.SolicitudDocument;
import datosGobierno.daoGobierno.interfacesGobierno.ISolicitudDAO;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.SolicitudDTO;
import objetosNegocioGobierno.adaptadores.SolicitudAdaptador;
import objetosNegocioGobierno.bo.excepciones.SolicitudBOException;
import objetosNegocioGobierno.bo.interfaces.ISolicitudBO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public class SolicitudBO implements ISolicitudBO {
    private final ISolicitudDAO solicitudDAO;

    public SolicitudBO(ISolicitudDAO solicitudDAO) {
        this.solicitudDAO = solicitudDAO;
    }

    @Override
    public boolean cambiarEstado(int id, EstadoSolicitud nuevoEstado){
        try {
            return solicitudDAO.cambiarEstado(id, nuevoEstado);
        } catch (Exception ex){
            throw new SolicitudBOException(ex.getMessage());
        }
    }

    @Override
    public Solicitud obtenerSolicitud(int id){
        try {
            return solicitudDAO.obtenerPorId(id);
        } catch (Exception ex){
            throw new SolicitudBOException(ex.getMessage());
        }
    }

    @Override
    public List<SolicitudDTO> obtenerListadoSolicitudes(String tipoBeca){
        try {
            return solicitudDAO.obtenerListadoSolicitudes(tipoBeca);
        } catch (Exception ex){
            throw new SolicitudBOException(ex.getMessage());
        }
    }
}
