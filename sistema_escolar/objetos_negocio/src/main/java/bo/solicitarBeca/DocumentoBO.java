package bo.solicitarBeca;
import solicitarBeca.dominio.enums.TipoDocumento;
import solicitarBeca.repository.dao.interfaces.IDocumentoDAO;
import solicitarBeca.dominio.Documento;
import bo.solicitarBeca.excepciones.DocumentoInvalidoException;
import interfaces.solicitarBeca.IDocumentoBO;
import solicitarBeca.repository.documents.DocumentoDocument;

/**
 *
 * @author Cortez, Manuel;
 */
public class DocumentoBO implements IDocumentoBO {
    private final IDocumentoDAO documentoDAO;

    public DocumentoBO(IDocumentoDAO documentoDAO) {
        this.documentoDAO = documentoDAO;
    }

    @Override
    public Documento crearDocumento(Long identificador, TipoDocumento tipo, byte[] contenido, Long estudiante) throws DocumentoInvalidoException {
        if (identificador == null || tipo == null || estudiante == null || contenido == null) {
            throw new DocumentoInvalidoException("Documento invalido");
        }
        return new Documento(contenido, estudiante, identificador, tipo);
    }

    @Override
    public void guardarDocumento(DocumentoDocument documento) throws DocumentoInvalidoException {
        try {
            documentoDAO.guardar(documento);
        } catch (Exception ex) {
            throw new DocumentoInvalidoException(ex.getMessage());
        }
    }
}