package controles;
import apiItson.ItsonAPI;
import apiItson.interfaces.IItsonAPI;
import excepciones.InfraestructuraItsonException;
import itson.actividades.*;
import itson.pagarAdeudo.ClaseDTOI;
import itson.pagarAdeudo.PrestamoDTOI;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import datos.adaptadoresItson.EstudianteAdaptador;
import datos.adaptadoresItson.HistorialAcademicoAdaptador;
import datos.adaptadoresItson.pagarAdeudo.ClaseAdaptador;
import datos.adaptadoresItson.pagarAdeudo.PrestamoAdaptador;
import datos.dominioItson.Estudiante;
import itson.EstudianteDTOItson;
import itson.HistorialAcademicoDTOItson;
import itson.LoginDTOItson;
import java.util.ArrayList;
import java.util.List;


public class ControlItson {

    IItsonAPI api;

    public ControlItson() {
        this.api = new ItsonAPI();
    }

    public boolean verificarLogin(LoginDTOItson loginDTO) {
        LoginDTOItson dtoInfra = new LoginDTOItson();
        dtoInfra.setUsuario(loginDTO.getUsuario());
        dtoInfra.setContrasenia(loginDTO.getContrasenia());
        return api.verificarLogin(dtoInfra);
    }

    public EstudianteDTOItson verificarEstudiante(Long matricula) {
        Estudiante estudiante = api.obtenerDatosEstudiante(matricula);
        return EstudianteAdaptador.toResponseDTO(estudiante);
    }

    public HistorialAcademicoDTOItson verificarHistorialAcademico(Long matricula) {
        return HistorialAcademicoAdaptador.toDTOItson(api.obtenerHistorialAcademico(matricula));
    }

    //Caso de uso Pagar adeudo
    public List<PrestamoDTOI> obtenerListaPrestamos(Long matricula) throws InfraestructuraItsonException {
        List<PrestamoDTOI> prestamosDtoi = PrestamoAdaptador.toDtoItson(api.obtenerListaPrestamosBiblioteca(matricula));
        if(prestamosDtoi.isEmpty()){
            return new ArrayList<>();
        }
        return prestamosDtoi;
    }

    public List<ClaseDTOI> obtenerListaClases(Long matricula) throws InfraestructuraItsonException{
        List<ClaseDTOI> claseDTOIS = ClaseAdaptador.toDtoItson(api.obtenerListaClaseColegiatura(matricula));
        if(claseDTOIS.isEmpty()){
            return new ArrayList<>();
        }
        return claseDTOIS;
    }

    public boolean solicitarLiquidacion(SolicitudPagoDTOI solicitudPagoDTOI) throws InfraestructuraItsonException{
        return api.notificarLiquidacion(solicitudPagoDTOI);
    }

    // caso de uso actividades
    public ActividadesDTOItson obtenerActividades() {
        try{
         return api.soloicitarActividades();
        } catch(Exception e){
            throw new InfraestructuraItsonException("Error desconocido por parte de la API al obtener actividades"+e.getMessage());
        }
    }
    
    public ActividadDTOItson obtenerActividaddPorNombre(ActividadDTOItson actividad){
        try{
          return api.obtenerActividaddPorNombre(actividad);
        } catch(Exception e){
            throw new InfraestructuraItsonException("Error desconocido por parte de la API al obtener actividades por nombre "+e.getMessage());
        }
    }

    public GruposResponseDTOItson obtenerGrupos(ActividadDTOItson actividadDTOItson) {
        try{
          return api.solicitarGrupos(actividadDTOItson);
        } catch(Exception e){
            throw new InfraestructuraItsonException("Error desconocido por parte de la API al obtener los grupos "+e.getMessage());
        }
    }

    public InscripcionDTOItson inscribirActividadExterno(InscripcionDTOItson inscripcionDTOItson) {
        try{
            return api.inscribirActividadExterno(inscripcionDTOItson);
        } catch(Exception e){
            throw new InfraestructuraItsonException("Error desconocido por parte de la API al inscribir actvidad en externo "+e.getMessage());
        }
    }
    
    public InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO){
        try{
           return api.obtenerInscripciones(estudianteDTO);
         } catch(Exception e){
             throw new InfraestructuraItsonException("Error desconocido por parte de la API al obtener las inscripciones "+e.getMessage());
         }
    }
    
    public GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcion){
        try{
            GrupoResponseDTOItson grupo= api.obtenerGrupoInscrito(inscripcion);
            return grupo;
        } catch(Exception e){
            throw new InfraestructuraItsonException("Error desconocido por parte de la API al obtener grupo inscrito "+e.getMessage());
        }
    }
    
    public BajaDTOItson darBajaActividad(BajaDTOItson baja){
        try{
           return api.darBajaActividad(baja);
        } catch(Exception e){
            throw new InfraestructuraItsonException("Error desconocido por parte de la API al dar de baja actividad  "+e.getMessage());
        }
    }

//    public boolean actualizarEstadoInscripcion(String idInscripcion){
//        return api.actualizarEstadoInscripcion(idInscripcion);
//    }
}
