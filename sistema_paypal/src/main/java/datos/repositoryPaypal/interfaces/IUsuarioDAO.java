package datos.repositoryPaypal.interfaces;

import datos.dominioPaypal.Usuario;

public interface IUsuarioDAO {
    Usuario buscarPorCredenciales(String email, String password);
    boolean actualizarSaldo(String email, double nuevoSaldo);
}