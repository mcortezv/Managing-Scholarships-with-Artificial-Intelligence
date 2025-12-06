package datos.repositoryItson.daoItson.pagarAdeudo;

import datos.repositoryItson.documents.pagarAdeudo.PrestamoDocument;
import java.util.List;

public interface IPrestamoDAO {
    List<PrestamoDocument> obtenerListaPrestamosPendientesByMatricula(Long matricula);
}
