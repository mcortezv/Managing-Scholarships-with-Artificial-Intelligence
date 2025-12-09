package objetosNegocio.bo.solicitarBeca.intefaces;
import objetosNegocio.bo.solicitarBeca.excepciones.SolicitudInvalidaException;
import solicitarBeca.BecaDTO;
import solicitarBeca.SolicitudDTO;

/**
 * The interface Solicitud bo.
 *
 * @author Cortez, Manuel;
 */
public interface ISolicitudBO {

    /**
     * Crear solicitud solicitud dto.
     *
     * @param beca the beca
     * @return the solicitud dto
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    SolicitudDTO crearSolicitud(BecaDTO beca) throws SolicitudInvalidaException;

    /**
     * Validar solicitud completa.
     *
     * @param solicitud the solicitud
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    void validarSolicitudCompleta(SolicitudDTO solicitud) throws SolicitudInvalidaException;

    /**
     * Guardar solicitud.
     *
     * @param solicitud the solicitud
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    void guardarSolicitud(SolicitudDTO solicitud) throws SolicitudInvalidaException;

    /**
     * Enviar solicitud.
     *
     * @param solicitud the solicitud
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    void enviarSolicitud(SolicitudDTO solicitud) throws SolicitudInvalidaException;
}
