
package controles;

import adaptadores.actividades.ActividadesAdaptador;
import adaptadores.actividades.GruposAdaptador;
import dto.actividades.ActividadDTO;
import dto.actividades.ActividadesDTO;
import dto.actividades.EstudianteDTO;
import dto.actividades.GrupoDTO;
import dto.actividades.GruposResponseDTO;
import dto.actividades.InscripcionDTO;
import interfaces.actividades.IActividadBO;
import interfaces.actividades.IGrupoBO;
import interfaces.actividades.IInscripcionBO;
//import interfaces.actividades.IInscripcionBO;
import itson.ActividadDTOItson;
import itson.ActividadesDTOItson;
import itson.actividades.GruposResponseDTOItson;

import java.util.Objects;
import interfaces.actividades.IEstudianteBOAct;

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
        this.actividadBO= Objects.requireNonNull(actividadBO);
        this.grupoBO= Objects.requireNonNull(grupoBO);
        this.inscripcionBO= Objects.requireNonNull(inscripcionBO);
        this.estudianteBO= Objects.requireNonNull(estudianteBO);
        
    }

    public ActividadesDTO obtenerActividades(){
        return actividadBO.obtenerActividades();
    }
    
    public GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO){  
        return grupoBO.obtenerGrupos(actividadDTO);   
    }
    
     public InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO){
         EstudianteDTO estudianteDTO= new EstudianteDTO(inscripcionDTO.getMatriculaEstudiante());
         EstudianteDTO estudianteEncontradoDTO= estudianteBO.obtenerEstudiante(estudianteDTO);
          EstudianteDTO estudianteGuardar;
         if(estudianteEncontradoDTO==null){
             System.out.println("no encontrado");
              estudianteGuardar= estudianteBO.guardarEstudiante(estudianteDTO);
             System.out.println("se guardo");
         } else{
             estudianteGuardar= estudianteEncontradoDTO;
         }
         
         inscripcionDTO.setIdEstudiante(estudianteGuardar.getId());
         return inscripcionBO.inscribirActividad(inscripcionDTO);
         
         
     }






}