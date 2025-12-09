package apiItson.controles;
import itson.pagarAdeudo.*;
import datos.adaptadoresItson.actividades.GrupoAdaptador;

import datos.dominioItson.HistorialAcademico;
import datos.dominioItson.pagarAdeudo.Clase;
import datos.dominioItson.pagarAdeudo.Prestamo;
import datos.serviceItson.ActividadService;
import datos.serviceItson.HistorialService;
import datos.serviceItson.pagarAdeudo.ClaseService;
import datos.serviceItson.pagarAdeudo.PrestamoService;
import datos.serviceItson.pagarAdeudo.SolicitudPagoService;
import itson.LoginDTOItson;
import datos.dominioItson.Estudiante;

import datos.serviceItson.EstudianteService;
import datos.serviceItson.GrupoService;
import datos.serviceItson.actividades.InscripcionService;
import itson.ActividadDTOItson;
import itson.ActividadesDTOItson;
import itson.EstudianteDTOItson;
import itson.actividades.BajaDTOItson;
import itson.actividades.GrupoResponseDTOItson;
import itson.actividades.GruposResponseDTOItson;
import itson.actividades.InscripcionDTOItson;
import itson.actividades.InscripcionesDTOItson;

import java.util.List;


public class ControlItson {
    private final HistorialService historialService;
    private final EstudianteService estudianteService;
    // pagar adeudo
    private final PrestamoService prestamoService;
    private final ClaseService claseService;
    private final SolicitudPagoService solicitudPagoService;
    //actividades
    private final ActividadService actividadService;
    private final GrupoService grupoService;
    private final InscripcionService inscripcionService;

    public ControlItson() {
        this.historialService = new HistorialService();
        this.estudianteService = new EstudianteService();

        //actividades
        this.actividadService = new ActividadService();
        this.grupoService= new GrupoService();
        this.inscripcionService= new InscripcionService();
        //pagar adeudo
        this.prestamoService = new PrestamoService();
        this.claseService = new ClaseService();
        this.solicitudPagoService = new SolicitudPagoService();
    }

    public boolean verificarLogin(LoginDTOItson dto){
        return estudianteService.verificarLogin(dto);
    }
    
    public Estudiante solicitarDatosEstudiante(Long matricula){
        return estudianteService.solicitarDatosEstudiante(matricula);
    }

    public HistorialAcademico obtenerHistorialAcademico(Long matricula){
        return historialService.obtenerHistorialAcademico(matricula);
    }




    //pagar adeudo
    public List<Prestamo> obtenerListaPrestamosBiblioteca(Long matricula){
        return prestamoService.obtenerListaPrestamos(matricula);
    }

    public List<Clase> obtenerListaClasesColegiatura(Long matricula){
        return claseService.obtenerListaClases(matricula);
    }

    public boolean notificarLiquidacion(SolicitudPagoDTOI solicitudPagoDTOI){
        return solicitudPagoService.notificarLiquidacion(solicitudPagoDTOI);
    }

    
    //actividades
    public ActividadesDTOItson solicitarActividades(){
        return actividadService.obtenerActividades();
    }
    
    public GruposResponseDTOItson obtenerGrupos(ActividadDTOItson actividad){
      return grupoService.obtenerGrupos(actividad);
    }

    public InscripcionDTOItson inscribirActividad(InscripcionDTOItson inscripcionDTOItson){
        return inscripcionService.inscribirActividad(inscripcionDTOItson);
    }
    
    public InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO){
        return inscripcionService.obtenerInscripciones(estudianteDTO);
    }
    
    public GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcion){
        return grupoService.obtenerGrupoInscrito(inscripcion);
    }
    
    public BajaDTOItson darBajaActividad(BajaDTOItson baja){
        return inscripcionService.darBajaActividad(baja);
    }
    
    public boolean actualizarEstadoInscripcion(String idInscripcion){
        return inscripcionService.actualizarEstadoInscripcion(idInscripcion);
    }
}