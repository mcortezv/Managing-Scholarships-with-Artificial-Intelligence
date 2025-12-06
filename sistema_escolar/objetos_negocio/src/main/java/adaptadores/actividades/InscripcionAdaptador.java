/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores.actividades;

import dto.actividades.InscripcionDTO;
import itson.actividades.InscripcionDTOItson;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class InscripcionAdaptador {
    
    public static InscripcionDTOItson toDTOItson(InscripcionDTO inscripcionDTO){
        InscripcionDTOItson inscripcionDTOItson= new InscripcionDTOItson();
        //el id del estudiante
        inscripcionDTOItson.setIdEstudiante(inscripcionDTO.getIdEstudiante());
        inscripcionDTOItson.setFechaInscripcion(inscripcionDTO.getFechaInscripcion());
        inscripcionDTOItson.setMatriculaEstudiante(inscripcionDTO.getMatriculaEstudiante());
        //id grupo
        inscripcionDTOItson.setIdGrupo(inscripcionDTO.getIdGrupo()); 
        inscripcionDTOItson.setNombreGrupo(inscripcionDTO.getNombreGrupo());
        inscripcionDTOItson.setCosto(inscripcionDTO.getCosto());
        inscripcionDTOItson.setDias(inscripcionDTO.getDias());
        inscripcionDTOItson.setHoraInicio(inscripcionDTO.getHoraInicio());
        inscripcionDTOItson.setHoraFin(inscripcionDTO.getHoraFin());
        System.out.println(inscripcionDTOItson);
        return inscripcionDTOItson;
    }
    
    public static InscripcionDTO toDTONegocio(InscripcionDTOItson inscripcionDTOItson){
        InscripcionDTO inscripcionDTO= new InscripcionDTO();
        inscripcionDTO.setId(inscripcionDTOItson.getIdInscipcion());
        // el del estudiante
        inscripcionDTO.setIdEstudiante(inscripcionDTOItson.getIdEstudiante());
        inscripcionDTO.setFechaInscripcion(inscripcionDTOItson.getFechaInscripcion());
        inscripcionDTO.setMatriculaEstudiante(inscripcionDTOItson.getMatriculaEstudiante());
        //grupi
        inscripcionDTO.setIdGrupo(inscripcionDTOItson.getIdGrupo());
        inscripcionDTO.setNombreGrupo(inscripcionDTOItson.getNombreGrupo());
        inscripcionDTO.setCosto(inscripcionDTOItson.getCosto());
        inscripcionDTO.setDias(inscripcionDTOItson.getDias());
        inscripcionDTO.setHoraInicio(inscripcionDTOItson.getHoraInicio());
        inscripcionDTO.setHoraFin(inscripcionDTOItson.getHoraFin());
        return inscripcionDTO;
    }
    
}
