/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.dominioItson.actividades;

import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class Inscripcion {
    
    private ObjectId id;
    private LocalDate fechaInscripcion;
    private String estado;
    private Baja baja;
    private Estudiante estudiante;
    private Grupo grupo;

    public Inscripcion() {
        this.estado = "ACTIVA";
        this.baja = null;
    }

    public Inscripcion(ObjectId id, LocalDate fechaInscripcion, String estado, Baja baja, Estudiante estudiante, Grupo grupo) {
        this.id = id;
        this.fechaInscripcion = fechaInscripcion;
        this.estudiante = estudiante;
        this.grupo = grupo;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Baja getBaja() {
        return baja;
    }

    public void setBaja(Baja baja) {
        this.baja = baja;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    
    
    
    
}
