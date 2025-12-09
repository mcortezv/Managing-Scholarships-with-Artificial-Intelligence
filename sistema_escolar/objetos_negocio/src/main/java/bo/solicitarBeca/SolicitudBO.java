package bo.solicitarBeca;
import gobierno.SolicitudDTOGobierno;
import bo.solicitarBeca.excepciones.SolicitudInvalidaException;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.repository.dao.interfaces.ISolicitudDAO;
import solicitarBeca.dominio.*;
import solicitarBeca.dominio.enums.EstadoSolicitud;
import interfaces.IFachadaGobierno;
import interfaces.solicitarBeca.ISolicitudBO;
import solicitarBeca.repository.documents.SolicitudDocument;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Cortez, Manuel;
 */
public class SolicitudBO implements ISolicitudBO {
    private final IFachadaGobierno fachadaGobierno;
    private final ISolicitudDAO solicitudDAO;

    public SolicitudBO(IFachadaGobierno fachadaGobierno, ISolicitudDAO solicitudDAO) {
        this.fachadaGobierno = fachadaGobierno;
        this.solicitudDAO = solicitudDAO;
    }

    @Override
    public Solicitud crearSolicitud(Beca beca) {
        Solicitud solicitud = new Solicitud();
        solicitud.setId(ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE));
        solicitud.setBeca(beca);
        solicitud.setFecha(LocalDate.now());
        solicitud.setEstado(EstadoSolicitud.ACTIVA);
        return solicitud;
    }

    @Override
    public void asignarEstudiante(Solicitud solicitud, Estudiante estudiante) throws SolicitudInvalidaException {
        if (solicitud == null) {
            throw new SolicitudInvalidaException("Solicitud nula.");
        }
        if (estudiante == null) {
            throw new SolicitudInvalidaException("Estudiante nulo.");
        }
        solicitud.setEstudiante(estudiante);
    }

    @Override
    public void asignarBeca(Solicitud solicitud, Beca beca) throws SolicitudInvalidaException {
        if (solicitud.getEstudiante() == null){
            throw new SolicitudInvalidaException("Debe agregar primero al estudiante.");
        }
        if (beca == null){
            throw new SolicitudInvalidaException("Beca nula.");
        }
        solicitud.setBeca(beca);
    }

    @Override
    public void asignarDocumentos(Solicitud solicitud, List<Documento> documentos) throws SolicitudInvalidaException {
        if (solicitud.getBeca() == null) {
            throw new SolicitudInvalidaException("Debe asignar la beca antes de documentos.");
        }
        if (documentos == null || documentos.isEmpty()) {
            throw new SolicitudInvalidaException("Debe adjuntar documentos.");
        }
        solicitud.setDocumentos(documentos);
    }

    @Override
    public void asignarHistorial(Solicitud solicitud, HistorialAcademico historialAcademico) throws SolicitudInvalidaException {
        if (solicitud.getDocumentos() == null) {
            throw new SolicitudInvalidaException("Debe agregar documentos primero.");
        }
        if (historialAcademico == null) {
            throw new SolicitudInvalidaException("Historial nulo.");
        }
        solicitud.setHistorialAcademico(historialAcademico);
    }

    @Override
    public void asignarSocioeconomico(Solicitud solicitud, InformacionSocioeconomica informacionSocioeconomica) throws SolicitudInvalidaException {
        if (solicitud.getHistorialAcademico() == null) {
            throw new SolicitudInvalidaException("Debe agregar el historial académico primero.");
        }
        if (informacionSocioeconomica == null) {
            throw new SolicitudInvalidaException("Información socioeconómica nula.");
        }
        solicitud.setInformacionSocioeconomica(informacionSocioeconomica);
    }

    @Override
    public void validarSolicitudCompleta(Solicitud solicitud) throws SolicitudInvalidaException {
        if (solicitud.getEstudiante() == null || solicitud.getBeca() == null || solicitud.getDocumentos() == null ||
                solicitud.getHistorialAcademico() == null || solicitud.getInformacionSocioeconomica() == null) {
            throw new SolicitudInvalidaException("Solicitud incompleta.");
        }
        solicitud.setEstado(EstadoSolicitud.ACTIVA);
    }

    @Override
    public void guardarSolicitud(SolicitudDocument solicitud) throws SolicitudInvalidaException {
        try {
            solicitudDAO.create(solicitud);
        } catch (Exception ex) {
            throw new SolicitudInvalidaException(ex.getMessage());
        }
    }

    @Override
    public void enviarSolicitud(SolicitudDTOGobierno solicitud) throws SolicitudInvalidaException {
        try {
            fachadaGobierno.enviarSolicitud(solicitud);
        } catch (Exception ex) {
            throw new SolicitudInvalidaException(ex.getMessage());
        }
    }

    //apelar resultado
    @Override
    public List<Solicitud> obtenerSolicitudesPorEstudiante(EstudianteDTO estudianteDTO) {
        return List.of();
    }
}
