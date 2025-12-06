package datos.repositoryItson.daoItson.pagarAdeudo.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import datos.configMongoItson.MongoClienteProvider;
import datos.excepciones.DaoException;
import datos.repositoryItson.daoItson.pagarAdeudo.IPrestamoDAO;
import datos.repositoryItson.documents.pagarAdeudo.PrestamoDocument;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO implements IPrestamoDAO {
    private final MongoCollection<PrestamoDocument> col;

    public PrestamoDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("prestamos", PrestamoDocument.class);
    }

    @Override
    public List<PrestamoDocument> obtenerListaPrestamosPendientesByMatricula(Long matricula){
        try{
            return col.find(
                    Filters.and(
                            Filters.eq("idEstudiante", matricula),
                            Filters.eq("estatus", "Pendiente")
                    )
            ).into(new ArrayList<>());
        }catch (DaoException ex){
            throw new DaoException("Error al obtener prestamos por matricula" +  ex.getMessage());
        }
    }

    public void liquidarPrestamosPorEstudiante(Long idEstudiante) {
        try {
            Bson filtro = Filters.and(
                    Filters.eq("idEstudiante", idEstudiante),
                    Filters.eq("estatus", "Pendiente")
            );

            Bson actualizacion = Updates.set("estatus", "Pagado");
            col.updateMany(filtro, actualizacion);
            System.out.println("Préstamos actualizados a PAGADO para el estudiante: " + idEstudiante);
        } catch (Exception e) {
            System.err.println("Error al liquidar préstamos: " + e.getMessage());
        }
    }

}
