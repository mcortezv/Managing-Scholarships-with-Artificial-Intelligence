package controles;

import apiBanco.BancoAPI;
import apiBanco.interfaces.IBancoAPI;
import dto.pagarAdeudo.SolicitudPagoDTO;
import excepciones.InfraestructuraBancoException;
import itson.pagarAdeudo.SolicitudPagoDTOI;

import java.awt.event.ActionListener;

/**
 * Controlador Interno del Subsistema Bancario.
 * <p>
 * Esta clase encapsula la interacción directa con la librería de terceros `BancoAPI`.
 * Su responsabilidad es adaptar las llamadas de la aplicación (que usan DTOs complejos)
 * a los métodos primitivos que requiere la API del banco, y procesar las respuestas.
 *
 * @author Escalante, Sebastian
 */
public class ControlBanco {

    IBancoAPI iBancoAPI; // Instancia de la interfaz de la librería externa del banco

    /**
     * Constructor.
     * Inicializa la conexión con la API del banco.
     * <p>
     * <b>Nota de diseño:</b> Crea una instancia concreta de `BancoAPI`.
     */
    public ControlBanco(){
        this.iBancoAPI = new BancoAPI();
    }

    /**
     * Solicita a la API bancaria que muestre su interfaz gráfica.
     *
     * @param listener El oyente que capturará el evento del botón "Pagar" dentro de la ventana del banco.
     */
    public void mostrarVentanaPago(ActionListener listener){
        iBancoAPI.mostrarVentanaPago(listener);
    }

    /**
     * Orquesta el proceso de pago.
     * Extrae la información necesaria del DTO, llama al método interno de transacción
     * y actualiza el estado del objeto de solicitud basándose en el resultado.
     *
     * @param solicitud El DTO con los datos del pago (monto, referencia, etc.).
     * @return El mismo DTO con el atributo `EstatusPago` actualizado ("Pagado" o "Rechazado").
     */
    public SolicitudPagoDTOI ejecutarPago(SolicitudPagoDTOI solicitud){
        double monto = solicitud.getMontoPagado();
        String estatusResultante = procesarTransaccion(monto);
        solicitud.setEstatusPago(estatusResultante);
        return solicitud;
    }

    /**
     * Método auxiliar privado que interactúa con la API bancaria.
     * Define un concepto de pago fijo y convierte la respuesta booleana de la API
     * en un String de estado de negocio.
     *
     * @param monto Cantidad a cobrar.
     * @return "Pagado" si la API devuelve true, "Rechazado" si devuelve false.
     */
    private String procesarTransaccion(double monto){
        String concepto = "Pago desde app de itson";
        boolean exito = iBancoAPI.ejecutarPago(monto, concepto);
        if (exito) {
            return "Pagado";
        } else {
            return "Rechazado";
        }
    }

    /**
     * Cierra la ventana de la API bancaria y maneja errores de infraestructura.
     * Envuelve cualquier excepción genérica de la librería externa en una excepción personalizada.
     *
     * @throws InfraestructuraBancoException Si falla el cierre de la ventana.
     */
    public void cerrarVentana() {
        try{
            iBancoAPI.cerrarVentana();
        }catch (Exception exception){
            throw new InfraestructuraBancoException("Error al cerrar la ventana");
        }

    }
}