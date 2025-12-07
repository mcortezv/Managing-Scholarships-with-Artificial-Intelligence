package datos.servicePaypal;


import datos.dominioPaypal.Transaccion;
import datos.dominioPaypal.Usuario;
import datos.repositoryPaypal.interfaces.ITransaccionDAO;
import datos.repositoryPaypal.TransaccionDAO;

import java.util.Date;
import java.util.UUID;

public class TransaccionService {
    private final ITransaccionDAO transaccionDAO;

    public TransaccionService(){
        this.transaccionDAO = new TransaccionDAO();
    }

    public void registrarExito(Usuario usuario, double monto, String concepto) {
        crearYGuardar(usuario.getEmail(), monto, concepto, "APROBADA");
    }

    public void registrarFallo(String email, double monto, String concepto, String motivoError) {
        crearYGuardar(email, monto, concepto, "RECHAZADA: " + motivoError);
    }

    private void crearYGuardar(String emailUsuario, double monto, String concepto, String estado) {
        Transaccion t = new Transaccion();
        t.setFolio("PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        t.setUsuarioEmail(emailUsuario);
        t.setMonto(monto);
        t.setDestinatario("ITSON");
        t.setConcepto(concepto);
        t.setFecha(new Date());
        t.setEstado(estado);
        transaccionDAO.guardar(t);
    }
}