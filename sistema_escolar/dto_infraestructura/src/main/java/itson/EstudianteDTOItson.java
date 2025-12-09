package itson;

/**
 * The type Estudiante dto itson.
 *
 * @author Escalante, Sebastian.
 */
public class EstudianteDTOItson {
    private Long matricula;
    private String nombre;
    private String carrera;
    private String telefono;
    private String direccion;
    private String correo;

    /**
     * Instantiates a new Estudiante dto itson.
     */
    public EstudianteDTOItson() {
    }

    /**
     * Instantiates a new Estudiante dto itson.
     *
     * @param carrera   the carrera
     * @param correo    the correo
     * @param direccion the direccion
     * @param matricula the matricula
     * @param nombre    the nombre
     * @param telefono  the telefono
     */
    public EstudianteDTOItson(String carrera, String correo, String direccion, Long matricula, String nombre, String telefono) {
        this.carrera = carrera;
        this.correo = correo;
        this.direccion = direccion;
        this.matricula = matricula;
        this.nombre = nombre;
        this.telefono = telefono;
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
}
