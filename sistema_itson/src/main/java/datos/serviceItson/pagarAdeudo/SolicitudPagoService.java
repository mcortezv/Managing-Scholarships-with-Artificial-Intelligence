package datos.serviceItson.pagarAdeudo;

import datos.repositoryItson.daoItson.pagarAdeudo.impl.ClaseDAO;
import datos.repositoryItson.daoItson.pagarAdeudo.impl.PrestamoDAO;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import datos.adaptadoresItson.pagarAdeudo.SolicitudPagoAdaptador;
import datos.repositoryItson.daoItson.pagarAdeudo.impl.SolicitudPagoDAO;
import datos.repositoryItson.documents.pagarAdeudo.SolicitudPagoDocument;

public class SolicitudPagoService {
    private final SolicitudPagoDAO solicitudPagoDAO;
    private final PrestamoDAO prestamoDAO;
    private final ClaseDAO claseDAO;
    public SolicitudPagoService(){
        this.solicitudPagoDAO = new SolicitudPagoDAO();
        this.prestamoDAO = new PrestamoDAO();
        this.claseDAO = new ClaseDAO();
    }

    public boolean notificarLiquidacion(SolicitudPagoDTOI solicitudPagoDTOI){
        SolicitudPagoDocument solicitudPago = SolicitudPagoAdaptador.toDocument(solicitudPagoDTOI);
        boolean guardado = solicitudPagoDAO.guardarSolicitud(solicitudPago);
        if (guardado) {
            actualizarEstatusAdeudo(solicitudPago);
        }
        return guardado;
    }

    private void actualizarEstatusAdeudo(SolicitudPagoDocument solicitud) {
        String tipo = solicitud.getTipoAdeudo();
        Long idEstudiante = solicitud.getIdEstudiante();
        if (tipo == null) {
            System.err.println("Advertencia: El tipo de adeudo es nulo, no se actualizaron registros.");
            return;
        }
        if ("Biblioteca".equalsIgnoreCase(tipo)) {
            prestamoDAO.liquidarPrestamosPorEstudiante(idEstudiante);
        }
        else if ("Colegiatura".equalsIgnoreCase(tipo)) {
            claseDAO.liquidarClasesPorEstudiante(idEstudiante);
        }
    }
}
