package presentacion.apelarResultado.coordinadorNegocioApelarResultado;

import dto.apelacionResultado.ApelacionDTO;
import interfaces.IFachadaApelacion;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.SolicitudDTO;

import java.util.List;

public class CoordinadorNegocioApelarResultado implements ICoordinadorNegocioApelarResultado{
    private IFachadaApelacion fachadaApelacion;
    public CoordinadorNegocioApelarResultado(IFachadaApelacion iFachadaApelacion){
        this.fachadaApelacion = iFachadaApelacion;
    }

    @Override
    public List<SolicitudDTO> obtenerSolicitudesPorEstudiante(EstudianteDTO estudianteDTO) {
        return List.of();
    }

    @Override
    public boolean registrarApelacion(ApelacionDTO apelacionDTO) {
        return false;
    }
}
