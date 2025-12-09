package solicitarBeca.repository.dao.interfaces;
import org.bson.types.ObjectId;
import solicitarBeca.excepciones.DocumentoDAOException;
import solicitarBeca.repository.documents.DocumentoDocument;

/**
 *
 * @author Cortez, Manuel;
 */
public interface IDocumentoDAO {

    ObjectId guardar(DocumentoDocument entity) throws DocumentoDAOException;
}
