package fachadas;
import itson.pagarAdeudo.ClaseDTOI;
import itson.pagarAdeudo.PrestamoDTOI;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import controles.ControlItson;
import interfaces.IFachadaITSON;
import itson.actividades.ActividadDTOItson;
import itson.actividades.ActividadesDTOItson;
import itson.EstudianteDTOItson;
import itson.HistorialAcademicoDTOItson;
import itson.LoginDTOItson;
import itson.actividades.BajaDTOItson;
import itson.actividades.GrupoResponseDTOItson;
import itson.actividades.GruposResponseDTOItson;
import itson.actividades.InscripcionDTOItson;
import itson.actividades.InscripcionesDTOItson;
import java.util.List;

/**
 * The type Fachada itson.
 *
 * @author janethcristinagalvanquinonez
 */
public class FachadaItson implements IFachadaITSON {
    private final ControlItson controlItson;

    /**
     * Instantiates a new Fachada itson.
     *
     * @param controlItson the control itson
     */
    public FachadaItson(ControlItson controlItson) {
        this.controlItson = controlItson;
    }

    @Override
    public boolean verificarLogin(LoginDTOItson solicitudLoginDTO) {
        return controlItson.verificarLogin(solicitudLoginDTO);
    }

    @Override
    public EstudianteDTOItson verificarEstudiante(Long matricula) {
        return controlItson.verificarEstudiante(matricula);
    }

    @Override
    public HistorialAcademicoDTOItson verificarHistorialAcademcio(Long matricula) {
        return controlItson.verificarHistorialAcademico(matricula);
    }


    /**
     * TODO ESTO ES DEL CASO PAGAR ADEUDO
     *
     * @param matricula // SEBASTIAN ESCALANTE RAMIREZ
     * @return
     */
    @Override
    public List<PrestamoDTOI> solicitarListaPrestamso(Long matricula) {
        return controlItson.obtenerListaPrestamos(matricula);
    }

    @Override
    public List<ClaseDTOI> solicitarListaClases(Long matricula) {
        return controlItson.obtenerListaClases(matricula);
    }

    @Override
    public boolean notificarLiquidacion(SolicitudPagoDTOI solicitudPagoDTOI) {
        return controlItson.solicitarLiquidacion(solicitudPagoDTOI);
    }


    /**
     * AQUI TERMINA CASO DE USO PAGAR ADEUDO
     */


    /**
     * ESTO ES DEL CASO ACTIVIDADES
     */
    public ActividadesDTOItson obtenerActividades() {
        return controlItson.obtenerActividades();
    }

    @Override
    public GruposResponseDTOItson otenerGrupos(ActividadDTOItson actividadDTO) {
        return controlItson.obtenerGrupos(actividadDTO);
    }

    public InscripcionDTOItson inscribirActividad(InscripcionDTOItson inscripcionDTOItson) {
        return controlItson.inscribirActividad(inscripcionDTOItson);
    }
    
    public InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO){
        return controlItson.obtenerInscripciones(estudianteDTO);
    } 
    
        
    public GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcion){
        return controlItson.obtenerGrupoInscrito(inscripcion);
       
    }
    
    public BajaDTOItson darBajaActividad(BajaDTOItson baja){
        return controlItson.darBajaActividad(baja);

    }
    
    public boolean actualizarEstadoInscripcion(String idInscripcion){
        return controlItson.actualizarEstadoInscripcion(idInscripcion);

    }


}
