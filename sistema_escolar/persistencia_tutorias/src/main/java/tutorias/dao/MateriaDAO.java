package tutorias.dao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import tutorias.config.MongoClientProvider;
import tutorias.dao.interfaces.IMateriaDAO;
import tutorias.dominio.Materia;
import tutorias.excepciones.MateriaDAOException;
import tutorias.repository.documents.MateriaDocument;

/**
 *
 * @author katia
 */
public class MateriaDAO implements IMateriaDAO{
    private final MongoCollection<MateriaDocument> col;
    
    public MateriaDAO() {
        MongoDatabase db = MongoClientProvider.INSTANCE.database();
        this.col = db.getCollection("materias", MateriaDocument.class);
    }

    @Override
    public Materia crear(Materia materia) throws MateriaDAOException{
        try {
            MateriaDocument doc = entityToDocument(materia);    
            if (doc.get_id() == null) {
                doc.set_id(new ObjectId());
            }
            if (doc.getCreadoEn() == null) {
                doc.setCreadoEn(Instant.now());
            }            
            col.insertOne(doc);
            materia.setId(doc.getIdMateria());
            return materia;           
        } catch (MongoException ex) {
            throw new MateriaDAOException("Error al insertar Materia: " + ex.getMessage());
        }
    }

    @Override
    public List<Materia> obtenerMaterias() throws MateriaDAOException{
        try {
            List<MateriaDocument> documents = col.find().into(new ArrayList<>());
            List<Materia> materias = new ArrayList<>();            
            for (MateriaDocument doc : documents) {
                materias.add(documentToEntity(doc));
            }
            return materias;         
        } catch (MongoException ex) {
            throw new MateriaDAOException("Error al consultar materias: " + ex.getMessage());
        }
    }

    @Override
    public Materia obtenerPorId(Long idMateria) throws MateriaDAOException{
        try {
            MateriaDocument doc = col.find(eq("idMateria", idMateria.intValue())).first();           
            if (doc == null) {
                throw new MateriaDAOException("No se encontró la materia con id " + idMateria);
            }           
            return documentToEntity(doc);         
        } catch (MongoException ex) {
            throw new MateriaDAOException("Error al consultar materia por id: " + ex.getMessage());
        }
    }
    
    private Materia documentToEntity(MateriaDocument doc){
        if (doc == null) return null;        
        Materia materia = new Materia();
        materia.setId(doc.getIdMateria());
        materia.setNombre(doc.getNombre());
        return materia;
    }
    
    private MateriaDocument entityToDocument(Materia materia){
        if (materia == null) return null;       
        MateriaDocument doc = new MateriaDocument();
        doc.set_id(new ObjectId());
        doc.setIdMateria(materia.getId());
        doc.setNombre(materia.getNombre());
        return doc;
    }
}
