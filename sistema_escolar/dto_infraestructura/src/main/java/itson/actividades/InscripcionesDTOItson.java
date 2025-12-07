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
public class InscripcionesDTOItson {
    
    List<InscripcionDTOItson> inscripciones;

    public InscripcionesDTOItson() {
    }

    public InscripcionesDTOItson(List<InscripcionDTOItson> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public List<InscripcionDTOItson> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionDTOItson> inscripciones) {
        this.inscripciones = inscripciones;
    }
    
    
    
}
