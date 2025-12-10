package datosGobierno.daoGobierno;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import datosGobierno.configMongoGobierno.MongoClienteProvider;
import datosGobierno.documents.SolicitudDocument;
import datosGobierno.daoGobierno.excepcionesGobierno.SolicitudDAOException;
import datosGobierno.daoGobierno.interfacesGobierno.ISolicitudDAO;
import datosGobierno.dominioGobierno.Estudiante;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Solicitud dao.
 *
 * @author Cortez, Manuel;
 */
public class SolicitudDAO implements ISolicitudDAO {
    private final MongoCollection<SolicitudDocument> col;

    /**
     * Instantiates a new Solicitud dao.
     */
    public SolicitudDAO(){
        this.col = MongoClienteProvider.INSTANCE.getCollection("solicitudes", SolicitudDocument.class);
    }

    @Override
    public boolean guardar(SolicitudDocument solicitud){
        try {
            if (solicitud.get_id() == null) {
                solicitud.set_id(new ObjectId());
            }
            solicitud.setCreadoEn(Instant.now());
            col.insertOne(solicitud);
            return true;
        } catch (MongoException ex) {
            return false;
        }
    }

    @Override
    public List<Solicitud> obtenerListadoSolicitudes(String tipoBeca) {
        try {
            MongoCollection<SolicitudDocument> col = MongoClienteProvider.INSTANCE
                    .getCollection("solicitudes", SolicitudDocument.class);
            List<SolicitudDocument> solicitudes = new ArrayList<>();
            Bson filtro = Filters.and(
                    Filters.eq("beca.tipo", tipoBeca),
                    Filters.eq("estado", "ACTIVA")
            );
            col.find(filtro).into(solicitudes);
            MongoCollection<Estudiante> colEst = MongoClienteProvider.INSTANCE
                    .getCollection("estudiantes", Estudiante.class);
            List<Solicitud> resultado = new ArrayList<>();
            for (SolicitudDocument sol : solicitudes) {
                Estudiante estudiante = colEst.find(Filters.eq("_id", sol.getEstudiante())).first();
                if (estudiante != null) {
                    Solicitud solicitud = new Solicitud();
                    solicitud.setIdSolicitud(sol.getIdSolicitud());
                    solicitud.setFecha(sol.getFecha());
                    solicitud.setEstado(sol.getEstado());
                    solicitud.setBeca(sol.getBeca());
                    solicitud.setEstudiante(estudiante);
                    solicitud.setHistorialAcademico(sol.getHistorialAcademico());
                    solicitud.setInformacionSocioeconomica(sol.getInformacionSocioeconomica());
                    resultado.add(solicitud);
                }
            }
            return resultado;
        } catch (Exception ex) {
            throw new SolicitudDAOException("Error al obtener las solicitudes por tipo de beca: " + ex.getMessage());
        }
    }

    @Override
    public boolean cambiarEstado(int id, EstadoSolicitud estado) {
        try {
            if (estado == null) {
                throw new SolicitudDAOException("El estado no puede ser nulo");
            }
            Bson filtro = Filters.eq("idSolicitud", (long) id);
            SolicitudDocument solicitud = col.find(filtro).first();
            if (solicitud == null) {
                throw new SolicitudDAOException("No se encontró solicitud con ID " + id);
            }
            Bson update = Updates.set("estado", estado);
            col.updateOne(filtro, update);
            return true;
        } catch (SolicitudDAOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new SolicitudDAOException("Error al cambiar el estado de la solicitud: " + ex.getMessage());
        }
    }

    @Override
    public Solicitud obtenerPorId(int idSolicitud) {
        try {
            MongoCollection<SolicitudDocument> colDoc = MongoClienteProvider.INSTANCE
                    .getCollection("solicitudes", SolicitudDocument.class);

            Bson filtro = Filters.eq("idSolicitud", (long) idSolicitud);
            SolicitudDocument solicitudDoc = colDoc.find(filtro).first();

            if (solicitudDoc == null) {
                throw new SolicitudDAOException("No se encontró solicitud con ID " + idSolicitud);
            }

            MongoCollection<Estudiante> colEst = MongoClienteProvider.INSTANCE
                    .getCollection("estudiantes", Estudiante.class);
            Estudiante estudiante = colEst.find(
                    Filters.eq("_id", solicitudDoc.getEstudiante())).first();

            if (estudiante == null) {
                throw new SolicitudDAOException("No se encontró el estudiante asociado");
            }

            Solicitud solicitud = new Solicitud();
            solicitud.setIdSolicitud(solicitudDoc.getIdSolicitud());
            solicitud.setBeca(solicitudDoc.getBeca());
            solicitud.setEstudiante(estudiante);
            solicitud.setInformacionSocioeconomica(solicitudDoc.getInformacionSocioeconomica());
            solicitud.setHistorialAcademico(solicitudDoc.getHistorialAcademico());
            solicitud.setFecha(solicitudDoc.getFecha());
            solicitud.setEstado(solicitudDoc.getEstado());

            return solicitud;

        } catch (SolicitudDAOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new SolicitudDAOException("Error al obtener la solicitud por ID: " + ex.getMessage());
        }
    }

    @Override
    public List<Solicitud> obtenerPorIdEstudiante(ObjectId idEstudiante) {
        try {
            if (idEstudiante == null) {
                return new ArrayList<>();
            }
            MongoCollection<Estudiante> colEst = MongoClienteProvider.INSTANCE
                    .getCollection("estudiantes", Estudiante.class);
            Estudiante estudiante = colEst.find(Filters.eq("_id", idEstudiante)).first();
            if (estudiante == null) {
                return new ArrayList<>();
            }
            MongoCollection<SolicitudDocument> colSol = MongoClienteProvider.INSTANCE
                    .getCollection("solicitudes", SolicitudDocument.class);
            Bson filtro = Filters.eq("estudiante", idEstudiante);
            List<SolicitudDocument> documentos = new ArrayList<>();
            colSol.find(filtro).into(documentos);
            List<Solicitud> resultado = new ArrayList<>();
            for (SolicitudDocument doc : documentos) {
                Solicitud solicitud = new Solicitud();
                solicitud.setIdSolicitud(doc.getIdSolicitud());
                solicitud.setFecha(doc.getFecha());
                solicitud.setEstado(doc.getEstado());
                solicitud.setBeca(doc.getBeca());
                solicitud.setHistorialAcademico(doc.getHistorialAcademico());
                solicitud.setInformacionSocioeconomica(doc.getInformacionSocioeconomica());
                solicitud.setEstudiante(estudiante);
                resultado.add(solicitud);
            }
            return resultado;
        } catch (Exception ex) {
            throw new SolicitudDAOException("Error al obtener solicitudes por estudiante: " + ex.getMessage());
        }
    }

}