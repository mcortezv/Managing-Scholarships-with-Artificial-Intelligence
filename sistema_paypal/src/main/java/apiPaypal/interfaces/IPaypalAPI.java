package apiPaypal.interfaces;

import java.awt.event.ActionListener;

public interface IPaypalAPI {
    void mostrarVentanaPago(double monto, String concepto, ActionListener listenerExito);

    void cerrarVentana();
}
