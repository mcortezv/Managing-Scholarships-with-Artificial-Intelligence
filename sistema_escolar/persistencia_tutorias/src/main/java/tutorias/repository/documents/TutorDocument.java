/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutorias.repository.documents;

import java.time.Instant;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 *
 * @author katia
 */
public class TutorDocument {
    @BsonProperty("_id")
    private ObjectId _id;    
    
    @BsonProperty("idTutor")
    private Long idTutor;      
    
    @BsonProperty("nombre")
    private String nombre;
    
    @BsonProperty("carrera")
    private String carrera;
    
    @BsonProperty("cubiculo")
    private String cubiculo;   
    
    @BsonProperty("enlace")
    private String enlace;    
    
    @BsonProperty("creadoEn")
    private Instant creadoEn;

    public TutorDocument() {
    }

    public TutorDocument(ObjectId _id, Long idTutor, String nombre, String carrera, String cubiculo, String enlace, Instant creadoEn) {
        this._id = _id;
        this.idTutor = idTutor;
        this.nombre = nombre;
        this.carrera = carrera;
        this.cubiculo = cubiculo;
        this.enlace = enlace;
        this.creadoEn = creadoEn;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Long getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Long idTutor) {
        this.idTutor = idTutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCubiculo() {
        return cubiculo;
    }

    public void setCubiculo(String cubiculo) {
        this.cubiculo = cubiculo;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Instant creadoEn) {
        this.creadoEn = creadoEn;
    } 
}
