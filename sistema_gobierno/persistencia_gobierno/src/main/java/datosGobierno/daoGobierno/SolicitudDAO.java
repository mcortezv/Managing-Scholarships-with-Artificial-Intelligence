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
    public boolean guardarSolicitud(SolicitudDocument solicitud){
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
                    solicitud.setId(sol.getId());
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
            Bson filtro = Filters.eq("id", (long) id);
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

            Bson filtro = Filters.eq("id", (long) idSolicitud);
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
            solicitud.setId(solicitudDoc.getId());
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
    public boolean actualizar(Solicitud solicitud) {
        try {
            if (solicitud == null) {
                throw new SolicitudDAOException("La solicitud no puede ser nula");
            }

            Bson filtro = Filters.eq("id", solicitud.getId());

            SolicitudDocument existente = col.find(filtro).first();
            if (existente == null) {
                throw new SolicitudDAOException("No existe solicitud con ID " + solicitud.getId());
            }
            Bson update = Updates.combine(
                    Updates.set("beca", solicitud.getBeca()),
                    Updates.set("informacionSocioeconomica", solicitud.getInformacionSocioeconomica()),
                    Updates.set("historialAcademico", solicitud.getHistorialAcademico()),
                    Updates.set("estado", solicitud.getEstado()),
                    Updates.set("fecha", solicitud.getFecha())
            );

            col.updateOne(filtro, update);
            return true;

        } catch (SolicitudDAOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new SolicitudDAOException("Error al actualizar la solicitud: " + ex.getMessage());
        }
    }
}