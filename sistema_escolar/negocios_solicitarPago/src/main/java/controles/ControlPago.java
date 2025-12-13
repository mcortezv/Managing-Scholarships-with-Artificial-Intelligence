package controles;

import java.awt.event.ActionListener;
import java.util.List;
import adaptadoresPagoAdeudo.SolicitudPagoAdapdator;
import excepciones.NegociosSolicitarPagoException;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import interfaces.*;
import objetosNegocio.bo.pagarAdeudo.interfaces.IAdeudoBO;
import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import dto.pagarAdeudo.SolicitudPagoDTO;
import solicitarBeca.EstudianteDTO;

/**
 * Controlador principal del caso de uso "Realizar Pago".
 * <p>
 * Esta clase centraliza la coordinación entre la lógica de negocio interna de la institución
 * (IAdeudoBO) y las fachadas de los sistemas de pago externos (Banco y PayPal).
 * <p>
 * Su responsabilidad clave es la orquestación y la adaptación de datos: convierte los objetos
 * de transferencia de datos (DTOs) internos de la aplicación a los formatos requeridos
 * por las librerías externas mediante el uso de adaptadores.
 */
public class ControlPago {

    protected final IAdeudoBO iAdeudoBO; // Referencia al objeto de negocio que gestiona las deudas (Biblioteca/Colegiatura)
    protected final IFachadaBanco iFachadaBanco; // Fachada para la comunicación con el subsistema bancario
    protected final IFachadaPayPal iFachadaPayPal; // Fachada para la comunicación con el subsistema PayPal

    /**
     * Constructor del controlador.
     * Recibe las dependencias necesarias mediante inyección para desacoplar la implementación.
     *
     * @param adeudoBO Objeto de negocio para consultas a la base de datos de la institución.
     * @param iFachadaBanco Fachada del sistema bancario.
     * @param fachadaPayPal Fachada del sistema PayPal.
     */
    public ControlPago(IAdeudoBO adeudoBO, IFachadaBanco iFachadaBanco, IFachadaPayPal fachadaPayPal){
        this.iAdeudoBO = adeudoBO;
        this.iFachadaPayPal = fachadaPayPal;
        this.iFachadaBanco = iFachadaBanco;
    }

    /**
     * Recupera los préstamos de biblioteca pendientes de un estudiante.
     * Extrae la matrícula del DTO y delega la consulta al Business Object (BO).
     *
     * @param estudianteDTO DTO con la información del estudiante.
     * @return Lista de préstamos con adeudo.
     */
    public List<PrestamoDTO> solicitarListaPrestamos(EstudianteDTO estudianteDTO) {
        Long matricula = estudianteDTO.getMatricula();
        return iAdeudoBO.obtenerDetallePrestamo(matricula);
    }

    /**
     * Recupera las clases/materias con adeudo de colegiatura.
     *
     * @param estudianteDTO DTO con la información del estudiante.
     * @return Lista de clases con pago pendiente.
     */
    public List<ClaseDTO> solicitarListaClases(EstudianteDTO estudianteDTO) {
        Long matricula = estudianteDTO.getMatricula();
        return iAdeudoBO.obtenerDetalleClase(matricula);
    }

    /**
     * Solicita al sistema bancario que muestre su interfaz de pago.
     * Captura y re-lanza excepciones de negocio en caso de fallo al abrir la ventana.
     *
     * @param listenerBotonPagar Oyente que gestionará la acción del usuario en la ventana del banco.
     * @throws NegociosSolicitarPagoException Si ocurre un error en la fachada bancaria.
     */
    public void solicitarVistaPago(ActionListener listenerBotonPagar) throws NegociosSolicitarPagoException{
        try{
            iFachadaBanco.mostrarPantallaPago(listenerBotonPagar);
        }catch (NegociosSolicitarPagoException exception){
            throw new NegociosSolicitarPagoException("Error al solicitar la vista de pago");
        }
    }

    /**
     * Ejecuta el proceso de pago bancario.
     * <p>
     * Este método utiliza el patrón <b>Adapter</b>. Convierte el `SolicitudPagoDTO` (interno de la app)
     * a `SolicitudPagoDTOI` (formato esperado por el subsistema externo) antes de enviarlo,
     * y realiza la conversión inversa al recibir la respuesta.
     *
     * @param solicitudPagoDTO Datos del pago en formato interno.
     * @return Datos del resultado del pago en formato interno.
     */
    public SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO){
        try{
            SolicitudPagoDTOI solicitudPagoDTOI = iFachadaBanco.ejecutarPago(SolicitudPagoAdapdator.toDTOI(solicitudPagoDTO));
            return SolicitudPagoAdapdator.toDTO(solicitudPagoDTOI);
        }catch (NegociosSolicitarPagoException exception){
            throw new NegociosSolicitarPagoException("Error al realizar el pago con el banco");
        }

    }

    /**
     * Cierra la ventana del sistema bancario.
     */
    public void cerrarVentanaBanco() {
        try{
            iFachadaBanco.cerrarVentana();
        }catch (NegociosSolicitarPagoException exception){
            throw new NegociosSolicitarPagoException("Error al intentar solicitar cerrar ventana del banco");
        }

    }

    /**
     * Solicita al sistema PayPal que muestre su interfaz de pago.
     *
     * @param monto Cantidad a cobrar.
     * @param concepto Descripción del cobro.
     * @param listener Oyente para las acciones en la ventana de PayPal.
     */
    public void solicitarVistaPayPal(double monto, String concepto, ActionListener listener){
        try{
            iFachadaPayPal.mostrarPantallaPago(monto, concepto, listener);
        }catch (NegociosSolicitarPagoException exception){
            throw new NegociosSolicitarPagoException("Error al solictar vista de paypal");
        }

    }

    /**
     * Cierra la ventana del sistema PayPal.
     */
    public void cerrarVentanaPaypal(){
        try{
            iFachadaPayPal.cerrarVentanaPaypal();
        }catch (NegociosSolicitarPagoException exception){
            throw new NegociosSolicitarPagoException("Error al intentar solicitar cerrar ventana de paypal");
        }

    }

    /**
     * Notifica al sistema interno (ITSON) que un pago se ha completado exitosamente.
     * Adapta el DTO interno al formato requerido por el Business Object para la persistencia.
     *
     * @param solicitudPagoDTO DTO con la confirmación del pago.
     */
    public void notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO){
        SolicitudPagoDTOI solicitudPagoDTOI = SolicitudPagoAdapdator.toDTOI(solicitudPagoDTO);
        iAdeudoBO.enviarSolicitudPago(solicitudPagoDTOI);
    }
}