package objetosNegocio.bo.pagarAdeudo;

import objetosNegocio.adaptadores.pagarAdeudo.excepciones.AdeudoException;
import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import itson.pagarAdeudo.ClaseDTOI;
import itson.pagarAdeudo.PrestamoDTOI;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import interfaces.IFachadaITSON;
import objetosNegocio.adaptadores.pagarAdeudo.ClaseAdaptador;
import objetosNegocio.adaptadores.pagarAdeudo.PrestamoAdaptador;
import objetosNegocio.bo.pagarAdeudo.interfaces.IAdeudoBO;

import java.util.List;

/**
 * Objeto de Negocio (BO) para la gestión de Adeudos.
 * <p>
 * Esta clase implementa la lógica necesaria para consultar deudas y notificar pagos.
 * Su responsabilidad principal es interactuar con la fachada del sistema institucional (ITSON),
 * adaptar los datos recibidos (DTOI) al formato interno (DTO) y aplicar reglas de negocio
 * básicas, como la validación de existencia de registros.
 */
public class AdeudoBO implements IAdeudoBO {

    private final IFachadaITSON iFachadaITSON; // Referencia a la fachada que conecta con el sistema legado de la universidad

    /**
     * Constructor del Objeto de Negocio.
     * Recibe por inyección la fachada institucional para realizar las peticiones externas.
     *
     * @param iFachadaITSON Instancia de la fachada de comunicación con ITSON.
     */
    public AdeudoBO(IFachadaITSON iFachadaITSON){
        this.iFachadaITSON = iFachadaITSON;
    }

    /**
     * Obtiene y procesa la lista de préstamos pendientes.
     * Solicita los datos externos, los adapta y valida si existen deudas.
     *
     * @param matricula Matrícula del estudiante a consultar.
     * @return Lista de préstamos adaptada al sistema interno.
     * @throws AdeudoException Si el estudiante no tiene ningún préstamo pendiente.
     */
    @Override
    public List<PrestamoDTO> obtenerDetallePrestamo(Long matricula) {
        List<PrestamoDTOI> listaPrestamosI = iFachadaITSON.solicitarListaPrestamos(matricula);
        List<PrestamoDTO> prestamos = PrestamoAdaptador.toDTO(listaPrestamosI);
        if (prestamos.isEmpty()) {
            throw new AdeudoException("El estudiante no tiene préstamos pendientes de pago.");
        }
        return prestamos;
    }

    /**
     * Obtiene y procesa la lista de materias con adeudo.
     * Solicita los datos externos, los adapta y valida si existen adeudos de colegiatura.
     *
     * @param matricula Matrícula del estudiante a consultar.
     * @return Lista de clases adaptada al sistema interno.
     * @throws AdeudoException Si el estudiante no tiene deudas de colegiatura.
     */
    @Override
    public List<ClaseDTO> obtenerDetalleClase(Long matricula) {
        List<ClaseDTOI> listaClasesI = iFachadaITSON.solicitarListaClases(matricula);
        List<ClaseDTO> clases = ClaseAdaptador.toDTO(listaClasesI);
        if (clases.isEmpty()) {
            throw new AdeudoException("El estudiante no tiene adeudos de colegiatura.");
        }
        return clases;
    }

    /**
     * Notifica la liquidación de un pago al sistema institucional.
     * Envía la solicitud confirmada para que la institución actualice sus registros.
     *
     * @param solicitudPagoDTO Objeto con los datos del pago realizado.
     * @throws AdeudoException Si ocurre un error de comunicación o procesamiento al notificar.
     */
    @Override
    public void enviarSolicitudPago(SolicitudPagoDTOI solicitudPagoDTO) {
        try{
            iFachadaITSON.notificarLiquidacion(solicitudPagoDTO);
        }catch (AdeudoException exception){
            throw new AdeudoException("No se pudo enviar la solicitud del pago");
        }

    }
}