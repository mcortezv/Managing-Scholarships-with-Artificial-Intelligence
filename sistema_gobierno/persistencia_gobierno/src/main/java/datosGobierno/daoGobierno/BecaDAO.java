package datosGobierno.daoGobierno;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import datosGobierno.configMongoGobierno.MongoClienteProvider;
import datosGobierno.documents.SolicitudDocument;
import datosGobierno.daoGobierno.excepcionesGobierno.BecaDAOException;
import datosGobierno.daoGobierno.interfacesGobierno.IBecaDAO;
import datosGobierno.dominioGobierno.Beca;
import gobierno.RequisitosDTOGobierno;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DAO corregido para Beca
 * @author Cortez, Manuel
 */
public class BecaDAO implements IBecaDAO {
    private final MongoCollection<Beca> colBecas;
    private final MongoCollection<SolicitudDocument> colSolicitudes;

    public BecaDAO() {
        this.colBecas = MongoClienteProvider.INSTANCE.getCollection("becas", Beca.class);
        this.colSolicitudes = MongoClienteProvider.INSTANCE.getCollection("solicitudes", SolicitudDocument.class);
    }

    @Override
    public List<Beca> findByRequisitos(RequisitosDTOGobierno r) {
        try {
            List<Beca> resultado = new ArrayList<>();
            Bson filtro = Filters.and(
                    Filters.lte("requisitos.promedioMinimo", r.getPromedioMinimo()),
                    Filters.gte("requisitos.ingresoFamiliarMaximo", r.getIngresoFamiliarMaximo()),
                    Filters.lte("requisitos.cargaAcademica", r.getCargaAcademica()),
                    Filters.eq("requisitos.trabajo", r.isTrabajo()),
                    Filters.eq("requisitos.deudas", r.isDeudas())
            );
            colBecas.find(filtro).into(resultado);
            return resultado;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BecaDAOException("Error al buscar becas con los requisitos proporcionados.");
        }
    }

    @Override
    public List<Beca> obtenerBecasConSolicitudes() {
        try {
            // Obtener todos los tipos de beca que tienen solicitudes
            List<SolicitudDocument> solicitudes = new ArrayList<>();
            colSolicitudes.find().into(solicitudes);

            if (solicitudes.isEmpty()) {
                System.out.println("DEBUG: No hay solicitudes en la base de datos");
                return new ArrayList<>();
            }

            // Extraer tipos únicos de beca de las solicitudes
            Set<String> tiposBecaConSolicitudes = new HashSet<>();
            for (SolicitudDocument sol : solicitudes) {
                if (sol.getBeca() != null && sol.getBeca().getTipo() != null) {
                    tiposBecaConSolicitudes.add(sol.getBeca().getTipo().toString());
                }
            }

            System.out.println("DEBUG: Tipos de beca encontrados: " + tiposBecaConSolicitudes);

            if (tiposBecaConSolicitudes.isEmpty()) {
                System.out.println("DEBUG: No hay tipos de beca en las solicitudes");
                return new ArrayList<>();
            }

            // Obtener las becas que coinciden con esos tipos
            List<Beca> becasConSolicitudes = new ArrayList<>();
            for (String tipo : tiposBecaConSolicitudes) {
                Bson filtro = Filters.eq("tipo", tipo);
                List<Beca> becasTipo = new ArrayList<>();
                colBecas.find(filtro).into(becasTipo);
                becasConSolicitudes.addAll(becasTipo);
            }

            System.out.println("DEBUG: Becas con solicitudes encontradas: " + becasConSolicitudes.size());

            return becasConSolicitudes;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BecaDAOException("Error al obtener becas con solicitudes: " + ex.getMessage());
        }
    }
}