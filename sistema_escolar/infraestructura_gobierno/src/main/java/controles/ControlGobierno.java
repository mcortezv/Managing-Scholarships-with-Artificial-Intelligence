package controles;
import apiGobierno.GobiernoAPI;
import apiGobierno.interfaces.IGobiernoAPI;
import dto.apelacionResultado.ApelacionDTO;
import gobierno.BecasDisponiblesDTOGobierno;
import gobierno.RequisitosDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.SolicitudDTO;

import java.util.List;

/**
 * The type Control gobierno.
 *
 * @author Cortez, Manuel;
 */
public class ControlGobierno {
    /**
     * The Gobierno api.
     */
    IGobiernoAPI gobiernoAPI;

    /**
     * Instantiates a new Control gobierno.
     */
    public ControlGobierno(){
        this.gobiernoAPI= new GobiernoAPI();
    }

    /**
     * Solicitar becas becas disponibles dto gobierno.
     *
     * @param requisitosDTO the requisitos dto
     * @return the becas disponibles dto gobierno
     */
    public BecasDisponiblesDTOGobierno solicitarBecas(RequisitosDTOGobierno requisitosDTO) {
        return gobiernoAPI.obtenerBecas(requisitosDTO);
    }

    /**
     * Enviar solicitud boolean.
     *
     * @param solicitudDTO the solicitud dto
     * @return the boolean
     */
    public boolean enviarSolicitud(SolicitudDTOGobierno solicitudDTO) {
        return gobiernoAPI.guardarSolicitud(solicitudDTO);
    }



    //apelar resultado
    public List<SolicitudDTOGobierno> obtenerSolicitudesPorEstudiante(EstudianteDTO estudianteDTO){
        return List.of();
    }

    public boolean registrarApelacion(ApelacionDTO apelacionDTO){
        return true;
    }
}