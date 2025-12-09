package controles;

import adaptadores.solicitarBeca.SolicitudAdaptador;
import dto.apelacionResultado.ApelacionDTO;
import interfaces.apelarResultado.IApelacionBO;
import interfaces.solicitarBeca.ISolicitudBO;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.SolicitudDTO;
import solicitarBeca.dominio.Solicitud;

import java.util.List;

public class ControlApelacion {
    private final ISolicitudBO iSolicitudBO;
    private final IApelacionBO iApelacionBO;

    public ControlApelacion(ISolicitudBO solicitudBO, IApelacionBO iApelacionBO){
        this.iSolicitudBO = solicitudBO;
        this.iApelacionBO = iApelacionBO;
    }

    public List<SolicitudDTO> obtenerSolicitudesPorEstudiante(EstudianteDTO estudianteDTO){
        List<Solicitud> solicitudDTOS = iSolicitudBO.obtenerSolicitudesPorEstudiante(estudianteDTO);
        return SolicitudAdaptador.toDTO(solicitudDTOS);
    }

    public boolean registrarApelacion(ApelacionDTO apelacionDTO){
        return iApelacionBO.registrarApelacion(apelacionDTO);
    }


}
