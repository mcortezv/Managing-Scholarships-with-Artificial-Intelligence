/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutorias.dao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import static com.mongodb.client.model.Updates.set;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import tutorias.config.MongoClientProvider;
import tutorias.dao.interfaces.IHorarioDAO;
import tutorias.dominio.Horario;
import tutorias.dominio.enums.EstadoDisponibilidad;
import tutorias.excepciones.HorarioDAOException;
import tutorias.repository.documents.HorarioDocument;

/**
 *
 * @author katia
 */
public class HorarioDAO implements IHorarioDAO{
    private final MongoCollection<HorarioDocument> col;

    public HorarioDAO() {
        MongoDatabase db = MongoClientProvider.INSTANCE.database();
        this.col = db.getCollection("horarios", HorarioDocument.class);
    }

    @Override
    public Horario crear(Horario horario) throws HorarioDAOException {
        try {
            HorarioDocument doc = entityToDocument(horario);
            if (doc.get_id() == null) {
                doc.set_id(new ObjectId());
            }
            if (doc.getCreadoEn() == null) {
                doc.setCreadoEn(Instant.now());
            }
            col.insertOne(doc);
            horario.setId(doc.getId());
            return horario;
        } catch (MongoException ex) {
            throw new HorarioDAOException("Error al insertar Horario: " + ex.getMessage());
        }
    }

    @Override
    public List<Horario> obtenerDisponiblesPorTutorYFecha(Long idTutor, LocalDate fecha) throws HorarioDAOException{
        try {
            List<HorarioDocument> documents = col.find(
                and(
                    eq("idTutor", idTutor),
                    eq("fecha", fecha),
                    eq("estadoDisponibilidad", "DISPONIBLE")
                )
            ).into(new ArrayList<>());
            return convertirDocumentsADominio(documents);
        } catch (MongoException ex) {
            throw new HorarioDAOException("Error al consultar horarios disponibles: " + ex.getMessage());
        }
    }
    
    @Override
    public Horario actualizarEstado(Long idHorario, EstadoDisponibilidad nuevoEstado) throws HorarioDAOException {
        try {
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            HorarioDocument actualizado = col.findOneAndUpdate(
                eq("id", idHorario),
                set("estadoDisponibilidad", nuevoEstado.name()),
                options
            );
            if (actualizado == null) {
                throw new HorarioDAOException("No se encontró el horario con id " + idHorario);
            }
            return documentToEntity(actualizado);
        } catch (MongoException ex) {
            throw new HorarioDAOException("Error al actualizar estado de disponibilidad: " + ex.getMessage());
        }
    }
    
    @Override
    public Horario obtenerPorId(Long id) throws HorarioDAOException {
        if (id == null || id <= 0) {
            throw new HorarioDAOException("El ID no puede ser nulo o inválido");
        }
        try {
            HorarioDocument doc = col.find(eq("id", id)).first();
            if (doc == null) {
                    return null;
            }
            return documentToEntity(doc);
        } catch (MongoException ex) {
            throw new HorarioDAOException("Error al obtener horario por ID: " + ex.getMessage());
        }
    }
    
    
    private Horario documentToEntity(HorarioDocument doc){
        if (doc == null) return null;
        Horario horario = new Horario();
        horario.setId(doc.getId());
        horario.setIdTutor(doc.getIdTutor());
        horario.setFecha(doc.getFecha());
        horario.setHora(doc.getHora());
        horario.setEstadoDisponibilidad(doc.getEstadoDisponibilidad());
        return horario;
    }
    
    private HorarioDocument entityToDocument(Horario horario){
        if (horario == null) return null;
        HorarioDocument doc = new HorarioDocument();
        doc.set_id(new ObjectId());
        doc.setId(horario.getId());
        doc.setIdTutor(horario.getIdTutor());
        doc.setFecha(horario.getFecha());
        doc.setHora(horario.getHora());
        doc.setEstadoDisponibilidad(horario.getEstadoDisponibilidad());        
        return doc;
    }
    
    private List<Horario> convertirDocumentsADominio(List<HorarioDocument> documents){
        List<Horario> resultado = new ArrayList<>();
        for (HorarioDocument doc : documents) {
            resultado.add(documentToEntity(doc));
        }
        return resultado;
    }
}
