package datos.dominioItson;

import datos.dominioItson.enums.Carrera;
import org.bson.types.ObjectId;

public class Estudiante {
    public ObjectId id;
    private Long matricula;
    private String nombre;
    private Carrera carrera;
    private Tutor tutor;
    private String contrasenia;
    private String telefono;
    private String direccion;
    private String correo;

    public Estudiante() {}

    public Estudiante(Carrera carrera, String contrasenia, String correo, String direccion, Long matricula, String nombre, String telefono, Tutor tutor) {
        this.carrera = carrera;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.direccion = direccion;
        this.matricula = matricula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tutor = tutor;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    
}