/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.actividades;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class InscripcionDTOItson {
    private String idInscipcion;
    private LocalDate fechaInscripcion;
    //private Estado estado;
    //estudiante
    private String idEstudiante;
    private String matriculaEstudiante;
    //grupo
    private String idGrupo;
    private int cupoDisponible;
    private int cupoTotal;
    private String idActividad;
    private String nombreGrupo;
    private String nombreActividad;
    private double costo;
    //horario
    private List<String> dias;
    private String horaInicio;
    private String horaFin;

    public InscripcionDTOItson() {
    }

    public InscripcionDTOItson(LocalDate fechaInscripcion, String matriculaEstudiante, String nombreGrupo, double costo, List<String> dias, String horaInicio, String horaFin) {
        this.fechaInscripcion = fechaInscripcion;
        this.matriculaEstudiante = matriculaEstudiante;
        this.nombreGrupo = nombreGrupo;
        this.costo = costo;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public InscripcionDTOItson(String idInscipcion, LocalDate fechaInscripcion, String idEstudiante, String matriculaEstudiante, String idGrupo, String nombreGrupo, double costo, List<String> dias, String horaInicio, String horaFin) {
        this.idInscipcion = idInscipcion;
        this.fechaInscripcion = fechaInscripcion;
        this.idEstudiante = idEstudiante;
        this.matriculaEstudiante = matriculaEstudiante;
        this.idGrupo = idGrupo;
        this.nombreGrupo = nombreGrupo;
        this.costo = costo;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public InscripcionDTOItson(String idInscipcion, LocalDate fechaInscripcion, String idEstudiante, String matriculaEstudiante, String idGrupo, String idActividad, String nombreGrupo, String nombreActividad, double costo, List<String> dias, String horaInicio, String horaFin) {
        this.idInscipcion = idInscipcion;
        this.fechaInscripcion = fechaInscripcion;
        this.idEstudiante = idEstudiante;
        this.matriculaEstudiante = matriculaEstudiante;
        this.idGrupo = idGrupo;
        this.idActividad = idActividad;
        this.nombreGrupo = nombreGrupo;
        this.nombreActividad = nombreActividad;
        this.costo = costo;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public InscripcionDTOItson(LocalDate fechaInscripcion, String idEstudiante, String matriculaEstudiante, String idGrupo, int cupoDisponible, int cupoTotal, String idActividad, String nombreGrupo, String nombreActividad, double costo) {
        this.fechaInscripcion = fechaInscripcion;
        this.idEstudiante = idEstudiante;
        this.matriculaEstudiante = matriculaEstudiante;
        this.idGrupo = idGrupo;
        this.cupoDisponible = cupoDisponible;
        this.cupoTotal = cupoTotal;
        this.idActividad = idActividad;
        this.nombreGrupo = nombreGrupo;
        this.nombreActividad = nombreActividad;
        this.costo = costo;
    }
    
    
    
    

    
    
    

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getMatriculaEstudiante() {
        return matriculaEstudiante;
    }

    public void setMatriculaEstudiante(String matriculaEstudiante) {
        this.matriculaEstudiante = matriculaEstudiante;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public List<String> getDias() {
        return dias;
    }

    public void setDias(List<String> dias) {
        this.dias = dias;
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

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getIdInscipcion() {
        return idInscipcion;
    }

    public void setIdInscipcion(String idInscipcion) {
        this.idInscipcion = idInscipcion;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public int getCupoDisponible() {
        return cupoDisponible;
    }

    public void setCupoDisponible(int cupoDisponible) {
        this.cupoDisponible = cupoDisponible;
    }

    public int getCupoTotal() {
        return cupoTotal;
    }

    public void setCupoTotal(int cupoTotal) {
        this.cupoTotal = cupoTotal;
    }
    
    
    
    
    
}
