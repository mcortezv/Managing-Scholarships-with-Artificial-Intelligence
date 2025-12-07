package datos.repositoryPaypal;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import datos.adaptadoresPaypal.UsuarioAdaptador;
import datos.configMongoPaypal.MongoClienteProvider;
import datos.dominioPaypal.Usuario;
import datos.repositoryPaypal.documents.UsuarioDocument;
import datos.repositoryPaypal.interfaces.IUsuarioDAO;
import org.bson.conversions.Bson;

public class UsuarioDAO implements IUsuarioDAO {
    private final MongoCollection<UsuarioDocument> col;

    public UsuarioDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("usuarios", UsuarioDocument.class);
    }

    @Override
    public Usuario buscarPorCredenciales(String email, String password) {
        try {
            Bson filtro = Filters.and(
                    Filters.eq("email", email),
                    Filters.eq("password", password)
            );
            UsuarioDocument doc = col.find(filtro).first();
            return UsuarioAdaptador.toEntity(doc);
        } catch (Exception e) {
            System.err.println("Error al buscar usuario PayPal: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean actualizarSaldo(String email, double nuevoSaldo) {
        try {
            Bson filtro = Filters.eq("email", email);
            Bson update = Updates.set("saldo", nuevoSaldo);

            col.updateOne(filtro, update);
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar saldo PayPal: " + e.getMessage());
            return false;
        }
    }
}
