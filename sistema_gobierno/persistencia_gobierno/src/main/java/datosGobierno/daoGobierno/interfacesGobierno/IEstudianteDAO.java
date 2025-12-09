package datosGobierno.daoGobierno.interfacesGobierno;
import datosGobierno.documents.EstudianteDocument;
import org.bson.types.ObjectId;

/**
 * The interface Estudiante dao.
 *
 * @author Cortez, Manuel;
 */
public interface IEstudianteDAO {

    /**
     * Guardar object id.
     *
     * @param entity the entity
     * @return the object id
     */
    ObjectId guardar(EstudianteDocument entity);
}
