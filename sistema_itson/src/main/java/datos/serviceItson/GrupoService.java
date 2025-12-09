/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.serviceItson;

import datos.adaptadoresItson.actividades.ActividadAdaptador;
import datos.adaptadoresItson.actividades.GrupoAdaptador;
import datos.dominioItson.actividades.Actividad;
import datos.dominioItson.actividades.Grupo;

import datos.repositoryItson.daoItson.actividades.impl.GrupoDAO;
import itson.actividades.ActividadDTOItson;
import itson.actividades.GruposResponseDTOItson;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class GrupoService {
    
    private final GrupoDAO grupoDAO;

    public GrupoService() {
        this.grupoDAO = new GrupoDAO();
    }
    
    public GruposResponseDTOItson obtenerGrupos(ActividadDTOItson actividad){
        Actividad actividadEntity= ActividadAdaptador.toEntity(actividad);
        List<Grupo> grupos= grupoDAO.obtenerGrupos(actividadEntity);
        return GrupoAdaptador.toDTOLista(grupos);
        
    }
    
    
    
    
    
}
