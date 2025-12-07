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
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import static com.mongodb.client.model.Updates.set;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import tutorias.config.MongoClientProvider;
import tutorias.dao.interfaces.ICitaDAO;
import tutorias.dominio.Cita;
import tutorias.dominio.Materia;
import tutorias.dominio.enums.EstadoCita;
import tutorias.excepciones.CitaDAOException;
import tutorias.repository.documents.CitaDocument;

/**
 *
 * @author katia
 */
public class CitaDAO implements ICitaDAO {

    private final MongoCollection<CitaDocument> col;
    
    public CitaDAO() {
        MongoDatabase db = MongoClientProvider.INSTANCE.database();
        this.col = db.getCollection("citas", CitaDocument.class);
    }
    
    @Override
    public Cita crear(Cita cita) throws CitaDAOException{
        try {
            CitaDocument doc = entityToDocument(cita);
            if (doc.get_id() == null) {
                doc.set_id(new ObjectId());
            }
            if (doc.getId() == null) {
                doc.setId(generarNuevoId());
            }
            if (doc.getCreadoEn() == null) {
                doc.setCreadoEn(Instant.now());
            }
            col.insertOne(doc);
            cita.setId(doc.getId());
            return cita;
        } catch (MongoException ex) {
            throw new CitaDAOException("Error al insertar Cita: " + ex.getMessage());
        }
    }

    @Override
    public List<Cita> obtenerHistorialCompletoAlumno(Long matriculaAlumno) throws CitaDAOException{
        try {
            List<CitaDocument> documents = col.find(eq("matriculaAlumno", matriculaAlumno))
                                              .into(new ArrayList<>());
            return convertirDocumentsADominio(documents);
        } catch (MongoException ex) {
            throw new CitaDAOException("Error al consultar historial completo: " + ex.getMessage());
        }
    }

    @Override
    public List<Cita> obtenerHistorialPorFecha(Long matriculaAlumno, LocalDate fecha) throws CitaDAOException{
        try {
            List<CitaDocument> documents = col.find(
                and(eq("matriculaAlumno", matriculaAlumno),
                    eq("fecha", fecha))
            ).into(new ArrayList<>());
            return convertirDocumentsADominio(documents);
        } catch (MongoException ex) {
            throw new CitaDAOException("Error al consultar por fecha: " + ex.getMessage());
        }
    }

    @Override
    public List<Cita> obtenerHistorialPorMateria(Long matriculaAlumno, Long idMateria) throws CitaDAOException{
        try {
            List<CitaDocument> documents = col.find(
                and(eq("matriculaAlumno", matriculaAlumno),
                    eq("idMateria", idMateria))
            ).into(new ArrayList<>());
            return convertirDocumentsADominio(documents);
        } catch (MongoException ex) {
            throw new CitaDAOException("Error al consultar por materia: " + ex.getMessage());
        }
    }

    @Override
    public List<Cita> obtenerHistorialPorFechaYMateria(Long matriculaAlumno, LocalDate fecha, Long idMateria) throws CitaDAOException{
        try {
            List<CitaDocument> documents = col.find(
                and(eq("matriculaAlumno", matriculaAlumno),
                    eq("fecha", fecha),
                    eq("idMateria", idMateria))
            ).into(new ArrayList<>());
            return convertirDocumentsADominio(documents);
        } catch (MongoException ex) {
            throw new CitaDAOException("Error al consultar por fecha y materia: " + ex.getMessage());
        }
    }

    @Override
    public List<Cita> obtenerFuturasPorAlumno(Long matriculaAlumno) throws CitaDAOException{
        try {
            List<CitaDocument> documents = col.find(
                and(eq("matriculaAlumno", matriculaAlumno),
                    gte("fecha", LocalDate.now()))
            ).into(new ArrayList<>());
            return convertirDocumentsADominio(documents);
        } catch (MongoException ex) {
            throw new CitaDAOException("Error al consultar citas futuras: " + ex.getMessage());
        }
    }

    @Override
    public int contarCitasPorAlumnoYEstadoEnMes(Long matriculaAlumno, EstadoCita estado, int mes, int anio) throws CitaDAOException{
        try {
            LocalDate inicioMes = LocalDate.of(anio, mes, 1);
            LocalDate finMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());
            
            long count = col.countDocuments(
                and(
                    eq("matriculaAlumno", matriculaAlumno),
                    eq("estado", estado.name()),
                    gte("fecha", inicioMes),
                    lte("fecha", finMes)
                )
            );
            return (int) count;
        } catch (MongoException ex) {
            throw new CitaDAOException("Error al contar citas: " + ex.getMessage());
        }
    }

    @Override
    public Cita actualizarEstado(Long idCita, EstadoCita nuevoEstado) throws CitaDAOException{
        try {
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions()
                    .returnDocument(ReturnDocument.AFTER);
            CitaDocument actualizada = col.findOneAndUpdate(
                    eq("id", idCita.intValue()),
                    set("estado", nuevoEstado.name()),
                    options
            );
            if (actualizada == null) {
                throw new CitaDAOException("No se encontró la cita con id " + idCita);
            }
            return documentToEntity(actualizada);
        } catch (MongoException ex) {
            throw new CitaDAOException("Error al actualizar estado: " + ex.getMessage());
        }
    }

    @Override
    public List<Cita> obtenerPorTutorYFecha(Long idTutor, LocalDate fecha) throws CitaDAOException{
        try {
            List<CitaDocument> documents = col.find(
                and(
                    eq("idTutor", idTutor),
                    eq("fecha", fecha)
                )
            ).into(new ArrayList<>());
            return convertirDocumentsADominio(documents);
        } catch (MongoException ex) {
            throw new CitaDAOException("Error al consultar por tutor y fecha: " + ex.getMessage());
        }
    }
    
    private Cita documentToEntity(CitaDocument doc){
        if (doc == null) return null;
        Cita cita = new Cita();
        cita.setId(doc.getId());
        cita.setMatriculaAlumno(doc.getMatriculaAlumno());
        cita.setIdTutor(doc.getIdTutor());
        cita.setIdHorario(doc.getIdHorario());
        cita.setTema(doc.getTema());
        cita.setModalidad(doc.getModalidad());
        cita.setFecha(doc.getFecha());
        cita.setHora(doc.getHora());
        cita.setUbicacion(doc.getUbicacion());
        cita.setEstado(doc.getEstado());
        if (doc.getIdMateria() != null) {
            Materia materia = new Materia();
            materia.setId(doc.getIdMateria());
            cita.setMateria(materia);
        }
        return cita;
    }
    
    private CitaDocument entityToDocument(Cita cita){
        if (cita == null) return null;
        CitaDocument doc = new CitaDocument();
        doc.set_id(new ObjectId());
        doc.setId(cita.getId());
        doc.setMatriculaAlumno(cita.getMatriculaAlumno());
        doc.setIdTutor(cita.getIdTutor());
        doc.setIdHorario(cita.getIdHorario());
        doc.setTema(cita.getTema());
        doc.setModalidad(cita.getModalidad());
        doc.setFecha(cita.getFecha());
        doc.setHora(cita.getHora());
        doc.setUbicacion(cita.getUbicacion());
        doc.setEstado(cita.getEstado());
        if (cita.getMateria() != null) {
            doc.setIdMateria(cita.getMateria().getId());
        }
        return doc;
    }
    
    private List<Cita> convertirDocumentsADominio(List<CitaDocument> documents){
        List<Cita> resultado = new ArrayList<>();
        for (CitaDocument doc : documents) {
            resultado.add(documentToEntity(doc));
        }
        return resultado;
    }
    
    private Long generarNuevoId() {
        try {
            CitaDocument ultimaCita = col.find()
                .sort(new org.bson.Document("id", -1))
                .first();
            if (ultimaCita == null || ultimaCita.getId() == null) {
                return 1L;
            }

            return ultimaCita.getId() + 1;

        } catch (Exception ex) {
            return System.currentTimeMillis();
        }
    }
}
