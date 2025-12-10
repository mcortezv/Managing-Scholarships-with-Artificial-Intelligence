package datos.repositoryItson.daoItson.pagarAdeudo.impl;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import datos.configMongoItson.MongoClienteProvider;
import datos.repositoryItson.daoItson.pagarAdeudo.ISolicitudPagoDAO;
import datos.repositoryItson.daoItson.pagarAdeudo.documents.SolicitudPagoDocument;

public class SolicitudPagoDAO implements ISolicitudPagoDAO {
    private final MongoCollection<SolicitudPagoDocument> col;

    public SolicitudPagoDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("solicitudesPago", SolicitudPagoDocument.class);
    }

    @Override
    public boolean guardarSolicitud(SolicitudPagoDocument solicitudPago) {
        try {
            col.insertOne(solicitudPago);
            return true;
        } catch (MongoException e) {
            return false;
        }
    }

}
