package objetosNegocio.bo.solicitarBeca;
import objetosNegocio.adaptadores.solicitarBeca.BecaAdaptador;
import objetosNegocio.adaptadores.solicitarBeca.SolicitudAdaptador;
import objetosNegocio.bo.solicitarBeca.excepciones.SolicitudInvalidaException;
import solicitarBeca.BecaDTO;
import solicitarBeca.SolicitudDTO;
import solicitarBeca.repository.dao.interfaces.ISolicitudDAO;
import solicitarBeca.dominio.*;
import solicitarBeca.dominio.enums.EstadoSolicitud;
import interfaces.IFachadaGobierno;
import objetosNegocio.bo.solicitarBeca.intefaces.ISolicitudBO;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The type Solicitud bo.
 *
 * @author Cortez, Manuel;
 */
public class SolicitudBO implements ISolicitudBO {
    private final IFachadaGobierno fachadaGobierno;
    private final ISolicitudDAO solicitudDAO;

    /**
     * Instantiates a new Solicitud bo.
     *
     * @param fachadaGobierno the fachada gobierno
     * @param solicitudDAO    the solicitud dao
     */
    public SolicitudBO(IFachadaGobierno fachadaGobierno, ISolicitudDAO solicitudDAO) {
        this.fachadaGobierno = fachadaGobierno;
        this.solicitudDAO = solicitudDAO;
    }

    @Override
    public SolicitudDTO crearSolicitud(BecaDTO beca) {
        Solicitud solicitud = new Solicitud();
        solicitud.setId(ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE));
        solicitud.setBeca(BecaAdaptador.toEntity(beca));
        solicitud.setFecha(LocalDate.now());
        solicitud.setEstado(EstadoSolicitud.ACTIVA);
        return SolicitudAdaptador.toDTO(solicitud);
    }

    @Override
    public void validarSolicitudCompleta(SolicitudDTO solicitud) throws SolicitudInvalidaException {
        if (solicitud.getEstudiante() == null || solicitud.getBeca() == null || solicitud.getDocumentos() == null ||
                solicitud.getHistorialAcademico() == null || solicitud.getInformacionSocioeconomica() == null) {
            throw new SolicitudInvalidaException("Solicitud incompleta.");
        }
        solicitud.setEstado(EstadoSolicitud.ACTIVA.toString());
    }

    @Override
    public void guardarSolicitud(SolicitudDTO solicitud) throws SolicitudInvalidaException {
        try {
            solicitudDAO.create(solicitud);
        } catch (Exception ex) {
            throw new SolicitudInvalidaException(ex.getMessage());
        }
    }

    @Override
    public void enviarSolicitud(SolicitudDTO solicitud) throws SolicitudInvalidaException {
        try {
            Solicitud entity = SolicitudAdaptador.toEntity(solicitud);
            fachadaGobierno.enviarSolicitud(SolicitudAdaptador.toDTOGobierno(entity));
        } catch (Exception ex) {
            throw new SolicitudInvalidaException(ex.getMessage());
        }
    }
}
