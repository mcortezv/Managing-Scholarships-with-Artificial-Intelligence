package datos.repositoryBanco.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import datos.configMongoBanco.MongoClienteProvider;
import datos.dominioBanco.Cuenta;
import datos.excepcionesBanco.BancoException;
import datos.repositoryBanco.dao.interfaces.ICuentaDAO;

public class CuentaDAO implements ICuentaDAO {

    private final MongoCollection<Cuenta> col;

    public CuentaDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("cuentas",Cuenta.class);
    }

    @Override
    public Cuenta buscarPorNumeroTarjeta(String numeroTarjeta) {
        try {
            return col.find(Filters.eq("numeroTarjeta", numeroTarjeta)).first();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BancoException("Error al recuperar la cuenta");
        }
    }

    @Override
    public boolean actualizarSaldo(String numeroTarjeta, double nuevoSaldo) {
        try {
            col.updateOne(
                    Filters.eq("numeroTarjeta", numeroTarjeta),
                    Updates.set("saldo", nuevoSaldo)
            );
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar saldo: " + e.getMessage());
            return false;
        }
    }
}