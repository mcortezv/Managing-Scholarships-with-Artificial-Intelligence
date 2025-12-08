/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.actividades;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class GrupoDTO {
    String nombreGrupo;
    private String id;
    private int cupoTotal;
    private int cupoDisponible;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaLimiteInscripcion;
    //horario
    private String horaInicio;
    private String horaFin;
    private List<String> dias;
    //actividad
    private String idActividad;
    String nombreActividad;
    double costo;    
    //ubicacion
    String nombreUbicacion;
    String unidad;
    //responsable
    String nombreResponsable;
    String correoResponsable;

    public GrupoDTO(int cupoTotal, int cupoDisponible, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaLimiteInscripcion, String horaInicio, String horaFin, List<String> dias, String nombreActividad, double costo, String nombreUbicacion, String unidad, String nombreResponsable, String correoResponsable) {
        this.cupoTotal = cupoTotal;
        this.cupoDisponible = cupoDisponible;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaLimiteInscripcion = fechaLimiteInscripcion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dias = dias;
        this.nombreActividad = nombreActividad;
        this.costo = costo;
        this.nombreUbicacion = nombreUbicacion;
        this.unidad = unidad;
        this.nombreResponsable = nombreResponsable;
        this.correoResponsable = correoResponsable;
    }

    public GrupoDTO(String nombreGrupo, String id, int cupoTotal, int cupoDisponible, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaLimiteInscripcion, String horaInicio, String horaFin, List<String> dias, String idActividad, String nombreActividad, double costo, String nombreUbicacion, String unidad, String nombreResponsable, String correoResponsable) {
        this.nombreGrupo = nombreGrupo;
        this.id = id;
        this.cupoTotal = cupoTotal;
        this.cupoDisponible = cupoDisponible;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaLimiteInscripcion = fechaLimiteInscripcion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dias = dias;
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.costo = costo;
        this.nombreUbicacion = nombreUbicacion;
        this.unidad = unidad;
        this.nombreResponsable = nombreResponsable;
        this.correoResponsable = correoResponsable;
    }

    

    
    
    

    public GrupoDTO() {
    }
    
    

    public int getCupoTotal() {
        return cupoTotal;
    }

    public void setCupoTotal(int cupoTotal) {
        this.cupoTotal = cupoTotal;
    }

    public int getCupoDisponible() {
        return cupoDisponible;
    }

    public void setCupoDisponible(int cupoDisponible) {
        this.cupoDisponible = cupoDisponible;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaLimiteInscripcion() {
        return fechaLimiteInscripcion;
    }

    public void setFechaLimiteInscripcion(LocalDate fechaLimiteInscripcion) {
        this.fechaLimiteInscripcion = fechaLimiteInscripcion;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public List<String> getDias() {
        return dias;
    }

    public void setDias(List<String> dias) {
        this.dias = dias;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getCorreoResponsable() {
        return correoResponsable;
    }

    public void setCorreoResponsable(String correoResponsable) {
        this.correoResponsable = correoResponsable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
    
    
    
    
    
    
    
}
