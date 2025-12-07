/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import tutorias.dao.interfaces.ITutorDAO;
import tutorias.dominio.Tutor;
import tutorias.excepciones.TutorDAOException;
import tutorias.repository.documents.TutorDocument;

/**
 *
 * @author katia
 */
public class TutorDAO implements ITutorDAO{
    private final MongoCollection<TutorDocument> col;

    public TutorDAO() {
        MongoDatabase db = MongoClientProvider.INSTANCE.database();
        this.col = db.getCollection("tutores", TutorDocument.class);
    }

    @Override
    public Tutor crear(Tutor tutor) throws TutorDAOException {
        try {
            TutorDocument doc = entityToDocument(tutor);            
            if (doc.get_id() == null) {
                doc.set_id(new ObjectId());
            }
            if (doc.getCreadoEn() == null) {
                doc.setCreadoEn(Instant.now());
            }
            col.insertOne(doc);
            tutor.setId(doc.getIdTutor());
            return tutor;
        } catch (MongoException ex) {
            throw new TutorDAOException("Error al insertar Tutor: " + ex.getMessage());
        }
    }

    @Override
    public List<Tutor> obtenerTutores() throws TutorDAOException {
        try {
            List<TutorDocument> documents = col.find().into(new ArrayList<>());
            List<Tutor> tutores = new ArrayList<>();
            for (TutorDocument doc : documents) {
                tutores.add(documentToEntity(doc));
            }
            return tutores;
        } catch (MongoException ex) {
            throw new TutorDAOException("Error al consultar tutores: " + ex.getMessage());
        }
    }

    @Override
    public Tutor obtenerPorId(Long idTutor) throws TutorDAOException {
        try {
            TutorDocument doc = col.find(eq("idTutor", idTutor.intValue())).first();
            if (doc == null) {
                throw new TutorDAOException("No se encontró el tutor con id " + idTutor);
            }
            return documentToEntity(doc);
        } catch (MongoException ex) {
            throw new TutorDAOException("Error al consultar tutor por id: " + ex.getMessage());
        }
    }
    
    private Tutor documentToEntity(TutorDocument doc) {
        if (doc == null) return null;
        Tutor tutor = new Tutor();
        tutor.setId(doc.getIdTutor());
        tutor.setNombre(doc.getNombre());
        tutor.setCarrera(doc.getCarrera());
        tutor.setCubiculo(doc.getCubiculo());
        tutor.setEnlace(doc.getEnlace());
        return tutor;
    }
    
    private TutorDocument entityToDocument(Tutor tutor) {
        if (tutor == null) return null;
        TutorDocument doc = new TutorDocument();
        doc.set_id(new ObjectId());
        doc.setIdTutor(tutor.getId());
        doc.setNombre(tutor.getNombre());
        doc.setCarrera(tutor.getCarrera());
        doc.setCubiculo(tutor.getCubiculo());
        doc.setEnlace(tutor.getEnlace());
        return doc;
    }
}
