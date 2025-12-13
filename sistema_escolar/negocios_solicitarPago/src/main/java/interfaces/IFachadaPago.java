package interfaces;

import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import dto.pagarAdeudo.SolicitudPagoDTO;
import excepciones.NegociosSolicitarPagoException;
import solicitarBeca.EstudianteDTO;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Interfaz que define el contrato para la Fachada de Pagos.
 * <p>
 * Esta interfaz actúa como un punto de entrada único para gestionar tres subsistemas distintos:
 * 1. ITSON: Recuperación de adeudos (biblioteca y colegiatura) y notificación de pagos.
 * 2. Banco: Gestión de la interfaz y transacciones bancarias.
 * 3. PayPal: Gestión de la interfaz y transacciones vía PayPal.
 * <p>
 * Su propósito es desacoplar la capa de presentación de la complejidad de los servicios externos e internos.
 */
public interface IFachadaPago {

    /**
     * Solicita al sistema universitario la lista de préstamos de libros pendientes.
     *
     * @param estudianteDTO Información del estudiante que consulta.
     * @return Una lista de objetos PrestamoDTO con los detalles de los libros y multas.
     */
    List<PrestamoDTO> solicitarListaPrestamos(EstudianteDTO estudianteDTO);

    /**
     * Solicita al sistema universitario la lista de clases/materias con adeudo de colegiatura.
     *
     * @param estudianteDTO Información del estudiante que consulta.
     * @return Una lista de objetos ClaseDTO con los detalles de las materias y costos.
     */
    List<ClaseDTO> solicitarListaClases(EstudianteDTO estudianteDTO);

    /**
     * Notifica al sistema universitario que un adeudo ha sido liquidado exitosamente.
     * Actualiza el estado de la cuenta del estudiante en la base de datos de la institución.
     *
     * @param solicitudPagoDTO El objeto con los detalles del pago realizado y aprobado.
     */
    void notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO);


    /**
     * Solicita la visualización de la interfaz de pago bancaria.
     *
     * @param listener El oyente (ActionListener) que manejará los eventos de la ventana del banco.
     */
    void solicitarVistaDePago(ActionListener listener);

    /**
     * Ejecuta la transacción de pago a través del subsistema bancario.
     *
     * @param solicitudPagoDTO Los datos necesarios para procesar el cargo.
     * @return Un objeto SolicitudPagoDTO actualizado con el estado de la transacción (aprobado/rechazado).
     */
    SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO);

    /**
     * Ordena el cierre de la ventana de pago bancaria.
     * Se utiliza para limpiar la interfaz una vez que la transacción ha concluido o se ha cancelado.
     */
    void cerrarVentana();


    /**
     * Solicita la visualización de la interfaz de pago de PayPal.
     *
     * @param monto El monto total a cobrar.
     * @param concepto El concepto o descripción del pago.
     * @param listener El oyente que manejará los eventos de la ventana de PayPal.
     */
    void solicitarVistaPaypal(double monto, String concepto, ActionListener listener);

    /**
     * Ordena el cierre de la ventana de PayPal.
     */
    void cerrarVentanaPaypal();

    /**
     * Ejecuta la transacción de pago a través del subsistema de PayPal.
     *
     * @param dto Los datos necesarios para procesar el cobro digital.
     * @return Un objeto SolicitudPagoDTO actualizado con el resultado de la operación.
     */
    SolicitudPagoDTO realizarPagoPaypal(SolicitudPagoDTO dto);
}