/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.actividades;

import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class InscripcionesDTO {
    
    List<InscripcionDTO> inscripciones;

    public InscripcionesDTO() {
    }

    public InscripcionesDTO(List<InscripcionDTO> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public List<InscripcionDTO> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionDTO> inscripciones) {
        this.inscripciones = inscripciones;
    }
    
    
}
