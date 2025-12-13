package fachadas;

import controles.ControlPago;
import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import dto.pagarAdeudo.SolicitudPagoDTO;
import excepciones.NegociosSolicitarPagoException;
import interfaces.IFachadaPago;
import objetosNegocio.bo.sesion.SesionUsuario;
import solicitarBeca.EstudianteDTO;

import java.awt.event.ActionListener;
import java.util.List;

/**
 * Implementación concreta de la Fachada de Pagos.
 * <p>
 * Esta clase orquesta la comunicación entre la capa de presentación y la capa de control.
 * Además de delegar las peticiones, su responsabilidad crítica es inyectar la información
 * de la sesión actual (usuario logueado) en las transacciones para asegurar la integridad de los datos.
 */
public class FachadaPago implements IFachadaPago {

    protected ControlPago controlPago; // Controlador que contiene la lógica de negocio real del módulo de pagos

    /**
     * Constructor de la fachada.
     * Recibe la instancia del controlador principal para delegarle las operaciones.
     *
     * @param controlPago Instancia del controlador de pagos.
     */
    public FachadaPago(ControlPago controlPago){
        this.controlPago = controlPago;
    }

    /**
     * Solicita la lista de préstamos bibliotecarios delegando al controlador.
     *
     * @param estudianteDTO Datos del estudiante.
     * @return Lista de préstamos pendientes.
     */
    @Override
    public List<PrestamoDTO> solicitarListaPrestamos(EstudianteDTO estudianteDTO) {
        return controlPago.solicitarListaPrestamos(estudianteDTO);
    }

    /**
     * Solicita la lista de materias con adeudo delegando al controlador.
     *
     * @param estudianteDTO Datos del estudiante.
     * @return Lista de clases pendientes de pago.
     */
    @Override
    public List<ClaseDTO> solicitarListaClases(EstudianteDTO estudianteDTO) {
        return controlPago.solicitarListaClases(estudianteDTO);
    }

    /**
     * Inicia el despliegue de la interfaz gráfica del subsistema bancario.
     *
     * @param listener Oyente para manejar los eventos de la ventana del banco.
     */
    @Override
    public void solicitarVistaDePago(ActionListener listener){
        controlPago.solicitarVistaPago(listener);
    }

    /**
     * Procesa un pago bancario.
     * <p>
     * <b>Importante:</b> Antes de delegar al controlador, este método recupera la matrícula
     * del estudiante logueado desde el Singleton de sesión (`SesionUsuario`) y la inyecta
     * en el DTO. Esto evita suplantación de identidad en la transacción.
     *
     * @param solicitudPagoDTO Datos del pago a procesar.
     * @return El DTO con el resultado de la transacción.
     */
    @Override
    public SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO) {
        solicitudPagoDTO.setIdEstudiante(SesionUsuario.getInstance().getEstudianteLogeado().getMatricula());
        return controlPago.realizarPago(solicitudPagoDTO);
    }

    /**
     * Cierra la interfaz del subsistema bancario.
     */
    @Override
    public void cerrarVentana() {
        controlPago.cerrarVentanaBanco();
    }

    /**
     * Inicia el despliegue de la interfaz gráfica de PayPal.
     *
     * @param monto Cantidad a pagar.
     * @param concepto Descripción del pago.
     * @param listener Oyente para los eventos de la ventana de PayPal.
     */
    @Override
    public void solicitarVistaPaypal(double monto, String concepto, ActionListener listener) {
        controlPago.solicitarVistaPayPal(monto, concepto, listener);
    }

    /**
     * Cierra la interfaz del subsistema PayPal.
     */
    @Override
    public void cerrarVentanaPaypal() {
        controlPago.cerrarVentanaPaypal();
    }

    /**
     * Procesa el resultado de un pago vía PayPal.
     * Al igual que el pago bancario, inyecta la matrícula del usuario de la sesión actual
     * y actualiza el estatus del DTO a "Pagado".
     *
     * @param dto Datos del pago realizado.
     * @return El DTO actualizado y confirmado.
     */
    @Override
    public SolicitudPagoDTO realizarPagoPaypal(SolicitudPagoDTO dto) {
        dto.setIdEstudiante(SesionUsuario.getInstance().getEstudianteLogeado().getMatricula());
        dto.setEstatusPago("Pagado");
        return dto;
    }

    /**
     * Notifica al sistema central que se ha liquidado un adeudo.
     *
     * @param solicitudPagoDTO Datos finales de la transacción completada.
     */
    @Override
    public void notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO) {
        controlPago.notificarLiquidacion(solicitudPagoDTO);
    }
}