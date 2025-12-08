/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.adaptadoresItson.actividades;


import datos.dominioItson.actividades.Grupo;
import itson.actividades.GrupoResponseDTOItson;
import itson.actividades.GruposResponseDTOItson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class GrupoAdaptador {
    
    public static GruposResponseDTOItson toDTOLista(List<Grupo> grupos){
        GruposResponseDTOItson gruposResponseDTOItson= new GruposResponseDTOItson();
        List<GrupoResponseDTOItson> grupoLista= new ArrayList<>();
    //    GrupoResponseDTOItson grupoResponseDTOItson= new GrupoResponseDTOItson();
        for(Grupo grupo: grupos){
             grupoLista.add(toDTO(grupo));    
         }
        gruposResponseDTOItson.setGruposItson(grupoLista);
        return gruposResponseDTOItson;
    }
    
    public static GrupoResponseDTOItson toDTO(Grupo grupo){
        GrupoResponseDTOItson grupoResponseDTOItson= new GrupoResponseDTOItson();
        grupoResponseDTOItson.setId(String.valueOf(grupo.getId()));
        grupoResponseDTOItson.setNombreGrupo(grupo.getNombreGrupo());
        grupoResponseDTOItson.setCupoTotal(grupo.getCupoTotal());
        grupoResponseDTOItson.setCupoDisponible(grupo.getCupoDisponible());
        grupoResponseDTOItson.setFechaInicio(grupo.getFechaInicio());
        grupoResponseDTOItson.setFechaFin(grupo.getFechaFin());
        grupoResponseDTOItson.setFechaLimiteInscripcion(grupo.getFechaLimiteInscripcion());
        //horario
        grupoResponseDTOItson.setHoraInicio(grupo.getHorario().getHoraInicio());
        grupoResponseDTOItson.setHoraFin(grupo.getHorario().getHoraFin());
        grupoResponseDTOItson.setDias(grupo.getHorario().getDias());
        //actividad 
        grupoResponseDTOItson.setNombreActividad(grupo.getActividad().getNombre());
        grupoResponseDTOItson.setCosto(grupo.getActividad().getCosto());
        //ubicacion
        grupoResponseDTOItson.setNombreUbicacion(grupo.getUbicacion().getNombre());
        grupoResponseDTOItson.setUnidad(grupo.getUbicacion().getUnidad());
        //responsable 
        grupoResponseDTOItson.setNombreResponsable(grupo.getResponsable().getNombre());
        grupoResponseDTOItson.setCorreoResponsable(grupo.getResponsable().getCorreo());
        return grupoResponseDTOItson;
        
    }
    
    
    }
    
   
    

