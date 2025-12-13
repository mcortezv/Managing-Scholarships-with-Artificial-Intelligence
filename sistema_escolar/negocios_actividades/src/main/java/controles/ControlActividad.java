package controles;

import dto.actividades.*;
import excepciones.ActividadesException;
import objetosNegocio.bo.actividades.interfaces.IActividadBO;
import objetosNegocio.bo.actividades.interfaces.IGrupoBO;
import objetosNegocio.bo.actividades.interfaces.IInscripcionBO;
//import interfaces.actividades.IInscripcionBO;

import java.util.Objects;
import objetosNegocio.bo.actividades.interfaces.IEstudianteBOAct;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ControlActividad {

    private final IActividadBO actividadBO;
    private final IGrupoBO grupoBO;
    private final IInscripcionBO inscripcionBO;
    private final IEstudianteBOAct estudianteBO;

    public ControlActividad(IActividadBO actividadBO, IGrupoBO grupoBO, IInscripcionBO inscripcionBO, IEstudianteBOAct estudianteBO) {
        this.actividadBO = Objects.requireNonNull(actividadBO);
        this.grupoBO = Objects.requireNonNull(grupoBO);
        this.inscripcionBO = Objects.requireNonNull(inscripcionBO);
        this.estudianteBO = Objects.requireNonNull(estudianteBO);

    }

    
   //caso de uso inscripciones
    public ActividadesDTO obtenerActividades() {
        try {
            ActividadesDTO actividadesDTO = actividadBO.obtenerActividades();
            if (actividadesDTO == null || actividadesDTO.getActividades() == null || actividadesDTO.getActividades().isEmpty()) {
                throw new ActividadesException("No hay existencia de actividades");
            }
            return actividadesDTO;
        } catch (Exception ex) {
            throw new ActividadesException("Error al consultar actividades: " + ex.getMessage());
        }
    }
    
     public ActividadDTO obtenerActividadPorNombre(ActividadDTO actividadDTO){
         try{
             return actividadBO.obtenerActividadPorNombre(actividadDTO);
         } catch(Exception e){
             throw new ActividadesException("Error al buscar actividad: " + e.getMessage());
         }
     }

    public GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO) {
        try {
            GruposResponseDTO gruposDTO = grupoBO.obtenerGrupos(actividadDTO);
            if (gruposDTO == null || gruposDTO.getGrupos() == null || gruposDTO.getGrupos().isEmpty()) {
                throw new ActividadesException("no hay grupos abiertos para esta actividad");
            }
            return gruposDTO;
            } catch(ActividadesException e){
                    throw e;
            } catch(Exception ex){
                throw new ActividadesException("Error al obtener grupos: " + ex.getMessage());
         }     
        }
                    

    


//    public InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO) {
//        EstudianteDTO estudianteDTO = new EstudianteDTO(inscripcionDTO.getMatriculaEstudiante());
//        EstudianteDTO estudianteEncontradoDTO = estudianteBO.obtenerEstudiante(estudianteDTO);
//        EstudianteDTO estudianteGuardar;
//        if (estudianteEncontradoDTO == null) {
//            estudianteGuardar = estudianteBO.guardarEstudiante(estudianteDTO);
//        } else {
//            estudianteGuardar = estudianteEncontradoDTO;
//        }
//
//        inscripcionDTO.setIdEstudiante(estudianteGuardar.getId());
//        return inscripcionBO.inscribirActividad(inscripcionDTO);
//
//    }
    
    public InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO){
        try{
            InscripcionDTO inscripcionExterno= inscribirActividadExterno(inscripcionDTO);
            if(inscripcionExterno!=null){
                return inscribirActividadLocal(inscripcionDTO);
            } else{
              throw new ActividadesException("El sistema externo rechazó la inscripción.");
            }
        } catch(ActividadesException e){
            throw e;
        } catch(Exception e){
            throw new ActividadesException("Error en el proceso de inscripción: " + e.getMessage());
        }
        
            
    }
    
    public InscripcionDTO inscribirActividadExterno(InscripcionDTO inscripcionDTO){
        return inscripcionBO.inscribirActividadExterno(inscripcionDTO);
    }
    
    
    
    public InscripcionDTO inscribirActividadLocal(InscripcionDTO inscripcionDTO){
        try{
            EstudianteDTO estudianteDTOEncontrado= buscarEstudiantePorMatricula(inscripcionDTO.getMatriculaEstudiante());
            if(estudianteDTOEncontrado!=null){
                inscripcionDTO.setIdEstudiante(estudianteDTOEncontrado.getId());
            }   
            if(estudianteDTOEncontrado==null){
                EstudianteDTO estudianteGuardado= guardarEstudiante(inscripcionDTO.getMatriculaEstudiante());
                inscripcionDTO.setIdEstudiante(estudianteGuardado.getId());

            }
             return inscripcionBO.inscribirActividadLocal(inscripcionDTO);
        } catch(Exception e){
            throw new ActividadesException("Inscripción exitosa en itson, pero error al guardar en local " + e.getMessage());   
        }
    }
    
    public EstudianteDTO buscarEstudiantePorMatricula(String matricula){
        return estudianteBO.obtenerEstudiantePorMatricula(matricula);
    }
    
    public EstudianteDTO guardarEstudiante(String matricula){
        return estudianteBO.guardarEstudiante(matricula);
    }
    
    //caso de uso baja 
    public InscripcionesDTO obtenerInscripciones(EstudianteDTO estudianteDTO) {
        try {
            return inscripcionBO.obtenerInscripciones(estudianteDTO);
        } catch (Exception ex) {
            throw new ActividadesException("Ha ocurrido un error al cargar tus inscripciones"+ex.getMessage());
        }

    }

    public GrupoDTO obtenerGrupoInscrito(InscripcionDTO inscripcionDTO) {
        try {
            return inscripcionBO.obtenerGrupoInscrito(inscripcionDTO);
        } catch (Exception ex) {
            throw new ActividadesException(("Error al obtener grupo"));
        }
    }

//    public BajaDTO darBajaActividad(BajaDTO baja) {
//        try {
//            BajaDTO bajaGuardada = inscripcionBO.darBajaActividad(baja);
//            if (bajaGuardada != null) {
//                inscripcionBO.actualizarEstadoInscripcion(bajaGuardada.getIdInscripcion());
//            } else {
//                throw new ActividadesException(("Error al procesar la baja"));
//            }
//            return bajaGuardada;
//        } catch (Exception ex) {
//            throw new ActividadesException(ex.getMessage());
//        }
//
//    }
    public BajaDTO darBajaActividad(BajaDTO baja){
        BajaDTO bajaExterno= darBajaExterno(baja);
        if(bajaExterno!=null){
             BajaDTO bajaLocal= darBajaLocal(baja);
             if(bajaLocal!=null){
                 return bajaLocal;
             } else{
                 throw new ActividadesException("Se actualizó el sistema externo, pero hubo un error al guardar la baja localmente");
             }
        } else{
            throw new ActividadesException("El sistema externo no pudo procesar la baja");
        }
        
    }
    
    
    public BajaDTO darBajaExterno(BajaDTO baja){
        try{    
          BajaDTO bajaExter= inscripcionBO.darBajaActividadExterno(baja);
         return bajaExter;
        } catch(Exception e){
            throw new ActividadesException(("Error al dar de baja en externo"));
        }
    }
    
    public BajaDTO darBajaLocal(BajaDTO baja){
        try{
            BajaDTO bajaLocal= inscripcionBO.darBajaLocal(baja);
            return bajaLocal;
        } catch(Exception ex){
            throw new ActividadesException("Error al dar de baja en local ");
        }
    }
    
//    public boolean darBajaExterno(BajaDTO baja){
//        try{
//            return inscripcionBO.actualizarEstadoInscripcion(baja.getIdInscripcion());
//        } catch(Exception e){
//            throw new ActividadesException(("Error al dar de baja en ITSON"));
//        }
//        
//    }
}
