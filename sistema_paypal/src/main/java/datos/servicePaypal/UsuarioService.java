package datos.servicePaypal;

import datos.dominioPaypal.Usuario;
import datos.repositoryPaypal.UsuarioDAO;
import datos.repositoryPaypal.interfaces.IUsuarioDAO;

public class UsuarioService {

    private final IUsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario validarCredenciales(String email, String password) {
        return usuarioDAO.buscarPorCredenciales(email, password);
    }

    public boolean verificarFondos(Usuario usuario, double montoA_Pagar) {
        if (usuario == null) return false;
        return usuario.getSaldo() >= montoA_Pagar;
    }

    public boolean descontarSaldo(Usuario usuario, double monto) {
        if (usuario == null) return false;
        double nuevoSaldo = usuario.getSaldo() - monto;
        boolean exito = usuarioDAO.actualizarSaldo(usuario.getEmail(), nuevoSaldo);
        if (exito) {
            usuario.setSaldo(nuevoSaldo);
        }
        return exito;
    }
}