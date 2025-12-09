package datosGobierno.daoGobierno;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import datosGobierno.daoGobierno.excepcionesGobierno.EstudianteDAOException;
import datosGobierno.daoGobierno.interfacesGobierno.IEstudianteDAO;
import datosGobierno.documents.EstudianteDocument;
import org.bson.Document;
import org.bson.types.ObjectId;
import solicitarBeca.config.MongoClientProvider;
import java.time.Instant;

/**
 * The type Estudiante dao.
 *
 * @author Cortez, Manuel;
 */
public class EstudianteDAO implements IEstudianteDAO {
    private final MongoCollection<EstudianteDocument> col;
    private final MongoCollection<Document> colDoc;

    /**
     * Instantiates a new Estudiante dao.
     */
    public EstudianteDAO() {
        this.col = MongoClientProvider.INSTANCE.getCollection("estudiantes", EstudianteDocument.class);
        this.colDoc = MongoClientProvider.INSTANCE.getCollection("estudiantes", Document.class);
    }

    @Override
    public ObjectId guardar(EstudianteDocument entity) throws EstudianteDAOException {
        try {
            if (entity.get_id() == null) {
                entity.set_id(new ObjectId());
            }
            entity.setCreadoEn(Instant.now());
            col.insertOne(entity);
            return entity.get_id();
        } catch (MongoException ex) {
            throw new EstudianteDAOException("Error al insertar Estudiante");
        }
    }
}
