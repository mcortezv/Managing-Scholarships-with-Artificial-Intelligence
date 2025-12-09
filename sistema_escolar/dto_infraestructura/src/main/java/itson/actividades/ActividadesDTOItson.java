/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.actividades;

import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ActividadesDTOItson {
    
    List<ActividadDTOItson> actividades;

    public ActividadesDTOItson(List<ActividadDTOItson> actividades) {
        this.actividades = actividades;
    }
    
    

    public ActividadesDTOItson() {
    }

    public List<ActividadDTOItson> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadDTOItson> actividades) {
        this.actividades = actividades;
    }
    
    
}
