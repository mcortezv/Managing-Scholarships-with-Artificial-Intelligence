package apiGobierno.controles;
import datosGobierno.servicesGobierno.BecasService;
import datosGobierno.servicesGobierno.SolicitudService;
import gobierno.BecasDisponiblesDTOGobierno;
import gobierno.EstudianteDTOGobierno;
import gobierno.RequisitosDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import gobierno.apelacion.ApelacionDTOGobierno;

import java.util.List;

/**
 * The type Gobierno control.
 *
 * @author Cortez, Manuel;
 */
public class GobiernoControl {
    private final SolicitudService solicitudService;
    private final BecasService becasService;

    /**
     * Instantiates a new Gobierno control.
     */
    public GobiernoControl() {
        this.solicitudService = new SolicitudService();
        this.becasService= new BecasService();
    }

    /**
     * Guardar solicitud boolean.
     *
     * @param dto the dto
     * @return the boolean
     */
    public boolean guardarSolicitud(SolicitudDTOGobierno dto){
        return solicitudService.guardarSolicitud(dto);
    }

    /**
     * Obtener becas becas disponibles dto gobierno.
     *
     * @param requisitosDTO the requisitos dto
     * @return the becas disponibles dto gobierno
     */
    public BecasDisponiblesDTOGobierno obtenerBecas(RequisitosDTOGobierno requisitosDTO) {
        return becasService.obtenerBecas(requisitosDTO);
    }

    public List<SolicitudDTOGobierno> obtenerListaSolicudesPorEstudiante(EstudianteDTOGobierno estudianteDTO){
        return solicitudService.obtenerListaSolicudesPorEstudiante(estudianteDTO);
    }

    public boolean registrarApelacion(ApelacionDTOGobierno apelacionDTOGobierno){
        return true;
    }
}
