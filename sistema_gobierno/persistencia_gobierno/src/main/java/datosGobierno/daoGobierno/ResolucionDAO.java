package datosGobierno.daoGobierno;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import datosGobierno.configMongoGobierno.MongoClienteProvider;
import datosGobierno.daoGobierno.excepcionesGobierno.ResolucionDAOException;
import datosGobierno.daoGobierno.interfacesGobierno.IResolucionDAO;
import datosGobierno.dominioGobierno.Resolucion;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones de Resolución en MongoDB
 * @author Cortez, Manuel
 */
public class ResolucionDAO implements IResolucionDAO {
    private final MongoCollection<Resolucion> col;

    public ResolucionDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("resoluciones", Resolucion.class);
    }

    @Override
    public boolean guardar(Resolucion resolucion) {
        try {
            if (resolucion == null) {
                throw new ResolucionDAOException("La resolución no puede ser nula");
            }
            Bson filtro = Filters.eq("solicitud.id", resolucion.getSolicitud().getId());
            Resolucion existente = col.find(filtro).first();
            if (existente != null) {
                throw new ResolucionDAOException("Ya existe una resolución para la solicitud " + resolucion.getSolicitud().getId());
            }
            col.insertOne(resolucion);
            return true;
        } catch (Exception ex) {
            throw new ResolucionDAOException("Error al guardar la resolución: " + ex.getMessage());
        }
    }

    @Override
    public boolean actualizar(Resolucion resolucion) {
        try {
            if (resolucion == null) {
                throw new ResolucionDAOException("La resolución no puede ser nula");
            }
            Bson filtro = Filters.eq("solicitud.id", resolucion.getSolicitud().getId());
            Resolucion existente = col.find(filtro).first();
            if (existente == null) {
                throw new ResolucionDAOException("No existe resolución para la solicitud " + resolucion.getSolicitud().getId());
            }
            Bson update = Updates.combine(
                    Updates.set("decision", resolucion.getDecision()),
                    Updates.set("motivo", resolucion.getMotivo()),
                    Updates.set("fechaEvaluacion", resolucion.getFechaEvaluacion()),
                    Updates.set("solicitud", resolucion.getSolicitud())
            );
            col.updateOne(filtro, update);
            return true;
        } catch (Exception ex) {
            throw new ResolucionDAOException("Error al actualizar la resolución: " + ex.getMessage());
        }
    }

    @Override
    public Resolucion obtenerPorId(int id) {
        try {
            Bson filtro = Filters.eq("solicitud.id", (long) id);
            Resolucion resolucion = col.find(filtro).first();
            if (resolucion == null) {
                throw new ResolucionDAOException("No se encontró resolución con ID " + id);
            }
            return resolucion;
        } catch (Exception ex) {
            throw new ResolucionDAOException("Error al obtener la resolución por ID: " + ex.getMessage());
        }
    }

    @Override
    public Resolucion obtenerPorFiltro(String tipoFiltro, String filtro) {
        try {
            Bson query;
            switch (tipoFiltro.toUpperCase()) {
                case "MATRICULA":
                    query = Filters.eq("solicitud.estudiante.matricula", Long.parseLong(filtro));
                    break;
                case "NOMBRE":
                    query = Filters.regex("solicitud.estudiante.nombre",
                            ".*" + filtro + ".*", "i");
                    break;
                case "ID_SOLICITUD":
                    query = Filters.eq("solicitud.id", Long.parseLong(filtro));
                    break;
                default:
                    throw new ResolucionDAOException("Tipo de filtro inválido: " + tipoFiltro);
            }
            Resolucion resolucion = col.find(query).first();
            if (resolucion == null) {
                throw new ResolucionDAOException(
                        "No se encontró resolución con " + tipoFiltro + ": " + filtro);
            }
            return resolucion;
        } catch (NumberFormatException ex) {
            throw new ResolucionDAOException("Formato de filtro inválido: " + filtro);
        } catch (Exception ex) {
            throw new ResolucionDAOException("Error al obtener resolución por filtro: " + ex.getMessage());
        }
    }

    @Override
    public List<Resolucion> obtenerTodas() {
        try {
            List<Resolucion> resoluciones = new ArrayList<>();
            col.find().into(resoluciones);
            return resoluciones;
        } catch (Exception ex) {
            throw new ResolucionDAOException("Error al obtener todas las resoluciones: " + ex.getMessage());
        }
    }

    /**
     * Obtiene resoluciones por tipo de beca
     */
    @Override
    public List<Resolucion> obtenerPorTipoBeca(String tipoBeca) {
        try {
            List<Resolucion> resoluciones = new ArrayList<>();
            Bson filtro = Filters.eq("solicitud.beca.tipo", tipoBeca);
            col.find(filtro).into(resoluciones);
            return resoluciones;

        } catch (Exception ex) {
            throw new ResolucionDAOException(
                    "Error al obtener resoluciones por tipo de beca: " + ex.getMessage());
        }
    }

    /**
     * Obtiene resoluciones por decisión
     */
    @Override
    public List<Resolucion> obtenerPorDecision(String decision) {
        try {
            List<Resolucion> resoluciones = new ArrayList<>();
            Bson filtro = Filters.eq("decision", decision);
            col.find(filtro).into(resoluciones);
            return resoluciones;

        } catch (Exception ex) {
            throw new ResolucionDAOException(
                    "Error al obtener resoluciones por decisión: " + ex.getMessage());
        }
    }

    /**
     * Elimina una resolución (para casos especiales)
     */
    @Override
    public boolean eliminar(long idSolicitud) {
        try {
            Bson filtro = Filters.eq("solicitud.id", idSolicitud);
            col.deleteOne(filtro);
            return true;

        } catch (Exception ex) {
            throw new ResolucionDAOException("Error al eliminar resolución: " + ex.getMessage());
        }
    }
}