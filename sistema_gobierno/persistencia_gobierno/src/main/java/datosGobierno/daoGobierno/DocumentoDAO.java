package datosGobierno.daoGobierno;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import datosGobierno.configMongoGobierno.MongoClienteProvider;
import datosGobierno.daoGobierno.excepcionesGobierno.DocumentoDAOException;
import datosGobierno.daoGobierno.interfacesGobierno.IDocumentoDAO;
import datosGobierno.documents.DocumentoDocument;
import org.bson.types.ObjectId;
import java.time.Instant;

/**
 * The type Documento dao.
 *
 * @author Cortez, Manuel;
 */
public class DocumentoDAO implements IDocumentoDAO {
    private final MongoCollection<DocumentoDocument> col;

    /**
     * Instantiates a new Documento dao.
     */
    public DocumentoDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("documentos", DocumentoDocument.class);
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
