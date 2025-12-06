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
public class Actividad {
    private ObjectId id;
    private String nombre;
    private double costo;

    public Actividad() {
    }

    public Actividad(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public Actividad(ObjectId id, String nombre, double costo) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    

    @Override
    public String toString() {
        return "Actividad{" + "nombre=" + nombre + ", costo=" + costo + '}';
    }
    
    
    
    
    
}
