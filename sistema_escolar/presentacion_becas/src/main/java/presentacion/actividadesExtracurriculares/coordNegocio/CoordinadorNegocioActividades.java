/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.actividadesExtracurriculares.coordNegocio;

import dto.actividades.ActividadDTO;
import dto.actividades.ActividadesDTO;
import dto.actividades.BajaDTO;
import dto.actividades.EstudianteDTO;
import dto.actividades.GrupoDTO;
import dto.actividades.GruposResponseDTO;
import dto.actividades.InscripcionDTO;
import dto.actividades.InscripcionesDTO;



import interfaces.IFachadaActividad;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class CoordinadorNegocioActividades implements ICoordNegocioActividades {
    
    private final IFachadaActividad fachadaActividad;
    
    public CoordinadorNegocioActividades(IFachadaActividad fachadaActividad){
        this.fachadaActividad= fachadaActividad;
        
    }
    
    public ActividadesDTO obtenerActividades(){
        return fachadaActividad.obtenerActividades();
    }
    
    

   public GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO){
       return fachadaActividad.obtenerGrupos(actividadDTO);
   
}
   
   public InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO){
       return fachadaActividad.inscribirActividad(inscripcionDTO);
       
   }
   
   // baja 
   public InscripcionesDTO obtenerInscripciones(EstudianteDTO estudianteDTO){
       return fachadaActividad.obtenerInscripciones(estudianteDTO);
       
   }
   
   public GrupoDTO obtenerGrupoInscrito(InscripcionDTO inscripcionDTO){
       System.out.println("cordnegocio"+inscripcionDTO.getIdGrupo());
       return fachadaActividad.obtenerGrupoInscrito(inscripcionDTO);
   }
   
    public BajaDTO darBajaActividad(BajaDTO baja){
        return fachadaActividad.darBajaActividad(baja);
    }
}
