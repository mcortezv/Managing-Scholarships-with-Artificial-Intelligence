package controles;

import dto.apelacionResultado.ApelacionDTO;
import objetosNegocio.bo.apelarResultado.interfaces.IApelacionBO;
import objetosNegocio.bo.solicitarBeca.intefaces.ISolicitudBO;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.SolicitudDTO;

import java.util.List;

public class ControlApelacion {
    private final ISolicitudBO iSolicitudBO;
    private final IApelacionBO iApelacionBO;

    public ControlApelacion(ISolicitudBO solicitudBO, IApelacionBO iApelacionBO){
        this.iSolicitudBO = solicitudBO;
        this.iApelacionBO = iApelacionBO;
    }

    public List<SolicitudDTO> obtenerSolicitudesPorEstudiante(EstudianteDTO estudianteDTO){
        return iSolicitudBO.obtenerSolicitudesPorEstudiante(estudianteDTO);
    }

    public boolean registrarApelacion(ApelacionDTO apelacionDTO){
        return iApelacionBO.registrarApelacion(apelacionDTO);
    }



}
