/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.adaptadoresItson.actividades;


import datos.dominioItson.actividades.Actividad;
import itson.actividades.ActividadDTOItson;
import itson.actividades.ActividadesDTOItson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ActividadAdaptador {
    
    public static ActividadDTOItson toResponseDTOAct(Actividad actividad){
        ActividadDTOItson actividadDTOItson= new ActividadDTOItson();
            actividadDTOItson.setId(actividad.getId().toString());
            actividadDTOItson.setNombre(actividad.getNombre());
            actividadDTOItson.setCosto(actividad.getCosto());
            return actividadDTOItson;
            
        }
    
    public static ActividadesDTOItson toResponseDTOListaAct(List<Actividad> actividades){
        ActividadesDTOItson actividadesDTOItson= new ActividadesDTOItson();
        List<ActividadDTOItson> listaActividadesDTOItson= new ArrayList<>();
        for(Actividad actividad: actividades){
            listaActividadesDTOItson.add(toResponseDTOAct(actividad));       
        }
        actividadesDTOItson.setActividades(listaActividadesDTOItson);
        return actividadesDTOItson;
    }
    
    public static Actividad toEntity(ActividadDTOItson actividadDTO){
        Actividad actividad= new Actividad();
        actividad.setCosto(actividadDTO.getCosto());
        actividad.setNombre(actividadDTO.getNombre());
        return actividad;
    }
    
}
