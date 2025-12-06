/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores.actividades;

import dto.actividades.GrupoDTO;
import dto.actividades.GruposResponseDTO;
import itson.actividades.GrupoResponseDTOItson;
import itson.actividades.GruposResponseDTOItson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class GruposAdaptador {
    
    public static GruposResponseDTO DTOItsonToDTO(GruposResponseDTOItson gruposResponseDTOItson){
        GruposResponseDTO gruposResponseDTO= new GruposResponseDTO();
        List<GrupoDTO> gruposDTO= new ArrayList<>();
        for(GrupoResponseDTOItson grupo: gruposResponseDTOItson.getGruposItson()){
            GrupoDTO grupoDTO= DTOItsonToDTOActividades(grupo);
            gruposDTO.add(grupoDTO);
            
        }
        gruposResponseDTO.setGrupos(gruposDTO);
        return gruposResponseDTO;
     
    }
    
    public static GrupoDTO DTOItsonToDTOActividades(GrupoResponseDTOItson grupoResponseDTOItson){
        GrupoDTO grupoDTO= new GrupoDTO();
        grupoDTO.setId(grupoResponseDTOItson.getId());
        grupoDTO.setCupoTotal(grupoResponseDTOItson.getCupoTotal());
        grupoDTO.setCupoDisponible(grupoResponseDTOItson.getCupoDisponible());
        grupoDTO.setFechaInicio(grupoResponseDTOItson.getFechaInicio());
        grupoDTO.setFechaFin(grupoResponseDTOItson.getFechaFin());
        grupoDTO.setFechaLimiteInscripcion(grupoResponseDTOItson.getFechaLimiteInscripcion());
        //horario
        grupoDTO.setHoraInicio(grupoResponseDTOItson.getHoraInicio());
        grupoDTO.setHoraFin(grupoResponseDTOItson.getHoraFin());
        grupoDTO.setDias(grupoResponseDTOItson.getDias());
        //actividad 
        grupoDTO.setIdActividad(grupoResponseDTOItson.getIdActividad());
        grupoDTO.setNombreActividad(grupoResponseDTOItson.getNombreActividad());
        grupoDTO.setCosto(grupoResponseDTOItson.getCosto());
        //ubicacion
        grupoDTO.setNombreUbicacion(grupoResponseDTOItson.getNombreUbicacion());
        grupoDTO.setUnidad(grupoResponseDTOItson.getUnidad());
        //responsable 
        grupoDTO.setNombreResponsable(grupoResponseDTOItson.getNombreResponsable());
        grupoDTO.setCorreoResponsable(grupoResponseDTOItson.getCorreoResponsable());
        return grupoDTO;
        
    }
    
}
