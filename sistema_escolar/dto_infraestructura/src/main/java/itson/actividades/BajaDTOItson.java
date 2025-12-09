/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.actividades;

import java.time.LocalDate;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class BajaDTOItson {
    
    private String idInscripcion;
    private String motivo;
    private LocalDate fechaBaja;

    public BajaDTOItson() {
    }

    public BajaDTOItson(String idInscripcion, String motivo) {
        this.idInscripcion = idInscripcion;
        this.motivo = motivo;
    }

    public String getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(String idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
    
    
    
    
    
}
