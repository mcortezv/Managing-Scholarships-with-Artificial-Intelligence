package presentacion.pagarAdeudo.coordinadorNegocioPagarAdeudo;

import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import dto.pagarAdeudo.SolicitudPagoDTO;
import solicitarBeca.EstudianteDTO;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Interfaz que define las reglas de negocio y operaciones para el módulo de Pagar Adeudo.
 * Establece el contrato que debe cumplir el Coordinador de Negocio. Agrupa tres tipos de funcionalidades:
 * 1. Gestión interna de datos (cálculos y consultas al sistema escolar/biblioteca).
 * 2. Integración con el sistema bancario externo.
 * 3. Integración con la pasarela de pagos PayPal.
 */
public interface ICoordinadorNegocioPagarAdeudo {


    /**
     * Calcula la suma total de los costos de una lista de préstamos de libros.
     *
     * @param prestamos Lista de objetos PrestamoDTO.
     * @return El monto total acumulado.
     */
    double calcularTotalPrestamos(List<PrestamoDTO> prestamos);

    /**
     * Calcula la suma total de los costos de una lista de clases (colegiaturas).
     *
     * @param clases Lista de objetos ClaseDTO.
     * @return El monto total acumulado.
     */
    double calcularTotalClases(List<ClaseDTO> clases);

    /**
     * Obtiene la lista de préstamos de libros pendientes asociados a un estudiante.
     *
     * @param estudianteDTO Datos del estudiante a consultar.
     * @return Lista de préstamos recuperados del sistema.
     */
    List<PrestamoDTO> obtenerListaPrestamos(EstudianteDTO estudianteDTO);

    /**
     * Obtiene la lista de materias o clases con adeudo asociadas a un estudiante.
     *
     * @param estudianteDTO Datos del estudiante a consultar.
     * @return Lista de clases recuperadas del sistema.
     */
    List<ClaseDTO> obtenerListaClases(EstudianteDTO estudianteDTO);


    /**
     * Solicita la visualización de la interfaz externa del Banco.
     *
     * @param listener Acción que se ejecutará cuando el usuario confirme el pago en la ventana del banco.
     */
    void mostrarVentanaPago(ActionListener listener);

    /**
     * Ejecuta la lógica de negocio para procesar un pago bancario.
     *
     * @param solicitudPagoDTO DTO con la información del pago (monto, método, estado).
     * @return El DTO actualizado con el resultado de la transacción.
     */
    SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO);

    /**
     * Cierra la ventana externa del sistema bancario.
     */
    void cerrarVentanaBanco();


    /**
     * Solicita la visualización de la interfaz de inicio de sesión y pago de PayPal.
     *
     * @param monto Cantidad monetaria a cobrar.
     * @param concepto Descripción breve del pago (ej. "Pago Biblioteca").
     * @param listener Acción que se ejecutará cuando PayPal confirme el éxito de la operación.
     */
    void mostrarVentanaPaypal(double monto, String concepto, ActionListener listener);

    /**
     * Cierra la ventana externa de PayPal.
     */
    void cerrarVentanaPaypal();

    /**
     * Ejecuta la lógica de negocio para procesar un pago vía PayPal.
     *
     * @param solicitudPagoDTO DTO con la información del pago.
     * @return El DTO actualizado con el resultado de la transacción.
     */
    SolicitudPagoDTO realizarPagoPaypal(SolicitudPagoDTO solicitudPagoDTO);


    /**
     * Notifica a los sistemas centrales (Biblioteca o Control Escolar) que un adeudo ha sido pagado.
     * Esto es necesario para liberar al estudiante de la deuda en la base de datos principal.
     *
     * @param solicitudPagoDTO La solicitud con el estatus "Pagado".
     */
    void notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO);
}