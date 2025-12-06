/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson;
/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ActividadDTOItson {
    private String id;
    String nombre;
    double costo;

    public ActividadDTOItson(String id, String nombre, double costo) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
    }
    
    

    public ActividadDTOItson(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public ActividadDTOItson() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
    
}
