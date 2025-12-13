package controles;

import apiPaypal.PaypalAPI;
import apiPaypal.interfaces.IPaypalAPI;
import java.awt.event.ActionListener;

/**
 * Controlador Interno del Subsistema PayPal.
 * <p>
 * Esta clase encapsula la interacción directa con la librería de terceros `PaypalAPI`.
 * Su función principal es servir de puente para invocar las funcionalidades visuales
 * y de control que ofrece el SDK o librería externa de PayPal.
 */
public class ControlPayPal {

    private final IPaypalAPI api; // Referencia a la interfaz de la librería externa

    /**
     * Constructor.
     * Inicializa la instancia concreta de la API de PayPal.
     * <p>
     * Al igual que en ControlBanco, aquí se crea el acoplamiento con la librería específica,
     * pero se mantiene contenido dentro de esta única clase.
     */
    public ControlPayPal(){
        this.api = new PaypalAPI();
    }

    /**
     * Delega la solicitud de mostrar la interfaz de cobro a la API externa.
     *
     * @param monto El valor numérico a cobrar (tipo primitivo).
     * @param concepto La descripción del cobro.
     * @param listener El oyente que la librería externa notificará cuando el usuario complete o cancele la acción.
     */
    public void mostrarVentanaPago(double monto, String concepto, ActionListener listener) {
        api.mostrarVentanaPago(monto, concepto, listener);
    }

    /**
     * Delega la solicitud de cierre de ventana a la API externa.
     * Libera los recursos visuales utilizados por la librería de PayPal.
     */
    public void cerrarVentana() {
        api.cerrarVentana();
    }
}