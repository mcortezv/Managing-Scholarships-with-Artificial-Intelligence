package presentacion.pagarAdeudo.coordinadorNegocioPagarAdeudo;

import interfaces.IFachadaPago;
import java.awt.event.ActionListener;
import java.util.List;
import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import dto.pagarAdeudo.SolicitudPagoDTO;
import solicitarBeca.EstudianteDTO;

/**
 * Coordinador de Negocio para el módulo de Pagar Adeudo.
 * Esta clase encapsula la lógica de negocio específica del módulo (como cálculos de totales)
 * y delega las operaciones de infraestructura o persistencia a la Fachada (`IFachadaPago`).
 * Sirve como intermediario entre el Coordinador de Aplicación (Presentación) y el núcleo del sistema.
 */
public class CoordinadorNegocioPagarAdeudo implements ICoordinadorNegocioPagarAdeudo {

    private final IFachadaPago fachadaPago;

    /**
     * Constructor del coordinador de negocio.
     * @param fachadaPago Instancia de la fachada para comunicación con subsistemas.
     */
    public CoordinadorNegocioPagarAdeudo(IFachadaPago fachadaPago) {
        this.fachadaPago = fachadaPago;
    }

    /**
     * Calcula el monto total a pagar sumando los costos de una lista de préstamos.
     * <p>
     * Es una regla de negocio pura: Total = Sumatoria(Costo Prestamo).
     * * @param prestamos Lista de préstamos de libros.
     * @return La suma total de los costos, o 0.0 si la lista es nula o vacía.
     */
    @Override
    public double calcularTotalPrestamos(List<PrestamoDTO> prestamos) {
        double total = 0.0;
        if (prestamos != null) {
            for (PrestamoDTO p : prestamos) {
                total += p.getCosto();
            }
        }
        return total;
    }

    /**
     * Calcula el monto total a pagar sumando los costos de una lista de clases (colegiatura).
     * * @param clases Lista de clases/materias adeudadas.
     * @return La suma total de los costos, o 0.0 si la lista es nula o vacía.
     */
    @Override
    public double calcularTotalClases(List<ClaseDTO> clases) {
        double total = 0.0;
        if (clases != null) {
            for (ClaseDTO c : clases) {
                total += c.getCosto();
            }
        }
        return total;
    }

    /**
     * Solicita al sistema la lista de préstamos pendientes de un estudiante.
     * Delega la consulta a la fachada.
     */
    @Override
    public List<PrestamoDTO> obtenerListaPrestamos(EstudianteDTO estudianteDTO) {
        return fachadaPago.solicitarListaPrestamos(estudianteDTO);
    }

    /**
     * Solicita al sistema la lista de clases pendientes de pago de un estudiante.
     * Delega la consulta a la fachada.
     */
    @Override
    public List<ClaseDTO> obtenerListaClases(EstudianteDTO estudianteDTO) {
        return fachadaPago.solicitarListaClases(estudianteDTO);
    }

    /**
     * Solicita a la fachada mostrar la interfaz del sistema bancario externo.
     * * @param listenerBotonPagarDelBanco Acción a ejecutar cuando el usuario confirme el pago en el banco.
     */
    @Override
    public void mostrarVentanaPago(ActionListener listenerBotonPagarDelBanco) {
        fachadaPago.solicitarVistaDePago(listenerBotonPagarDelBanco);
    }

    /**
     * Procesa la transacción de pago bancario.
     * Envía la solicitud a través de la fachada y recibe el resultado actualizado.
     * * @param solicitudPagoDTO Datos del pago a realizar.
     * @return SolicitudPagoDTO con el estatus actualizado (ej. "Pagado").
     */
    @Override
    public SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO) {
        return fachadaPago.realizarPago(solicitudPagoDTO);
    }

    /**
     * Cierra la ventana del sistema bancario externo a través de la fachada.
     */
    @Override
    public void cerrarVentanaBanco() {
        fachadaPago.cerrarVentana();
    }

    /**
     * Solicita a la fachada mostrar la interfaz de PayPal.
     * * @param monto Cantidad a cobrar.
     * @param concepto Descripción del pago.
     * @param listener Acción a ejecutar tras el éxito en PayPal.
     */
    @Override
    public void mostrarVentanaPaypal(double monto, String concepto, ActionListener listener) {
        fachadaPago.solicitarVistaPaypal(monto, concepto, listener);
    }

    /**
     * Cierra la ventana de PayPal a través de la fachada.
     */
    @Override
    public void cerrarVentanaPaypal() {
        fachadaPago.cerrarVentanaPaypal();
    }

    /**
     * Procesa la transacción de pago vía PayPal.
     * * @param solicitudPagoDTO Datos del pago.
     * @return SolicitudPagoDTO con el resultado de la operación.
     */
    @Override
    public SolicitudPagoDTO realizarPagoPaypal(SolicitudPagoDTO solicitudPagoDTO){
        return fachadaPago.realizarPagoPaypal(solicitudPagoDTO);
    }

    /**
     * Notifica al sistema central (Escolar/Biblioteca) que un adeudo ha sido liquidado
     * para que se actualicen los registros del estudiante.
     * * @param solicitudPagoDTO La solicitud que contiene los detalles del pago completado.
     */
    @Override
    public void notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO) {
        fachadaPago.notificarLiquidacion(solicitudPagoDTO);
    }
}