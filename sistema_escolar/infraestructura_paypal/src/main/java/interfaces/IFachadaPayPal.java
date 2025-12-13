package interfaces;


import java.awt.event.ActionListener;

/**
 * Interfaz que define el contrato de comunicación con el subsistema de PayPal.
 * <p>
 * Esta fachada aísla la lógica de presentación y transacción de PayPal del resto de la aplicación.
 * Permite invocar la interfaz gráfica de cobro pasando datos primitivos simples,
 * facilitando la reutilización del componente.
 *
 * @author Escalante, Sebastian
 */
public interface IFachadaPayPal {

    /**
     * Solicita el despliegue de la interfaz gráfica de cobro de PayPal.
     * <p>
     * A diferencia de la fachada bancaria, este método recibe explícitamente el monto
     * y el concepto como tipos primitivos para configurar la vista antes de mostrarla.
     *
     * @param monto La cantidad monetaria a cobrar.
     * @param concepto La descripción del pago (ej. "Pago de Multa Biblioteca").
     * @param listener El oyente (ActionListener) que gestionará la confirmación del pago.
     */
    void mostrarPantallaPago(double monto, String concepto, ActionListener listener);

    /**
     * Ordena el cierre de la ventana de PayPal.
     * Se utiliza para limpiar la pantalla tras completar la transacción o cancelar la operación.
     */
    void cerrarVentanaPaypal();
}