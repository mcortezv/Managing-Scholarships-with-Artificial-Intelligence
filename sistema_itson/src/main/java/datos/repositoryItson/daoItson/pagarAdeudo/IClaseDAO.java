package datos.repositoryItson.daoItson.pagarAdeudo;

import datos.repositoryItson.documents.pagarAdeudo.ClaseDocument;

import java.util.List;

public interface IClaseDAO {
    List<ClaseDocument> obtenerListaClasesPendientesByMatricula(Long matricula);
}
