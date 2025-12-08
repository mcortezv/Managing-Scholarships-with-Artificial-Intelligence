package presentacion.apelarResultado.coordinadorNegocioApelarResultado;


import dto.apelacionResultado.ApelacionDTO;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.SolicitudDTO;

import java.util.List;

public interface ICoordinadorNegocioApelarResultado {
    List<SolicitudDTO> obtenerSolicitudesPorEstudiante(EstudianteDTO estudianteDTO);
    boolean registrarApelacion(ApelacionDTO apelacionDTO);
}
