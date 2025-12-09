/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtoGobierno;
import java.time.LocalDate;

/**
 * The type Beca dto.
 *
 * @author Cortez, Manuel;
 */
public class BecaDTO {
    private Long codigo;
    private String tipo;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int becasDisponibles;
    private RequisitosDTO requisitos;
    private LocalDate fechaResultados;

    /**
     * Instantiates a new Beca dto.
     */
    public BecaDTO() {}

    /**
     * Instantiates a new Beca dto.
     *
     * @param becasDisponibles the becas disponibles
     * @param codigo           the codigo
     * @param descripcion      the descripcion
     * @param fechaFin         the fecha fin
     * @param fechaInicio      the fecha inicio
     * @param fechaResultados  the fecha resultados
     * @param nombre           the nombre
     * @param requisitos       the requisitos
     * @param tipo             the tipo
     */
    public BecaDTO(int becasDisponibles, Long codigo, String descripcion, LocalDate fechaFin, LocalDate fechaInicio, LocalDate fechaResultados, String nombre, RequisitosDTO requisitos, String tipo) {
        this.becasDisponibles = becasDisponibles;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.fechaResultados = fechaResultados;
        this.nombre = nombre;
        this.requisitos = requisitos;
        this.tipo = tipo;
    }

    /**
     * Gets becas disponibles.
     *
     * @return the becas disponibles
     */
    public int getBecasDisponibles() {
        return becasDisponibles;
    }

    /**
     * Sets becas disponibles.
     *
     * @param becasDisponibles the becas disponibles
     */
    public void setBecasDisponibles(int becasDisponibles) {
        this.becasDisponibles = becasDisponibles;
    }

    /**
     * Gets codigo.
     *
     * @return the codigo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * Sets codigo.
     *
     * @param codigo the codigo
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets fecha resultados.
     *
     * @return the fecha resultados
     */
    public LocalDate getFechaResultados() {
        return fechaResultados;
    }

    /**
     * Sets fecha resultados.
     *
     * @param fechaResultados the fecha resultados
     */
    public void setFechaResultados(LocalDate fechaResultados) {
        this.fechaResultados = fechaResultados;
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
     * Gets fecha fin.
     *
     * @return the fecha fin
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets fecha fin.
     *
     * @param fechaFin the fecha fin
     */
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Gets fecha inicio.
     *
     * @return the fecha inicio
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets fecha inicio.
     *
     * @param fechaInicio the fecha inicio
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Gets requisitos.
     *
     * @return the requisitos
     */
    public RequisitosDTO getRequisitos() {
        return requisitos;
    }

    /**
     * Sets requisitos.
     *
     * @param requisitos the requisitos
     */
    public void setRequisitos(RequisitosDTO requisitos) {
        this.requisitos = requisitos;
    }

    /**
     * Gets tipo.
     *
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets tipo.
     *
     * @param tipo the tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Gets descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets descripcion.
     *
     * @param descripcion the descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
