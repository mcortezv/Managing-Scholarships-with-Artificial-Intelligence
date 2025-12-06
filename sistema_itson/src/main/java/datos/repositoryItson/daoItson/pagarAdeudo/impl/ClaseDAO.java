package datos.repositoryItson.daoItson.pagarAdeudo.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import datos.configMongoItson.MongoClienteProvider;
import datos.dominioItson.pagarAdeudo.Clase;
import datos.excepciones.DaoException;
import datos.repositoryItson.daoItson.pagarAdeudo.IClaseDAO;
import datos.repositoryItson.documents.pagarAdeudo.ClaseDocument;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class ClaseDAO implements IClaseDAO {
    private final MongoCollection<ClaseDocument> col;

    public ClaseDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("clases", ClaseDocument.class);
    }

    @Override
    public List<ClaseDocument> obtenerListaClasesPendientesByMatricula(Long matricula){
        try{
            return col.find(
                    Filters.and(
                            Filters.eq("idEstudiante", matricula),
                            Filters.eq("estatus", "Pendiente")
                    )
            ).into(new ArrayList<>());
        }catch (DaoException ex){
            System.out.println("Error al obtener clases por matricula" +  ex.getMessage());
            throw new DaoException("Error al obtener clases por matricula" +  ex.getMessage());
        }
    }

    public void liquidarClasesPorEstudiante(Long idEstudiante) {
        try {
            Bson filtro = Filters.and(
                    Filters.eq("idEstudiante", idEstudiante),
                    Filters.eq("estatus", "Pendiente")
            );
            Bson actualizacion = Updates.set("estatus", "Pagado");

            col.updateMany(filtro, actualizacion);
            System.out.println("Clases actualizadas a PAGADO para el estudiante: " + idEstudiante);

        } catch (Exception e) {
            System.err.println("Error al liquidar clases: " + e.getMessage());
        }
    }
}
