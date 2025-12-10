package objetosNegocio.bo.solicitarBeca;
import datosGobierno.documents.DocumentoDocument;
import solicitarBeca.DocumentoDTO;
import solicitarBeca.repository.dao.interfaces.IDocumentoDAO;
import objetosNegocio.bo.solicitarBeca.excepciones.DocumentoInvalidoException;
import objetosNegocio.bo.solicitarBeca.intefaces.IDocumentoBO;

/**
 * The type Documento bo.
 *
 * @author Cortez, Manuel;
 */
public class DocumentoBO implements IDocumentoBO {
    private final IDocumentoDAO documentoDAO;

    /**
     * Instantiates a new Documento bo.
     *
     * @param documentoDAO the documento dao
     */
    public DocumentoBO(IDocumentoDAO documentoDAO) {
        this.documentoDAO = documentoDAO;
    }

    @Override
    public void guardarDocumento(DocumentoDTO documento) throws DocumentoInvalidoException {
        try {
            //documentoDAO.guardar(documento);
        } catch (Exception ex) {
            throw new DocumentoInvalidoException(ex.getMessage());
        }
    }
}
