package datos.serviceBanco;

import datos.dominioBanco.Cuenta;
import datos.excepcionesBanco.BancoException;
import datos.repositoryBanco.dao.CuentaDAO;
import datos.repositoryBanco.dao.interfaces.ICuentaDAO;

public class CuentaService {

    private final ICuentaDAO cuentaDAO;

    public CuentaService() {
        this.cuentaDAO = new CuentaDAO();
    }

    public Cuenta validarYObtenerCuenta(String numero, String cvv, String fechaVencimiento) throws BancoException {
        Cuenta cuenta = cuentaDAO.buscarPorNumeroTarjeta(numero);
        if (cuenta == null) {
            throw new BancoException("TARJETA INEXISTENTE O NÚMERO INCORRECTO");
        }
        if (!cuenta.getCvv().equals(cvv)) {
            throw new BancoException("CÓDIGO DE SEGURIDAD (CVV) INCORRECTO");
        }

        if (!cuenta.getFechaVencimiento().equals(fechaVencimiento)) {
            throw new BancoException("FECHA DE VENCIMIENTO INCORRECTA");
        }
        return cuenta;
    }

    public boolean verificarFondos(Cuenta cuenta, double monto) {
        return cuenta.getSaldo() >= monto;
    }

    public boolean descontarSaldo(Cuenta cuenta, double monto) {
        double nuevoSaldo = cuenta.getSaldo() - monto;
        boolean exito = cuentaDAO.actualizarSaldo(cuenta.getNumeroTarjeta(), nuevoSaldo);
        if (exito) {
            cuenta.setSaldo(nuevoSaldo);
        }

        return exito;
    }
}