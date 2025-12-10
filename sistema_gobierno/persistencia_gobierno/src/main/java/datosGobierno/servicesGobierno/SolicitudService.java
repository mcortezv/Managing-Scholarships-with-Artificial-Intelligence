package datosGobierno.servicesGobierno;
import datosGobierno.adaptadoresGobierno.DocumentoAdaptador;
import datosGobierno.adaptadoresGobierno.EstudianteAdaptador;
import datosGobierno.adaptadoresGobierno.SolicitudAdaptador;
import datosGobierno.daoGobierno.DocumentoDAO;
import datosGobierno.daoGobierno.EstudianteDAO;
import datosGobierno.daoGobierno.SolicitudDAO;
import datosGobierno.daoGobierno.excepcionesGobierno.SolicitudDAOException;
import datosGobierno.daoGobierno.interfacesGobierno.IDocumentoDAO;
import datosGobierno.daoGobierno.interfacesGobierno.IEstudianteDAO;
import datosGobierno.daoGobierno.interfacesGobierno.ISolicitudDAO;
import datosGobierno.documents.DocumentoDocument;
import datosGobierno.documents.SolicitudDocument;
import datosGobierno.dominioGobierno.Solicitud;
import gobierno.DocumentoDTOGobierno;
import gobierno.EstudianteDTOGobierno;
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
    private final ISolicitudDAO solicitudDAO;
    private final IEstudianteDAO estudianteDAO;
    private final IDocumentoDAO documentoDAO;

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
        solicitudDAO.guardar(solicitudDocument);
        return true;
    }

    public List<SolicitudDTOGobierno> obtenerListaSolicudesPorEstudiante(EstudianteDTOGobierno estudianteDTOGobierno){
        if (estudianteDTOGobierno == null || estudianteDTOGobierno.getMatricula() == null) {
            return new ArrayList<>();
        }
        try {
            ObjectId idMongo = new ObjectId(String.valueOf(estudianteDTOGobierno.getMatricula()));
            List<Solicitud> listaEntidades = solicitudDAO.obtenerPorIdEstudiante(idMongo);
            return SolicitudAdaptador.toDTOGobierno(listaEntidades);
        } catch (IllegalArgumentException e) {
            System.out.println("El ID del estudiante no es un ObjectId válido: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
