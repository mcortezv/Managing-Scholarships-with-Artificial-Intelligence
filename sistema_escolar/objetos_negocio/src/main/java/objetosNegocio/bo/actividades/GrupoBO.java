/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.bo.actividades;

import objetosNegocio.adaptadores.actividades.ActividadesAdaptador;
import objetosNegocio.adaptadores.actividades.GruposAdaptador;
import dto.actividades.ActividadDTO;
import dto.actividades.GruposResponseDTO;
import interfaces.IFachadaITSON;
import objetosNegocio.bo.actividades.interfaces.IGrupoBO;
import itson.actividades.ActividadDTOItson;
import itson.actividades.GruposResponseDTOItson;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class GrupoBO implements IGrupoBO{
    
    private IFachadaITSON fachadaITSON;

    public GrupoBO(IFachadaITSON fachadaITSON) {
        this.fachadaITSON = fachadaITSON;
    }
    
   public GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO){
       ActividadDTOItson actividadDTOItson= ActividadesAdaptador.toDTOItson(actividadDTO);
       GruposResponseDTOItson gruposResponseDTOItson= fachadaITSON.otenerGrupos(actividadDTOItson);
       GruposResponseDTO gruposResponseDTO= GruposAdaptador.DTOItsonToDTO(gruposResponseDTOItson);
       return gruposResponseDTO;
   }
    
    
    
}
