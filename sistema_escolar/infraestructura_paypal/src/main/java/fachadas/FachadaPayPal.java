package fachadas;

import controles.ControlPayPal;
import interfaces.IFachadaPayPal;
import java.awt.event.ActionListener;

/**
 * Implementación concreta de la Fachada para el subsistema de PayPal.
 * <p>
 * Esta clase sirve como intermediario entre la aplicación principal (que usa la interfaz IFachadaPayPal)
 * y el controlador específico del módulo de PayPal (ControlPayPal).
 * Su objetivo es desacoplar la lógica de integración de la implementación interna del módulo de pagos digitales.
 *
 * @author Escalante, Sebastian
 */
public class FachadaPayPal implements IFachadaPayPal {

    private final ControlPayPal controlPayPal; // Controlador encargado de la lógica y gestión de ventanas del subsistema PayPal

    /**
     * Constructor de la fachada.
     * Recibe la instancia del controlador de PayPal para delegarle las operaciones.
     *
     * @param controlPayPal Instancia del controlador interno de PayPal.
     */
    public FachadaPayPal(ControlPayPal controlPayPal){
        this.controlPayPal = controlPayPal;
    }

    /**
     * Despliega la ventana de pago de PayPal.
     * Delega la instrucción al controlador interno pasando los parámetros visuales y de control.
     *
     * @param monto Cantidad a pagar.
     * @param concepto Descripción del pago.
     * @param listener Oyente para manejar la acción de confirmar pago.
     */
    @Override
    public void mostrarPantallaPago(double monto, String concepto, ActionListener listener) {
        controlPayPal.mostrarVentanaPago(monto, concepto, listener);
    }

    /**
     * Cierra la ventana de PayPal.
     * Delega la instrucción al controlador para liberar los recursos gráficos.
     */
    @Override
    public void cerrarVentanaPaypal() {
        controlPayPal.cerrarVentana();
    }
}