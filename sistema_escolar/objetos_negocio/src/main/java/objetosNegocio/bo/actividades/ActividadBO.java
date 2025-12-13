/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.bo.actividades;


import dto.actividades.ActividadDTO;
import objetosNegocio.adaptadores.actividades.ActividadesAdaptador;
import dto.actividades.ActividadesDTO;
import interfaces.IFachadaITSON;
import itson.actividades.ActividadDTOItson;
import objetosNegocio.bo.actividades.interfaces.IActividadBO;
import itson.actividades.ActividadesDTOItson;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ActividadBO implements IActividadBO {
    
    private IFachadaITSON fachadaITSON;

    public ActividadBO(IFachadaITSON fachadaITSON) {
        this.fachadaITSON= fachadaITSON;
    }
    
    @Override
    public ActividadesDTO obtenerActividades(){
        ActividadesDTOItson actividadesDTOItson= fachadaITSON.obtenerActividades();
        
        return ActividadesAdaptador.toDTO(actividadesDTOItson);
    }
    
     public ActividadDTO obtenerActividadPorNombre(ActividadDTO actividadDTO){
         ActividadDTOItson actividadDTOItson= ActividadesAdaptador.toDTOItson(actividadDTO);
         ActividadDTOItson actividadItsonResponse= fachadaITSON.obtenerActividaddPorNombre(actividadDTOItson);
         return ActividadesAdaptador.toDTO(actividadItsonResponse);
         
     }
    
    
    
    
}
