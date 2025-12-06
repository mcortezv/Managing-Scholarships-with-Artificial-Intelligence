package datos.repositoryBanco.dao;

import com.mongodb.client.MongoCollection;
import datos.adaptadoresBanco.TransaccionAdaptador;
import datos.configMongoBanco.MongoClienteProvider;
import datos.dominioBanco.Transaccion;
import datos.repositoryBanco.dao.interfaces.ITransaccionDAO;
import datos.repositoryBanco.documents.TransaccionDocument;

public class TransaccionDAO implements ITransaccionDAO {

    private final MongoCollection<TransaccionDocument> col;

    public TransaccionDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("transacciones", TransaccionDocument.class);
    }

    @Override
    public void guardarTransaccion(Transaccion transaccion) {
        try {
            TransaccionDocument doc = TransaccionAdaptador.toDocument(transaccion);
            col.insertOne(doc);
        } catch (Exception e) {
            System.err.println("Error al guardar transacción: " + e.getMessage());
        }
    }
}