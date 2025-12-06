/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.actividades;


import adaptadores.actividades.ActividadesAdaptador;
import datos.repositoryItson.daoItson.IActividadDAO;
import dto.actividades.ActividadesDTO;
import interfaces.IFachadaITSON;
import interfaces.actividades.IActividadBO;
import itson.ActividadesDTOItson;

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
    
    
    
    
}
