/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.dominioItson.actividades;

import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class Grupo {
    private ObjectId id;
    private String nombreGrupo;
    private int cupoTotal;
    private int cupoDisponible;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaLimiteInscripcion;
    private Horario horario;
    private Actividad actividad;
    private Ubicacion ubicacion;
    private Responsable responsable;

    public Grupo() {
    }

    public Grupo(ObjectId id, String nombreGrupo, int cupoTotal, int cupoDisponible, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaLimiteInscripcion, Horario horario, Actividad actividad, Ubicacion ubicacion, Responsable responsable) {
        this.id = id;
        this.nombreGrupo = nombreGrupo;
        this.cupoTotal = cupoTotal;
        this.cupoDisponible = cupoDisponible;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaLimiteInscripcion = fechaLimiteInscripcion;
        this.horario = horario;
        this.actividad = actividad;
        this.ubicacion = ubicacion;
        this.responsable = responsable;
    }

    

    public int getCupoTotal() {
        return cupoTotal;
    }

    public void setCupoTotal(int cupoTotal) {
        this.cupoTotal = cupoTotal;
    }

    public int getCupoDisponible() {
        return cupoDisponible;
    }

    public void setCupoDisponible(int cupoDisponible) {
        this.cupoDisponible = cupoDisponible;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaLimiteInscripcion() {
        return fechaLimiteInscripcion;
    }

    public void setFechaLimiteInscripcion(LocalDate fechaLimiteInscripcion) {
        this.fechaLimiteInscripcion = fechaLimiteInscripcion;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
    
    
    
    

    
    
    
    
    
}
