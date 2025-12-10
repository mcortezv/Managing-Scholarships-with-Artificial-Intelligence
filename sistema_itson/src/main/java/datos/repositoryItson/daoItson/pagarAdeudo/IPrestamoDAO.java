package datos.repositoryItson.daoItson.pagarAdeudo;

import datos.repositoryItson.daoItson.pagarAdeudo.documents.PrestamoDocument;
import java.util.List;

public interface IPrestamoDAO {
    List<PrestamoDocument> obtenerListaPrestamosPendientesByMatricula(Long matricula);
}
