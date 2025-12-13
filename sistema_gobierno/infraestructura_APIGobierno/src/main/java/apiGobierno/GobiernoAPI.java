package apiGobierno;
import apiGobierno.controles.GobiernoControl;
import apiGobierno.interfaces.IGobiernoAPI;
import dtoGobierno.EstudianteDTO;
import gobierno.BecasDisponiblesDTOGobierno;
import gobierno.EstudianteDTOGobierno;
import gobierno.RequisitosDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import gobierno.apelacion.ApelacionDTOGobierno;

import java.util.List;

/**
 * The type Gobierno api.
 *
 * @author Cortez, Manuel;
 */
public class GobiernoAPI implements IGobiernoAPI {
    private final GobiernoControl gobiernoControl;

    /**
     * Instantiates a new Gobierno api.
     */
    public GobiernoAPI(){
        this.gobiernoControl = new GobiernoControl();
    }

    @Override
    public boolean guardarSolicitud(SolicitudDTOGobierno solicitudDTO){
        return gobiernoControl.guardarSolicitud(solicitudDTO);
    }

    @Override
    public BecasDisponiblesDTOGobierno obtenerBecas(RequisitosDTOGobierno requisitosDTO) {
        return gobiernoControl.obtenerBecas(requisitosDTO);
    }

    //apelar resultado
    @Override
    public List<SolicitudDTOGobierno> obtenerListaSolicitudesPorEstudiante(EstudianteDTOGobierno estudianteDTO) {
        return gobiernoControl.obtenerListaSolicudesPorEstudiante(estudianteDTO);
    }

    @Override
    public boolean registrarApelacion(ApelacionDTOGobierno apelacionDTOGobierno) {
        return gobiernoControl.registrarApelacion(apelacionDTOGobierno);
    }
}
