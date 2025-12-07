/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividades.dominio;

import java.time.LocalDate;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class Baja {
    
    private LocalDate fecha;
    private String motivo;

    public Baja() {
    }

    public Baja(LocalDate fecha, String motivo) {
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    
    
}
