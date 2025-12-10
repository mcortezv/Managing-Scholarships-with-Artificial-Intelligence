package solicitarBeca;

/**
 * The type Tutor dto.
 *
 * @author Escalante, Sebastian.
 */
public class TutorDTO {
    private String nombre;
    private String parentesco;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String telefono;
    private String correo;
    private String direccion;

    /**
     * Instantiates a new Tutor dto.
     */
    public TutorDTO() {
    }

    /**
     * Instantiates a new Tutor dto.
     *
     * @param nombre          the nombre
     * @param parentesco      the parentesco
     * @param apellidoMaterno the apellido materno
     * @param apellidoPaterno the apellido paterno
     * @param telefono        the telefono
     * @param correo          the correo
     * @param direccion       the direccion
     */
    public TutorDTO(String nombre, String parentesco, String apellidoMaterno, String apellidoPaterno, String telefono, String correo, String direccion) {
        this.nombre = nombre;
        this.parentesco = parentesco;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    /**
     * Gets apellido materno.
     *
     * @return the apellido materno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Sets apellido materno.
     *
     * @param apellidoMaterno the apellido materno
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Gets apellido paterno.
     *
     * @return the apellido paterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Sets apellido paterno.
     *
     * @param apellidoPaterno the apellido paterno
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
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
     * Gets parentesco.
     *
     * @return the parentesco
     */
    public String getParentesco() {
        return parentesco;
    }

    /**
     * Sets parentesco.
     *
     * @param parentesco the parentesco
     */
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
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
}
