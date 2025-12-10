package datos.repositoryItson.daoItson.pagarAdeudo;

import datos.repositoryItson.daoItson.pagarAdeudo.documents.ClaseDocument;

import java.util.List;

public interface IClaseDAO {
    List<ClaseDocument> obtenerListaClasesPendientesByMatricula(Long matricula);
}
