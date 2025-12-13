package controles;
import apiGobierno.GobiernoAPI;
import apiGobierno.interfaces.IGobiernoAPI;
import gobierno.BecasDisponiblesDTOGobierno;
import gobierno.EstudianteDTOGobierno;
import gobierno.RequisitosDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import gobierno.apelacion.ApelacionDTOGobierno;

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
    public List<SolicitudDTOGobierno> obtenerSolicitudesPorEstudiante(EstudianteDTOGobierno estudianteDTO){
        return gobiernoAPI.obtenerListaSolicitudesPorEstudiante(estudianteDTO);
    }

    public boolean registrarApelacion(ApelacionDTOGobierno apelacionDTO){
        return gobiernoAPI.registrarApelacion(apelacionDTO);
    }
}