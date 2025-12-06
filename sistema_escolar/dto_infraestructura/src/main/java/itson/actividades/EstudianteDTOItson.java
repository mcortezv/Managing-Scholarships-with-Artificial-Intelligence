/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.actividades;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class EstudianteDTOItson {
    
    private String id;
    private String matricula;
    private String nombre;

    public EstudianteDTOItson() {
    }

    public EstudianteDTOItson(String id, String matricula, String nombre) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
