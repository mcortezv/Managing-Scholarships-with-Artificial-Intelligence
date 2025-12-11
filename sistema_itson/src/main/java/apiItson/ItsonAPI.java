package apiItson;

import apiItson.controles.ControlItson;
import apiItson.interfaces.IItsonAPI;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import datos.dominioItson.HistorialAcademico;
import datos.dominioItson.pagarAdeudo.Clase;
import datos.dominioItson.pagarAdeudo.Prestamo;
import itson.LoginDTOItson;
import datos.dominioItson.Estudiante;
import itson.actividades.ActividadDTOItson;
import itson.actividades.ActividadesDTOItson;
import itson.EstudianteDTOItson;
import itson.actividades.BajaDTOItson;
import itson.actividades.GrupoResponseDTOItson;
import itson.actividades.GruposResponseDTOItson;
import itson.actividades.InscripcionDTOItson;
import itson.actividades.InscripcionesDTOItson;

import java.util.List;

public class ItsonAPI implements IItsonAPI {
    private final ControlItson controlItson;

    public ItsonAPI() {
        this.controlItson = new ControlItson();
    }

    @Override
    public boolean verificarLogin(LoginDTOItson dto) {
        return controlItson.verificarLogin(dto);
    }

    @Override
    public Estudiante obtenerDatosEstudiante(Long matricula) {
        return controlItson.solicitarDatosEstudiante(matricula);
    }

    @Override
    public HistorialAcademico obtenerHistorialAcademico(Long matricula) {
        return controlItson.obtenerHistorialAcademico(matricula);
    }

    // pagar adeudo
    @Override
    public List<Prestamo> obtenerListaPrestamosBiblioteca(Long matricula) {
        return controlItson.obtenerListaPrestamosBiblioteca(matricula);
    }

    @Override
    public List<Clase> obtenerListaClaseColegiatura(Long matricula) {
        return controlItson.obtenerListaClasesColegiatura(matricula);
    }

    @Override
    public boolean notificarLiquidacion(SolicitudPagoDTOI solicitudPagoDTOI) {
        return controlItson.notificarLiquidacion(solicitudPagoDTOI);
    }

    // actividades
    @Override
    public ActividadesDTOItson soloicitarActividades() {
        return controlItson.solicitarActividades();
    }

    public GruposResponseDTOItson solicitarGrupos(ActividadDTOItson actividad) {
        return controlItson.obtenerGrupos(actividad);
    }

    public InscripcionDTOItson inscribirActividadExterno(InscripcionDTOItson inscripcionDTOItson) {
        return controlItson.inscribirActividadExterno(inscripcionDTOItson);
    }
    
    public GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcion){
        return controlItson.obtenerGrupoInscrito(inscripcion);
    }

    public InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO) {
        return controlItson.obtenerInscripciones(estudianteDTO);
    }
    
    public BajaDTOItson darBajaActividad(BajaDTOItson baja){
        return controlItson.darBajaActividad(baja);
    }
    
    public boolean actualizarEstadoInscripcion(String idInscripcion){
        return controlItson.actualizarEstadoInscripcion(idInscripcion);
    }
}
