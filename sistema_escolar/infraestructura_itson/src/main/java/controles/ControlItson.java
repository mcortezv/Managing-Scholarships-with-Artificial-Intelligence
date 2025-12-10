package controles;

import apiItson.ItsonAPI;
import apiItson.interfaces.IItsonAPI;
import datos.dominioItson.pagarAdeudo.Clase;
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
        return api.soloicitarActividades();
    }

    public GruposResponseDTOItson obtenerGrupos(ActividadDTOItson actividadDTOItson) {
        return api.solicitarGrupos(actividadDTOItson);
    }

    public InscripcionDTOItson inscribirActividad(InscripcionDTOItson inscripcionDTOItson) {
        return api.inscribirActividad(inscripcionDTOItson);
    }
    
    public InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO){
        return api.obtenerInscripciones(estudianteDTO);
    }
    
    public GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcion){
        GrupoResponseDTOItson grupo= api.obtenerGrupoInscrito(inscripcion);
        return grupo;
    }
    
    public BajaDTOItson darBajaActividad(BajaDTOItson baja){
        return api.darBajaActividad(baja);
    }

    public boolean actualizarEstadoInscripcion(String idInscripcion){
        return api.actualizarEstadoInscripcion(idInscripcion);
    }
}
