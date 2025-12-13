package apiItson.controles;
import itson.pagarAdeudo.*;

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
import itson.actividades.ActividadDTOItson;
import itson.actividades.ActividadesDTOItson;
import itson.EstudianteDTOItson;
import itson.actividades.BajaDTOItson;
import itson.actividades.GrupoResponseDTOItson;
import itson.actividades.GruposResponseDTOItson;
import itson.actividades.InscripcionDTOItson;
import itson.actividades.InscripcionesDTOItson;
import java.time.LocalDate;

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
    
    public ActividadDTOItson obtenerActividaddPorNombre(ActividadDTOItson actividad){
        return actividadService.obtenerActividaddPorNombre(actividad);
    }
    
    public GruposResponseDTOItson obtenerGrupos(ActividadDTOItson actividad){
      return grupoService.obtenerGrupos(actividad);
    }

    public InscripcionDTOItson inscribirActividadExterno(InscripcionDTOItson inscripcionDTOItson){
        Estudiante est= solicitarDatosEstudiante((Long.valueOf(inscripcionDTOItson.getMatriculaEstudiante())));
        inscripcionDTOItson.setIdEstudiante(String.valueOf(est.getId()));
        if(revisarCupoDisponible(inscripcionDTOItson) && revisarFechaLimite(inscripcionDTOItson)){
              InscripcionDTOItson inscripcionExterna= inscripcionService.inscribirActividadExterno(inscripcionDTOItson);
              actualizarCupoDisponible(inscripcionDTOItson);
              return inscripcionExterna;
        } else{
            System.out.println("no se pudo inscribir por cupos");
            return null;
        }
    }
    
    
    //excepciones inscribir 
    
    //              cupos disponibles 
    public boolean actualizarCupoDisponible(InscripcionDTOItson inscripcionDTOItson){
        return grupoService.actualizarCupoDisponible(inscripcionDTOItson.getIdGrupo());
    }
    
    public boolean revisarCupoDisponible(InscripcionDTOItson inscripcionDTOItson){
        int cupoDisponible= grupoService.revisarCupoDisponible(inscripcionDTOItson.getIdGrupo());
        if(cupoDisponible>0){
            return true;
        } else{
            return false;
        }
    }
    
    //              fecha limite de inscripcion
    public boolean revisarFechaLimite(InscripcionDTOItson inscripcionDTOItson){
        LocalDate fecha= grupoService.revisarFechaLimite(inscripcionDTOItson.getIdGrupo());
        if(fecha.isBefore(LocalDate.now())){
            return false;
        } else{
            return true;
        }
    }


    public InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO){
        return inscripcionService.obtenerInscripciones(estudianteDTO);
    }
    
    public GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcion){
        return grupoService.obtenerGrupoInscrito(inscripcion);
    }
    
    public BajaDTOItson darBajaActividad(BajaDTOItson baja){
        BajaDTOItson bajaDTO= inscripcionService.darBajaActividad(baja);
        if(bajaDTO!=null){
            boolean actualizado= actualizarEstadoInscripcion(baja.getIdInscripcion());
            if(!actualizado){
                System.out.println("baja creada pero no actualizada");
            }
        }
        return bajaDTO;
    }
    
    public boolean actualizarEstadoInscripcion(String idInscripcion){
        try{
            return inscripcionService.actualizarEstadoInscripcion(idInscripcion);
        } catch(IllegalArgumentException e){
            System.out.println("id sin formato valido");
            return false;
        } catch(Exception e){
            System.out.println("error al actualozar"+e.getMessage());
            e.printStackTrace();
            return false;
        }
        
    }
}