/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutorias.repository.documents;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import tutorias.dominio.enums.EstadoDisponibilidad;

/**
 *
 * @author katia
 */
public class HorarioDocument {
    @BsonProperty("_id")
    private ObjectId _id;   
    
    @BsonProperty("id")
    private Long id;
    
    @BsonProperty("idTutor")
    private Long idTutor;
    
    @BsonProperty("fecha")
    private LocalDate fecha;
    
    @BsonProperty("hora")
    private LocalTime hora;
    
    @BsonProperty("estadoDisponibilidad")
    private EstadoDisponibilidad estadoDisponibilidad;
    
    @BsonProperty("creadoEn")
    private Instant creadoEn;

    public HorarioDocument() {
    }

    public HorarioDocument(ObjectId _id, Long id, Long idTutor, LocalDate fecha, LocalTime hora, EstadoDisponibilidad estadoDisponibilidad, Instant creadoEn) {
        this._id = _id;
        this.id = id;
        this.idTutor = idTutor;
        this.fecha = fecha;
        this.hora = hora;
        this.estadoDisponibilidad = estadoDisponibilidad;
        this.creadoEn = creadoEn;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Long getId() {
        return id;
    }

    public Long getIdTutor() {
        return idTutor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public EstadoDisponibilidad getEstadoDisponibilidad() {
        return estadoDisponibilidad;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdTutor(Long idTutor) {
        this.idTutor = idTutor;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setEstadoDisponibilidad(EstadoDisponibilidad estadoDisponibilidad) {
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    public void setCreadoEn(Instant creadoEn) {
        this.creadoEn = creadoEn;
    }
}

