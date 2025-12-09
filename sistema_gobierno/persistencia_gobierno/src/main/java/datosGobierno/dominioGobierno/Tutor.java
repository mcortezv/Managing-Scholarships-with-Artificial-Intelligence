/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosGobierno.dominioGobierno;
import datosGobierno.dominioGobierno.enums.Parentesco;

/**
 * The type Tutor.
 *
 * @author Cortez, Manuel;
 */
public class Tutor {
    private Long id;
    private String nombre;
    private Parentesco parentesco;
    private String telefono;
    private String direccion;
    private String correo;

    /**
     * Instantiates a new Tutor.
     */
    public Tutor() {}

    /**
     * Instantiates a new Tutor.
     *
     * @param nombre     the nombre
     * @param parentesco the parentesco
     * @param telefono   the telefono
     * @param direccion  the direccion
     * @param correo     the correo
     */
    public Tutor(String nombre, Parentesco parentesco, String telefono, String direccion, String correo) {
        this.nombre = nombre;
        this.parentesco = parentesco;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }

    /**
     * Instantiates a new Tutor.
     *
     * @param id         the id
     * @param nombre     the nombre
     * @param parentesco the parentesco
     * @param telefono   the telefono
     * @param direccion  the direccion
     * @param correo     the correo
     */
    public Tutor(Long id, String nombre, Parentesco parentesco, String telefono, String direccion, String correo) {
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
    public Parentesco getParentesco() {
        return parentesco;
    }

    /**
     * Sets parentesco.
     *
     * @param parentesco the parentesco
     */
    public void setParentesco(Parentesco parentesco) {
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
