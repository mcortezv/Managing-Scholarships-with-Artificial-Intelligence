package interfaces;
import itson.actividades.*;
import itson.pagarAdeudo.ClaseDTOI;
import itson.pagarAdeudo.PrestamoDTOI;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import itson.EstudianteDTOItson;
import itson.HistorialAcademicoDTOItson;
import itson.LoginDTOItson;

import java.util.List;

/**
 * @author Escalante, Sebastian.
 */
public interface IFachadaITSON {

    boolean verificarLogin(LoginDTOItson solicitudLoginDTO);

    EstudianteDTOItson verificarEstudiante(Long matricula);

    HistorialAcademicoDTOItson verificarHistorialAcademcio(Long matricula);

    //pagar adeudo
    List<PrestamoDTOI> solicitarListaPrestamso(Long matricula);
    List<ClaseDTOI> solicitarListaClases(Long matricula);
    boolean notificarLiquidacion(SolicitudPagoDTOI solicitudPagoDTOI);

    //actividades
    public ActividadesDTOItson obtenerActividades();
    public GruposResponseDTOItson otenerGrupos(ActividadDTOItson actividadDTO);
     public boolean buscarEstudiantePorMatricula(InscripcionDTOItson inscripcionDTOItson);
     public InscripcionDTOItson inscribirActividad(InscripcionDTOItson inscripcionDTOItson);
     public InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO);
     public GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcion);
     public BajaDTOItson darBajaActividad(BajaDTOItson baja);
     
     public boolean actualizarEstadoInscripcion(String idInscripcion);
}