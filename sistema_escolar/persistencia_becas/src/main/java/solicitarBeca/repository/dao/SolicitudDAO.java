package solicitarBeca.repository.dao;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import solicitarBeca.config.MongoClientProvider;
import solicitarBeca.excepciones.SolicitudDAOException;
import solicitarBeca.repository.dao.interfaces.ISolicitudDAO;
import solicitarBeca.repository.documents.SolicitudDocument;
import java.time.Instant;

/**
 * The type Solicitud dao.
 *
 * @author Cortez, Manuel;
 */
public class SolicitudDAO implements ISolicitudDAO {
    private final MongoCollection<SolicitudDocument> col;

    /**
     * Instantiates a new Solicitud dao.
     */
    public SolicitudDAO() {
        this.col = MongoClientProvider.INSTANCE.getCollection("solicitudes", SolicitudDocument.class);
    }

    @Override
    public ObjectId create(SolicitudDocument entity) throws SolicitudDAOException {
        try {
            if (entity.get_id() == null) {
                entity.set_id(new ObjectId());
            }
            entity.setCreadoEn(Instant.now());
            col.insertOne(entity);
            return entity.get_id();
        } catch (MongoException ex) {
            throw new SolicitudDAOException("Error al insertar Solicitud");
        }
    }
}
