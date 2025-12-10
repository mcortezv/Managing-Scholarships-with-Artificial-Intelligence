package objetosNegocio.bo.solicitarBeca;
import objetosNegocio.adaptadores.solicitarBeca.BecaAdaptador;
import objetosNegocio.adaptadores.solicitarBeca.SolicitudAdaptador;
import objetosNegocio.bo.solicitarBeca.excepciones.SolicitudInvalidaException;
import org.bson.types.ObjectId;
import solicitarBeca.BecaDTO;
import solicitarBeca.SolicitudDTO;
import solicitarBeca.repository.dao.interfaces.IDocumentoDAO;
import solicitarBeca.repository.dao.interfaces.IEstudianteDAO;
import solicitarBeca.repository.dao.interfaces.ISolicitudDAO;
import solicitarBeca.dominio.*;
import solicitarBeca.dominio.enums.EstadoSolicitud;
import interfaces.IFachadaGobierno;
import objetosNegocio.bo.solicitarBeca.intefaces.ISolicitudBO;
import solicitarBeca.repository.documents.DocumentoDocument;
import solicitarBeca.repository.documents.EstudianteDocument;
import solicitarBeca.repository.documents.SolicitudDocument;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The type Solicitud bo.
 *
 * @author Cortez, Manuel;
 */
public class SolicitudBO implements ISolicitudBO {
    private final IFachadaGobierno fachadaGobierno;
    private final ISolicitudDAO solicitudDAO;
    private final IEstudianteDAO estudianteDAO;
    private final IDocumentoDAO documentoDAO;

    /**
     * Instantiates a new Solicitud bo.
     *
     * @param fachadaGobierno the fachada gobierno
     * @param solicitudDAO    the solicitud dao
     */
    public SolicitudBO(IFachadaGobierno fachadaGobierno, ISolicitudDAO solicitudDAO, IEstudianteDAO estudianteDAO, IDocumentoDAO documentoDAO) {
        this.fachadaGobierno = fachadaGobierno;
        this.solicitudDAO = solicitudDAO;
        this.estudianteDAO = estudianteDAO;
        this.documentoDAO = documentoDAO;
    }

    @Override
    public SolicitudDTO crearSolicitud(BecaDTO beca) {
        Solicitud solicitud = new Solicitud();
        solicitud.setId(ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE));
        solicitud.setBeca(BecaAdaptador.toEntity(beca));
        solicitud.setFecha(LocalDate.now());
        solicitud.setEstado(EstadoSolicitud.ACTIVA);
        return SolicitudAdaptador.toDTO(solicitud);
    }

    @Override
    public void validarSolicitudCompleta(SolicitudDTO solicitud) throws SolicitudInvalidaException {
        if (solicitud.getEstudiante() == null || solicitud.getBeca() == null || solicitud.getDocumentos() == null ||
                solicitud.getHistorialAcademico() == null || solicitud.getInformacionSocioeconomica() == null) {
            throw new SolicitudInvalidaException("Solicitud incompleta.");
        }
        solicitud.setEstado(EstadoSolicitud.ACTIVA.toString());
    }

    @Override
    public void guardarSolicitud(SolicitudDTO solicitud) throws SolicitudInvalidaException {
        try {
            Solicitud entity = SolicitudAdaptador.toEntity(solicitud);
            Estudiante estudiante = entity.getEstudiante();
            EstudianteDocument estudianteDocument = new EstudianteDocument();
            estudianteDocument.setCarrera(estudiante.getCarrera());
            estudianteDocument.setCorreo(estudiante.getCorreo());
            estudianteDocument.setNombre(estudiante.getNombre());
            estudianteDocument.setTelefono(estudiante.getTelefono());
            estudianteDocument.setDireccion(estudiante.getDireccion());
            estudianteDocument.setMatricula(estudiante.getMatricula());
            if (estudiante.getTutor() != null) {
                estudianteDocument.setTutor(estudiante.getTutor());
            }
            ObjectId idEstudiante = estudianteDAO.guardar(estudianteDocument);

            List<Documento> documentos = entity.getDocumentos();
            List<ObjectId> documentosId = new ArrayList<>();
            if (documentos == null) {
                for (Documento documento : entity.getDocumentos()) {
                    DocumentoDocument documentoDocument = new DocumentoDocument();
                    documentoDocument.setEstudiante(idEstudiante);
                    documentoDocument.setContenido(documento.getContenido());
                    documentoDocument.setTipo(documento.getTipo());
                    documentoDocument.setIdentificador(documento.getIdentificador());
                    documentosId.add(documentoDAO.guardar(documentoDocument));
                }
            }

            SolicitudDocument solicitudDocument = new SolicitudDocument();
            solicitudDocument.setEstudiante(idEstudiante);
            solicitudDocument.setDocumentos(documentosId);
            solicitudDocument.setBeca(entity.getBeca());
            solicitudDocument.setFecha(entity.getFecha());
            solicitudDocument.setEstado(entity.getEstado());
            solicitudDocument.setId(entity.getId());
            solicitudDocument.setHistorialAcademico(entity.getHistorialAcademico());
            solicitudDocument.setInformacionSocioeconomica(entity.getInformacionSocioeconomica());
            solicitudDAO.create(solicitudDocument);

        } catch (Exception ex) {
            throw new SolicitudInvalidaException(ex.getMessage());
        }
    }

    @Override
    public void enviarSolicitud(SolicitudDTO solicitud) throws SolicitudInvalidaException {
        try {
            Solicitud entity = SolicitudAdaptador.toEntity(solicitud);
            fachadaGobierno.enviarSolicitud(SolicitudAdaptador.toDTOGobierno(entity));
        } catch (Exception ex) {
            throw new SolicitudInvalidaException(ex.getMessage());
        }
    }
}
