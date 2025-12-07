package datos.repositoryPaypal.interfaces;

import datos.dominioPaypal.Transaccion;

public interface ITransaccionDAO {
    void guardar(Transaccion transaccion);
}
