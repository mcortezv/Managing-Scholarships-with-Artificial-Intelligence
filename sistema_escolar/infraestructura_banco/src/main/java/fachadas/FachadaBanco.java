package fachadas;
import controles.ControlBanco;
import excepciones.InfraestructuraBancoException;
import interfaces.IFachadaBanco;
import itson.pagarAdeudo.SolicitudPagoDTOI;

import java.awt.event.ActionListener;


/**
 * Implementación concreta de la Fachada para el subsistema Bancario.
 * <p>
 * Esta clase actúa como un intermediario o "wrapper" sobre el controlador interno del banco (ControlBanco).
 * Su propósito es exponer solo las funcionalidades necesarias (mostrar pantalla, cobrar, cerrar)
 * ocultando la complejidad de la implementación interna y manteniendo un bajo acoplamiento.
 *
 * @author Escalante, Sebastian
 */
public class FachadaBanco implements IFachadaBanco {

    private final ControlBanco controlBanco; // Referencia al controlador que maneja la lógica real del banco

    /**
     * Constructor de la fachada bancaria.
     * Recibe la instancia del controlador del banco mediante inyección de dependencias.
     *
     * @param controlBanco Instancia del controlador interno del subsistema bancario.
     */
    public FachadaBanco(ControlBanco controlBanco) {
        this.controlBanco = controlBanco;
    }

    /**
     * Solicita al controlador interno que muestre la interfaz gráfica de pago.
     * Pasa el listener proporcionado por la aplicación principal para manejar el evento de pago.
     *
     * @param listenerBotonPagar Oyente para el botón de acción en la vista del banco.
     */
    @Override
    public void mostrarPantallaPago(ActionListener listenerBotonPagar){
        controlBanco.mostrarVentanaPago(listenerBotonPagar);
    }

    /**
     * Delega la ejecución de la transacción financiera al controlador interno.
     *
     * @param solicitud El DTO con los datos del pago.
     * @return El DTO procesado con la respuesta del banco (aprobado/rechazado).
     */
    @Override
    public SolicitudPagoDTOI ejecutarPago(SolicitudPagoDTOI solicitud){
        return controlBanco.ejecutarPago(solicitud);
    }

    /**
     * Solicita al controlador interno que cierre y limpie la interfaz gráfica.
     */
    @Override
    public void cerrarVentana() {
        controlBanco.cerrarVentana();
    }
}