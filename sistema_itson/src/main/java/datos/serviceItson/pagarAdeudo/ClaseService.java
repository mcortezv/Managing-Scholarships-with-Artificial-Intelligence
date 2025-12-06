package datos.serviceItson.pagarAdeudo;

import datos.adaptadoresItson.pagarAdeudo.ClaseAdaptador;
import datos.dominioItson.pagarAdeudo.Clase;
import datos.repositoryItson.daoItson.pagarAdeudo.impl.ClaseDAO;
import datos.repositoryItson.documents.pagarAdeudo.ClaseDocument;
import solicitarBeca.dominio.Beca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            System.out.println("clase service\n");
            System.out.println(claseDocument.getCosto());
        }

        return clases;
    }

}
