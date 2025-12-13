package fachadas;

import controles.ControlApelacion;
import dto.apelacionResultado.ApelacionDTO;
import interfaces.IFachadaApelacion;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.SolicitudDTO;

import java.util.List;

public class FachadaApelacion implements IFachadaApelacion {
    private final ControlApelacion controlApelacion;

    public FachadaApelacion(ControlApelacion controlApelacion) {
        this.controlApelacion = controlApelacion;
    }

    @Override
    public List<SolicitudDTO> obtenerSolicitudesPorEstudiante(EstudianteDTO estudianteDTO) {
        return controlApelacion.obtenerSolicitudesPorEstudiante(estudianteDTO);
    }

    @Override
    public boolean registrarApelacion(ApelacionDTO apelacionDTO) {
        return controlApelacion.registrarApelacion(apelacionDTO);
    }
}
