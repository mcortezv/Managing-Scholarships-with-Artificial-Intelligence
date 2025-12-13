package interfaces;
import itson.pagarAdeudo.SolicitudPagoDTOI;

import java.awt.event.ActionListener;

/**
 * Interfaz que define el contrato de comunicación con el subsistema Bancario.
 * <p>
 * Esta fachada abstrae la complejidad de la interfaz gráfica y la lógica de transacción
 * del proveedor de servicios bancarios (mock o real), permitiendo que la aplicación principal
 * delegue el proceso de cobro sin acoplarse a la implementación específica del banco.
 *
 * @author Escalante, Sebastian
 */
public interface IFachadaBanco {

    /**
     * Solicita al subsistema bancario que despliegue su ventana de pago.
     * Permite inyectar un comportamiento desde la aplicación principal para manejar
     * el evento de confirmación de pago.
     *
     * @param listenerBotonPagar El oyente (ActionListener) que se activará cuando
     * el usuario presione el botón de "Pagar" en la ventana del banco.
     */
    void mostrarPantallaPago(ActionListener listenerBotonPagar);

    /**
     * Ejecuta la transacción financiera enviando los datos al sistema bancario.
     *
     * @param solicitud El objeto de transferencia de datos (en formato externo DTOI)
     * que contiene el monto, referencia y datos del estudiante.
     * @return El mismo objeto DTO actualizado con el estado final de la transacción (aprobado/rechazado).
     */
    SolicitudPagoDTOI ejecutarPago(SolicitudPagoDTOI solicitud);

    /**
     * Ordena el cierre y la liberación de recursos de la ventana del banco.
     * Debe llamarse al finalizar el flujo de pago, ya sea por éxito, error o cancelación.
     */
    void cerrarVentana();
}