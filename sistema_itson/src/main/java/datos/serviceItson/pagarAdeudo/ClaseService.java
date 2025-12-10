package datos.serviceItson.pagarAdeudo;

import datos.adaptadoresItson.pagarAdeudo.ClaseAdaptador;
import datos.dominioItson.pagarAdeudo.Clase;
import datos.repositoryItson.daoItson.pagarAdeudo.impl.ClaseDAO;
import datos.repositoryItson.daoItson.pagarAdeudo.documents.ClaseDocument;

import java.util.ArrayList;
import java.util.List;

public class ClaseService {
    private final ClaseDAO claseDAO;

    public ClaseService(){
        this.claseDAO = new ClaseDAO();
    }

    public List<Clase> obtenerListaClases(Long matricula){
        List<ClaseDocument> clasesDocument = claseDAO.obtenerListaClasesPendientesByMatricula(matricula);
        List<Clase> clases = new ArrayList<>();
        for(ClaseDocument claseDocument : clasesDocument){
            clases.add(ClaseAdaptador.toEntity(claseDocument));
        }

        return clases;
    }

}
