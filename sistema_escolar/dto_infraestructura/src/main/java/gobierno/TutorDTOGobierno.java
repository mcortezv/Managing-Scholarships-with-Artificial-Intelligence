/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gobierno;

/**
 * The type Tutor dto gobierno.
 *
 * @author Cortez, Manuel;
 */
public class TutorDTOGobierno {
    private Long id;
    private String nombre;
    private String parentesco;
    private String telefono;
    private String direccion;
    private String correo;

    /**
     * Instantiates a new Tutor dto gobierno.
     */
    public TutorDTOGobierno() {}

    /**
     * Instantiates a new Tutor dto gobierno.
     *
     * @param nombre     the nombre
     * @param parentesco the parentesco
     * @param telefono   the telefono
     * @param direccion  the direccion
     * @param correo     the correo
     */
    public TutorDTOGobierno(String nombre, String parentesco, String telefono, String direccion, String correo) {
        this.nombre = nombre;
        this.parentesco = parentesco;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }

    /**
     * Instantiates a new Tutor dto gobierno.
     *
     * @param id         the id
     * @param nombre     the nombre
     * @param parentesco the parentesco
     * @param telefono   the telefono
     * @param direccion  the direccion
     * @param correo     the correo
     */
    public TutorDTOGobierno(Long id, String nombre, String parentesco, String telefono, String direccion, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.parentesco = parentesco;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
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
