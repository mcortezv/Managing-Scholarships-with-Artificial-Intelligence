/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores.actividades;

import actividades.dominio.Estudiante;
import dto.actividades.EstudianteDTO;

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

}
