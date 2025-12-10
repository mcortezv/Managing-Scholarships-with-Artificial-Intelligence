package datos.serviceItson.pagarAdeudo;

import datos.adaptadoresItson.pagarAdeudo.PrestamoAdaptador;
import datos.dominioItson.pagarAdeudo.Prestamo;
import datos.repositoryItson.daoItson.pagarAdeudo.impl.PrestamoDAO;
import datos.repositoryItson.daoItson.pagarAdeudo.documents.PrestamoDocument;
import java.util.ArrayList;
import java.util.List;

public class PrestamoService {
    private final PrestamoDAO prestamoDAO;

    public PrestamoService(){
        this.prestamoDAO = new PrestamoDAO();
    }

    public List<Prestamo> obtenerListaPrestamos(Long matricula){
        List<PrestamoDocument> prestamoDocuments = prestamoDAO.obtenerListaPrestamosPendientesByMatricula(matricula);
        List<Prestamo> prestamos = new ArrayList<>();
        for(PrestamoDocument prestamoDocument : prestamoDocuments){
            prestamos.add(PrestamoAdaptador.toEntity(prestamoDocument));
        }
        return prestamos;
    }


}
