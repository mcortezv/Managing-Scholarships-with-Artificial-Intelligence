package datos.repositoryItson.daoItson.pagarAdeudo;

import datos.repositoryItson.daoItson.pagarAdeudo.documents.SolicitudPagoDocument;

public interface ISolicitudPagoDAO {
    boolean guardarSolicitud(SolicitudPagoDocument solicitudPago);
}
