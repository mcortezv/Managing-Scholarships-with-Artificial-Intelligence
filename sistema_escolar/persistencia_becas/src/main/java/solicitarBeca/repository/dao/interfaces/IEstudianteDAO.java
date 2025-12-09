package solicitarBeca.repository.dao.interfaces;
import org.bson.types.ObjectId;
import solicitarBeca.excepciones.EstudianteDAOException;
import solicitarBeca.repository.documents.EstudianteDocument;

/**
 *
 * @author Cortez, Manuel;
 */
public interface IEstudianteDAO {

    ObjectId guardar(EstudianteDocument entity) throws EstudianteDAOException;
}
