/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.actividades;

import java.time.LocalDate;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class BajaDTO {
    private String idBaja;
    private String idInscripcion;
    private String motivo;
    private LocalDate fechaBaja;

    public BajaDTO() {
    }

    public BajaDTO(String idBaja, String idInscripcion, String motivo, LocalDate fechaBaja) {
        this.idBaja = idBaja;
        this.idInscripcion = idInscripcion;
        this.motivo = motivo;
        this.fechaBaja = fechaBaja;
    }

    public BajaDTO(String motivo) {
        this.motivo = motivo;
    }
    
    

    

    public BajaDTO(String idInscripcion, String motivo) {
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

    public String getIdBaja() {
        return idBaja;
    }

    public void setIdBaja(String idBaja) {
        this.idBaja = idBaja;
    }
    
    
    
    
    
}
