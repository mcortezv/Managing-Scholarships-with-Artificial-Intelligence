/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividades.dominio;

import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class Baja {
    private ObjectId idBaja;
    private ObjectId idInscripcion;
    private LocalDate fecha;
    private String motivo;

    public Baja() {
    }

    public Baja(ObjectId idBaja, ObjectId idInscripcion, LocalDate fecha, String motivo) {
        this.idBaja = idBaja;
        this.idInscripcion = idInscripcion;
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

    public ObjectId getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(ObjectId idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public ObjectId getIdBaja() {
        return idBaja;
    }

    public void setIdBaja(ObjectId idBaja) {
        this.idBaja = idBaja;
    }
    
    

    
    
}
