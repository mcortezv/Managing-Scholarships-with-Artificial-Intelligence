package datos.repositoryPaypal;

import com.mongodb.client.MongoCollection;
import datos.adaptadoresPaypal.TransaccionAdaptador;
import datos.configMongoPaypal.MongoClienteProvider;
import datos.dominioPaypal.Transaccion;
import datos.repositoryPaypal.documents.TransaccionDocument;
import datos.repositoryPaypal.interfaces.ITransaccionDAO;

public class TransaccionDAO implements ITransaccionDAO {
    private final MongoCollection<TransaccionDocument> col;

    public TransaccionDAO(){
        this.col = MongoClienteProvider.INSTANCE.getCollection("transacciones", TransaccionDocument.class);
    }
    @Override
    public void guardar(Transaccion transaccion) {
        try {
            TransaccionDocument doc = TransaccionAdaptador.toDocument(transaccion);
            col.insertOne(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
