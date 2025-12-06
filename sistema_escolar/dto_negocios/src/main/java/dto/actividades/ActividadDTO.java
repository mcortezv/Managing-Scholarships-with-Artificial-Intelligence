/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.actividades;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ActividadDTO {
    private String id;
    String nombre;
    double costo;

    public ActividadDTO(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public ActividadDTO(String id, String nombre, double costo) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
    }
    

    public ActividadDTO() {
        
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
