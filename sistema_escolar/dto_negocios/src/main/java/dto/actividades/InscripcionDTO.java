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
public class InscripcionDTO {
    private String id;
    private LocalDate fechaInscripcion;
  //  private Estado estado;
    //estudiante
    private String matriculaEstudiante;
    private String nombreGrupo;
    private double costo;
    //horario
    private List<String> dias;
    private String horaInicio;
    private String horaFin;
    private String idEstudiante;
    private String idGrupo;

    public InscripcionDTO() {
    }

    public InscripcionDTO(LocalDate fechaInscripcion, String matriculaEstudiante, String nombreGrupo, double costo, List<String> dias, String horaInicio, String horaFin, String idEstudiante, String idGrupo) {
        this.fechaInscripcion = fechaInscripcion;
        this.matriculaEstudiante = matriculaEstudiante;
        this.nombreGrupo = nombreGrupo;
        this.costo = costo;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idEstudiante = idEstudiante;
        this.idGrupo = idGrupo;
    }

    public InscripcionDTO(LocalDate fechaInscripcion, String matriculaEstudiante, String nombreGrupo, double costo, List<String> dias, String horaInicio, String horaFin) {
        this.fechaInscripcion = fechaInscripcion;
        this.matriculaEstudiante = matriculaEstudiante;
        this.nombreGrupo = nombreGrupo;
        this.costo = costo;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public InscripcionDTO(String id, LocalDate fechaInscripcion, String matriculaEstudiante, String nombreGrupo, double costo, List<String> dias, String horaInicio, String horaFin, String idEstudiante, String idGrupo) {
        this.id = id;
        this.fechaInscripcion = fechaInscripcion;
        this.matriculaEstudiante = matriculaEstudiante;
        this.nombreGrupo = nombreGrupo;
        this.costo = costo;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idEstudiante = idEstudiante;
        this.idGrupo = idGrupo;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
    
    
}
