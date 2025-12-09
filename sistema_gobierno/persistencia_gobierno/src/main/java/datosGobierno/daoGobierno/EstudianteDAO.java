package datosGobierno.daoGobierno;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import solicitarBeca.config.MongoClientProvider;
import solicitarBeca.excepciones.EstudianteDAOException;
import solicitarBeca.repository.dao.interfaces.IEstudianteDAO;
import solicitarBeca.repository.documents.EstudianteDocument;

import java.time.Instant;

/**
 *
 * @author Cortez, Manuel;
 */
public class EstudianteDAO implements IEstudianteDAO {
    private final MongoCollection<EstudianteDocument> col;
    private final MongoCollection<Document> colDoc;

    public EstudianteDAO() {
        this.col = MongoClientProvider.INSTANCE.getCollection("estudiantes", EstudianteDocument.class);
        this.colDoc = MongoClientProvider.INSTANCE.getCollection("estudiantes", Document.class);
    }

    @Override
    public ObjectId create(EstudianteDocument entity) throws EstudianteDAOException {
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
