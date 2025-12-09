package solicitarBeca.repository.dao;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import solicitarBeca.config.MongoClientProvider;
import solicitarBeca.excepciones.DocumentoDAOException;
import solicitarBeca.repository.dao.interfaces.IDocumentoDAO;
import solicitarBeca.repository.documents.DocumentoDocument;
import java.time.Instant;

/**
 *
 * @author Cortez, Manuel;
 */
public class DocumentoDAO implements IDocumentoDAO {
    private final MongoCollection<DocumentoDocument> col;
    private final MongoCollection<Document> colDoc;

    public DocumentoDAO() {
        this.col = MongoClientProvider.INSTANCE.getCollection("documentos", DocumentoDocument.class);
        this.colDoc = MongoClientProvider.INSTANCE.getCollection("documentos", Document.class);
    }

    @Override
    public ObjectId guardar(DocumentoDocument entity) throws DocumentoDAOException {
        try {
            if (entity.get_id() == null) {
                entity.set_id(new ObjectId());
            }
            entity.setCreadoEn(Instant.now());
            col.insertOne(entity);
            return entity.get_id();
        } catch (MongoException ex) {
            throw new DocumentoDAOException("Error al insertar Documento");
        }
    }
}
