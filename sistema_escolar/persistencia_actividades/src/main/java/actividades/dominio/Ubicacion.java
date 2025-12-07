/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividades.dominio;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class Ubicacion {
    
    private String nombre;
    private String unidad;

    public Ubicacion() {
    }

    public Ubicacion(String nombre, String unidad) {
        this.nombre = nombre;
        this.unidad = unidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    
    
    
}
