/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gobierno;

/**
 * The type Estudiante dto gobierno.
 *
 * @author Cortez, Manuel;
 */
public class EstudianteDTOGobierno {
    private Long matricula;
    private String nombre;
    private String carrera;
    private TutorDTOGobierno tutor;
    private String contrasenia;
    private String telefono;
    private String direccion;
    private String correo;

    /**
     * Instantiates a new Estudiante dto gobierno.
     */
    public EstudianteDTOGobierno() {}

    /**
     * Instantiates a new Estudiante dto gobierno.
     *
     * @param carrera     the carrera
     * @param contrasenia the contrasenia
     * @param correo      the correo
     * @param direccion   the direccion
     * @param matricula   the matricula
     * @param nombre      the nombre
     * @param telefono    the telefono
     * @param tutor       the tutor
     */
    public EstudianteDTOGobierno(String carrera, String contrasenia, String correo, String direccion, Long matricula, String nombre, String telefono, TutorDTOGobierno tutor) {
        this.carrera = carrera;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.direccion = direccion;
        this.matricula = matricula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tutor = tutor;
    }

    /**
     * Gets carrera.
     *
     * @return the carrera
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * Sets carrera.
     *
     * @param carrera the carrera
     */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    /**
     * Gets contrasenia.
     *
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Sets contrasenia.
     *
     * @param contrasenia the contrasenia
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Gets correo.
     *
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Sets correo.
     *
     * @param correo the correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Gets direccion.
     *
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets direccion.
     *
     * @param direccion the direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Gets matricula.
     *
     * @return the matricula
     */
    public Long getMatricula() {
        return matricula;
    }

    /**
     * Sets matricula.
     *
     * @param matricula the matricula
     */
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombre the nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets telefono.
     *
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets telefono.
     *
     * @param telefono the telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Gets tutor.
     *
     * @return the tutor
     */
    public TutorDTOGobierno getTutor() {
        return tutor;
    }

    /**
     * Sets tutor.
     *
     * @param tutor the tutor
     */
    public void setTutor(TutorDTOGobierno tutor) {
        this.tutor = tutor;
    }
}
