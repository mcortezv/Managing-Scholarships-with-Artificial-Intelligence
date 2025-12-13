/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.serviceItson;


import datos.repositoryItson.daoItson.impl.ActividadDAO;
import itson.actividades.ActividadesDTOItson;
import datos.adaptadoresItson.actividades.ActividadAdaptador;
import datos.dominioItson.actividades.Actividad;
import datos.excepciones.DaoException;
import datos.excepciones.ServiceException;
import itson.actividades.ActividadDTOItson;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ActividadService {
    
    private final ActividadDAO actividadDAO;

    public ActividadService() {
        this.actividadDAO = new ActividadDAO();
    }
    

    
    public ActividadesDTOItson obtenerActividades(){
        try{
            List<Actividad> actividades= actividadDAO.obtenerActividades();
            return ActividadAdaptador.toResponseDTOListaAct(actividades);
        } catch(DaoException e){
            throw new ServiceException("Error al obtener las actividades", e);
        }
         
       // return actividadDAO.obtenerActividades();
    }
    
    public ActividadDTOItson obtenerActividaddPorNombre(ActividadDTOItson actividad){
        try{
            Actividad actividadEntity= actividadDAO.obtenerActividaddPorNombre(actividad.getNombre());
            return ActividadAdaptador.toResponseDTOAct(actividadEntity);
        } catch(DaoException e){
            throw new ServiceException("Error al obtener actividad por nombre", e);
        }
        
    } 
    
    
    
    
}
