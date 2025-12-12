package datosGobierno.daoGobierno;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import datosGobierno.configMongoGobierno.MongoClienteProvider;
import datosGobierno.daoGobierno.excepcionesGobierno.EstudianteDAOException;
import datosGobierno.daoGobierno.interfacesGobierno.IEstudianteDAO;
import datosGobierno.documents.EstudianteDocument;
import org.bson.types.ObjectId;
import java.time.Instant;

/**
 * The type Estudiante dao.
 *
 * @author Cortez, Manuel;
 */
public class EstudianteDAO implements IEstudianteDAO {
    private final MongoCollection<EstudianteDocument> col;

    /**
     * Instantiates a new Estudiante dao.
     */
    public EstudianteDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("estudiantes", EstudianteDocument.class);
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
