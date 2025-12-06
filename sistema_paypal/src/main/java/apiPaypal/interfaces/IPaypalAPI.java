package apiPaypal.interfaces;

import java.awt.event.ActionListener;

public interface IPaypalAPI {
    void mostrarVentanaPago(ActionListener actionListener);
    boolean ejecutarPago(double monto, String concepto);
    void cerrarVentana();
}
