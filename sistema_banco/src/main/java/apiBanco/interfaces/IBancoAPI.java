package apiBanco.interfaces;

import java.awt.event.ActionListener;

public interface IBancoAPI {
    void mostrarVentanaPago(ActionListener actionListener);

    boolean ejecutarPago(double monto, String concepto);

    void cerrarVentana();
}
