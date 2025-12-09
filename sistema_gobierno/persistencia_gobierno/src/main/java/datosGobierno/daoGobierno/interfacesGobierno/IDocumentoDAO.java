package datosGobierno.daoGobierno.interfacesGobierno;
import datosGobierno.documents.DocumentoDocument;
import org.bson.types.ObjectId;

/**
 * The interface Documento dao.
 *
 * @author Cortez, Manuel;
 */
public interface IDocumentoDAO {

    /**
     * Guardar object id.
     *
     * @param entity the entity
     * @return the object id
     */
    ObjectId guardar(DocumentoDocument entity);
}
