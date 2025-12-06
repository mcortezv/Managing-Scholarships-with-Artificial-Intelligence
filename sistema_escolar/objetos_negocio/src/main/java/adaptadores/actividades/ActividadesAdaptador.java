/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores.actividades;
import dto.actividades.ActividadDTO;
import dto.actividades.ActividadesDTO;
import itson.ActividadDTOItson;
import itson.ActividadesDTOItson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ActividadesAdaptador {
    
    public static ActividadesDTO toDTO (ActividadesDTOItson actividadesDTOItson){
        ActividadesDTO actividadesDTO= new ActividadesDTO();
        List<ActividadDTO> actividades= new ArrayList<>();

        for(ActividadDTOItson actividadDTOItson: actividadesDTOItson.getActividades()){
            ActividadDTO actividad= new ActividadDTO();
            actividad.setId(actividadDTOItson.getId());
            actividad.setNombre(actividadDTOItson.getNombre());
            actividad.setCosto(actividadDTOItson.getCosto());
            actividades.add(actividad);
                   
        }
        actividadesDTO.setActividades(actividades);
        return actividadesDTO;
             
        
    }
    
    public static ActividadDTOItson toDTOItson(ActividadDTO actividadDTO){
        ActividadDTOItson actividadDTOItson= new ActividadDTOItson();
        actividadDTOItson.setNombre(actividadDTO.getNombre());
        actividadDTOItson.setCosto(actividadDTO.getCosto());
        return actividadDTOItson;
        
        
    }
}
