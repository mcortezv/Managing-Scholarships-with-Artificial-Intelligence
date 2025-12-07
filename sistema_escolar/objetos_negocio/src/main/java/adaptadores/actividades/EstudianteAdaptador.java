/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores.actividades;

import actividades.dominio.Estudiante;
import dto.actividades.EstudianteDTO;
import itson.EstudianteDTOItson;


/**
 *
 * @author janethcristinagalvanquinonez
 */
public class EstudianteAdaptador {

    public static EstudianteDTO toDTO(Estudiante estudiante) {

        EstudianteDTO estudianteDTO = new EstudianteDTO();
        estudianteDTO.setMatricula(estudiante.getMatricula());
        estudianteDTO.setId(estudiante.getId().toString());
        return estudianteDTO;
    }
    
    public static Estudiante toEntity(EstudianteDTO estudianteDTO){
        Estudiante estudiante= new Estudiante();
        estudiante.setMatricula(estudianteDTO.getMatricula());
        return estudiante;
    }
    
    public static EstudianteDTOItson toDTOItson(EstudianteDTO estudianteDTO){
        EstudianteDTOItson estudianteDTOItson= new EstudianteDTOItson();
        estudianteDTOItson.setMatricula(Long.valueOf(estudianteDTO.getMatricula()));
        return estudianteDTOItson;
        
        
    }

}
