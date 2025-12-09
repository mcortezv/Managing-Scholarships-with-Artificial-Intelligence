package datosGobierno.servicesGobierno;
import datosGobierno.adaptadoresGobierno.DocumentoAdaptador;
import datosGobierno.adaptadoresGobierno.EstudianteAdaptador;
import datosGobierno.adaptadoresGobierno.SolicitudAdaptador;
import datosGobierno.daoGobierno.DocumentoDAO;
import datosGobierno.daoGobierno.EstudianteDAO;
import datosGobierno.daoGobierno.SolicitudDAO;
import datosGobierno.daoGobierno.excepcionesGobierno.SolicitudDAOException;
import datosGobierno.documents.DocumentoDocument;
import datosGobierno.documents.SolicitudDocument;
import gobierno.DocumentoDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Solicitud service.
 *
 * @author Cortez, Manuel;
 */
public class SolicitudService {
    private final SolicitudDAO solicitudDAO;
    private final EstudianteDAO estudianteDAO;
    private final DocumentoDAO documentoDAO;

    /**
     * Instantiates a new Solicitud service.
     */
    public SolicitudService(){
        this.estudianteDAO = new EstudianteDAO();
        this.documentoDAO = new DocumentoDAO();
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
        ObjectId idEstudiante = estudianteDAO.guardar(EstudianteAdaptador.toDocument(solicitud.getEstudiante()));
        List<ObjectId> documentos = new ArrayList<>();
        for(DocumentoDTOGobierno documentoDTOGobierno: solicitud.getDocumentos()){
            DocumentoDocument documentoDocument = DocumentoAdaptador.toDocument(documentoDTOGobierno);
            documentoDocument.setEstudiante(idEstudiante);
            documentos.add(documentoDAO.guardar(documentoDocument));
        }
        SolicitudDocument solicitudDocument = SolicitudAdaptador.toDocument(solicitud);
        solicitudDocument.setDocumentos(documentos);
        solicitudDocument.setEstudiante(idEstudiante);
        solicitudDAO.guardarSolicitud(solicitudDocument);
        return true;
    }
}
