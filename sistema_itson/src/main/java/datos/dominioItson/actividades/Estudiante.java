/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.dominioItson.actividades;

import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class Estudiante {
    private ObjectId id;
    private String matricula;
    private String nombre;

    public Estudiante() {
    }

    public Estudiante(ObjectId id, String matricula, String nombre) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
