/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.tutorias;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author katia
 */
public class HorarioDTO {
    private Long id;
    private Long idTutor;
    private LocalDate fecha;
    private LocalTime hora;
    private String estadoDisponibilidad;

    public HorarioDTO() {
    }

    public HorarioDTO(Long id, Long idTutor, LocalDate fecha, LocalTime hora, String estadoDisponibilidad) {
        this.id = id;
        this.idTutor = idTutor;
        this.fecha = fecha;
        this.hora = hora;
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Long idTutor) {
        this.idTutor = idTutor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstadoDisponibilidad() {
        return estadoDisponibilidad;
    }

    public void setEstadoDisponibilidad(String estadoDisponibilidad) {
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    @Override
    public String toString() {
        return hora.toString();
    }
    
    
}
